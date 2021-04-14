package distributed.threadpool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Player implements Serializable {
	
	private static final long serialVersionUID = -9095407924704629872L;
	
	int x, y, id;
	Player(int x, int y, int id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
		System.out.println("Player " + id + " Moved to " + x + " " + y);
	}
}
class World implements Serializable {
	
	private static World singleton;
	
	public World() {
		singleton = this;
	}
	
	public static World getSingleton() {
		if(singleton == null)
			return new World();
		else return singleton;
	}
	
	private static final long serialVersionUID = -2552218216218950925L;
	
	LinkedList<Player> players = new LinkedList<Player>();
	
	public int getSize() {
		return players.size();
	}
	
	public void MovePlayer(int playerId, int x, int y) {
		players.get(playerId).moveTo(x, y);
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
}
public class Server {
	
	public static void main(String args[]) {
		try {
			ServerSocket ss = new ServerSocket(4000);
			ExecutorService pool = Executors.newFixedThreadPool(5);
			Scanner sc = new Scanner(System.in);
			System.out.println("Server Initiated");
			World world = World.getSingleton();
			while(!ss.isClosed()) {
				System.out.println("Waiting for connection");
				Socket client = ss.accept();
				Player p = new Player(0,0, world.getSize());
				world.addPlayer(p);
				System.out.println("Found client " + client.isConnected());
				pool.execute(new PacketHandler(client, p.id));
				pool.execute(new WorldStatePublisher(client));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}

class WorldStatePublisher implements Runnable {
	Socket client;
	public WorldStatePublisher(Socket client) {
		this.client = client;
	}
	@Override
	public void run() {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(client.getOutputStream());
			while(true) {
				try {
					out.flush();
					out.reset();
					out.writeObject(World.getSingleton());
					Thread.sleep(2000);
				} catch (SocketException se) {
					break;
				} catch (InterruptedException | IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}


class PacketHandler implements Runnable {
	Socket client;
	int playerId;
	public PacketHandler(Socket client, int playerId) {
		this.client = client;
		this.playerId = playerId;
	}
	@Override
	public void run() {
		System.out.println("waiting for message input");
		World world = World.getSingleton();
		Player player = world.players.get(playerId);
		try {
		 BufferedReader in = new BufferedReader(
			        new InputStreamReader(client.getInputStream()));
		 String message;
			while((message = in.readLine()) != null) {
				System.out.println("Incoming message " + message);
				if(message.equals("close")) {
					client.close();
				} else {
					switch(message) {
						case "u":
							world.MovePlayer(playerId, player.x, player.y + 1);
							break;
						case "a":
							world.MovePlayer(playerId, player.x-1, player.y);
							break;
						case "d":
							world.MovePlayer(playerId, player.x, player.y - 1);
							break;
						case "s":
							world.MovePlayer(playerId, player.x+1, player.y);
							break;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
