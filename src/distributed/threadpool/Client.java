package distributed.threadpool;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


class WorldStateSubscriber implements Runnable {

	Socket server;
	public WorldStateSubscriber(Socket server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		World w;
		try {
			ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
			while(ois.readObject() != null) {
				w = (World) ois.readObject();
				System.out.println("Recieved object");
				w.players.forEach(p -> {
					System.out.println("Player ID " + p.id + " X: " + p.x + " Y: " + p.y);
				});
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
public class Client {
	public static void main(String args[]) {
		try {
			Socket server = new Socket("127.0.0.1", 4000);
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(server.getInputStream()));
			Scanner sc = new Scanner(System.in);
			PrintWriter out =
			        new PrintWriter(server.getOutputStream(), true);
			new Thread(new WorldStateSubscriber(server)).start();
			JFrame frame = new JFrame("JFrame Example");  
	        JPanel panel = new JPanel();  
	        panel.setLayout(new FlowLayout());  
	        JLabel label = new JLabel("JFrame By Example");  
	        JButton buttonU = new JButton();
	        buttonU.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
					out.println("u");
	            }
	        });
	        buttonU.setText("MoveUp");  
	        JButton buttonD = new JButton();  
	        buttonD.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
					out.println("d");
	            }
	        });
	        buttonD.setText("MoveDown");  
	        JButton buttonL = new JButton();  
	        buttonL.setText("MoveLeft");  
	        buttonL.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
					out.println("a");
	            }
	        });
	        JButton buttonR = new JButton();  
	        buttonR.setText("MoveRight");  
	        buttonR.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
					out.println("d");
	            }
	        });
	        panel.add(label);  
	        panel.add(buttonU); 
	        panel.add(buttonD);
	        panel.add(buttonL);
	        panel.add(buttonR);
	        frame.add(panel);  
	        frame.setSize(200, 300);  
	        frame.setLocationRelativeTo(null);  
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        frame.setVisible(true);  
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
