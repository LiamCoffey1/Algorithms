package distributed.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class Receiver extends Thread {
	protected MulticastSocket socket = null;
	protected byte[] buf = new byte[256];
	protected boolean isRunning = true;
	private final int PORT = 8888;
	private final String ADDRESS = "224.2.2.3";

	public void run() {
		try {
			socket = new MulticastSocket(PORT);
			InetAddress mcastaddr = InetAddress.getByName(ADDRESS);
			InetSocketAddress group = new InetSocketAddress(mcastaddr, PORT);
			NetworkInterface netIf = NetworkInterface.getByInetAddress(mcastaddr);
			socket.joinGroup(group, netIf);
			System.out.println("Welcome to the chat!");
			while (isRunning) {
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				String message = new String(packet.getData(), 0, packet.getLength());
				System.out.println(message);
			}
			socket.leaveGroup(group, netIf);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Transmitter {
	private DatagramSocket socket;
	private InetAddress group;
	private byte[] buf;
	private String name;
	private final int PORT = 8888;
	private final String ADDRESS = "224.2.2.3";

	public Transmitter(String name) {
		this.name = name;
	}

	protected void sendMessage(String message) throws IOException {
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
		transmit(timeStamp + " " + name + ": " + message);
	}
	protected void playerJoined() throws IOException {
		transmit(name + " has joined the chat!");
	}
	protected void playerLeft() throws IOException {
		transmit(name + " has left the chat!");
	}
	
	protected void transmit(String message) throws IOException {
		socket = new DatagramSocket();
		group = InetAddress.getByName(ADDRESS);
		buf = message.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, group, PORT);
		socket.send(packet);
		socket.close();
	}
}

public class ChatClient {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a nickname: ");
		String name = sc.nextLine();
		// Run receiver code on separate thread
		Receiver rcvr = new Receiver();
		rcvr.start();
		// Run transmitter off main thread
		Transmitter transmitter = new Transmitter(name);
		try {
			transmitter.playerJoined();
			// Use Scanner to take input and transmit messages
			while (sc.hasNext()) {
				String message = sc.nextLine();
				if(message.equals("leave")) {
					// Notify receiver to stop once user has left
					rcvr.isRunning = false;
					transmitter.playerLeft();
					break;
				}
				transmitter.sendMessage(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
	}
}
