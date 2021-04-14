package distributed.webfetch;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

class SaveHyperLinkTask implements Runnable {

	private String url;
	private int index;
	
	private static final int TIMEOUT = 5000;

	public SaveHyperLinkTask(String url, int index) {
		this.url = url;
		this.index = index;
	}

	@Override
	public void run() {
		try {
			CloseableHttpClient httpclient = HttpClients.createMinimal();
			HttpGet httpget = new HttpGet(url);
			httpget.setConfig(
					RequestConfig.custom()
						.setSocketTimeout(TIMEOUT)
						.setConnectTimeout(TIMEOUT)
						.setConnectionRequestTimeout(TIMEOUT)
						.build()
			);
			System.out.println("Getting and saving hyperlink content: " + index + " " + url);
			HttpResponse httpresponse = httpclient.execute(httpget);
			Scanner sc = new Scanner(httpresponse.getEntity().getContent());
			StringBuilder page = new StringBuilder("");
			while (sc.hasNext()) {
				String line = sc.nextLine();
				page.append(line);
			}
			File file = new File("out/" + index + ".html");
			file.createNewFile();
			String data = page.toString();
			PrintWriter writer = new PrintWriter(file);
			writer.write(data);
			writer.close();
			httpclient.close();
		} catch (Exception e) {
			Log log = LogFactory.getLog("SaveHyperLink");
			log.error("TASK FAILED " + e.getMessage());
		}
		System.out.println("Thread complete" + index);
	}

}

public class WebFetch {

	public static void main(String args[]) {
		System.out.println("Please enter your URL");
		Scanner urlScanner = new Scanner(System.in);
		String SITE_URL = urlScanner.nextLine();
		urlScanner.close();
		ExecutorService pool = Executors.newFixedThreadPool(50);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(SITE_URL);
		try {
			HttpResponse httpresponse = httpclient.execute(httpget);
			Scanner sc = new Scanner(httpresponse.getEntity().getContent());
			StringBuilder page = new StringBuilder("");
			while (sc.hasNext()) {
				String line = sc.nextLine();
				page.append(line);
			}
			Pattern linkPattern = Pattern.compile("(<a[^>]+>.+?<\\/a>)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			Matcher pageMatcher = linkPattern.matcher(page);
			ArrayList<String> links = new ArrayList<String>();
			int index = 0;
			while (pageMatcher.find()) {
				String match = pageMatcher.group();
				if (!match.contains("href=\"#")) {
					String[] linkParts = match.split("href=\"");
					if (linkParts.length >= 2) {
						String[] urlParts = linkParts[1].split("\"");
						String url = urlParts[0];
						if (!url.startsWith("http") && !links.contains(url))
							url = SITE_URL.concat(url);
						links.add(url);
						pool.execute(new SaveHyperLinkTask(url, index));
						index++;
					}
				}
			}
			System.out.println("Finished submitting tasks");
			pool.shutdown();
			File file = new File("out/main.html");
			file.createNewFile();
			String data = page.toString();
			PrintWriter writer = new PrintWriter(file);
			writer.write(data);
			System.out.println("Saved main content");
			writer.close();
			httpclient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
