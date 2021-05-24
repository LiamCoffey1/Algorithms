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
	
	// Timeout for HTTP Requests so threads don't get stuck
	private static final int TIMEOUT = 5000;

	public SaveHyperLinkTask(String url, int index) {
		this.url = url;
		this.index = index;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
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
		long end = System.currentTimeMillis() - start;
		System.out.println("Thread complete: - " + index + " - Time: " + end);
	}

}

public class WebFetch {
	
	/**
	 * How many threads to fetch HTML content in parallel
	 * Tested with 8 cores
	 */
	private static final int THREAD_AMOUNT = 50;
	
	private static String SITE_URL;
	
	/**
	 * Takes a whole hyperlink html tag and extracts the href part
	 * @param match - hyperlink html tag
	 * @param hrefMatch - either version with ' or version with "
	 * @param previousLinks - previous found links to prevent duplicates
	 * @return only the url within the html tag
	 */
	private static String extractLink(String match, String hrefChar, ArrayList<String> previousLinks) {
		if (!match.contains("href=" + hrefChar + "#")) {
			String[] linkParts = match.split("href=" + hrefChar);
			if (linkParts.length >= 2) {
				// Get only the link part of the href part
				String[] urlParts = linkParts[1].split(hrefChar);
				String url = urlParts[0];
				// If relative URL add back the site location for later processing
				if (!url.startsWith("http"))
					url = SITE_URL.concat(url);
				return url;
			}
		}
		return null;
	}

	public static void main(String args[]) {
		System.out.println("Please enter your URL");
		Scanner urlScanner = new Scanner(System.in);
		SITE_URL = urlScanner.nextLine();
		urlScanner.close();
		ExecutorService pool = Executors.newFixedThreadPool(THREAD_AMOUNT);
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
			
			//Match hyperlink tags with regex
			String aTagRegex = "(<a[^>]+>.+?<\\/a>)";
			Pattern linkPattern = Pattern.compile(aTagRegex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			Matcher pageMatcher = linkPattern.matcher(page);
			
			//Extract URL from hyperlink tag and submit for get request
			ArrayList<String> links = new ArrayList<String>();
			int index = 0;
			while (pageMatcher.find()) {
				String match = pageMatcher.group();
				String extracted = "";
				//Extract links that use ' in href of " in href
				if((extracted = extractLink(match, "\"", links)) != null || (extracted = extractLink(match, "\'", links)) != null) {
					//Ensure no duplicates
					if(!links.contains(extracted)) {
						// Add link to previous links list for checking duplicates
						links.add(extracted);
						// Add new task with the link for processing in parallel
						pool.execute(new SaveHyperLinkTask(extracted, ++index));
					}
				}		
			}
			
			// Finished submitting tasks save the main page content and close
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
