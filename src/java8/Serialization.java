package java8;

import java.io.*;

/*
 * Class being saved, mainly a container class
 * @implements Serializable, important for saving class
 * 
 */
class Person implements Serializable {
	
	private static final long serialVersionUID = 3334548164498920778L;
	private String name;
	private int id, age;
	
	public Person(int id, String name, int age) {
		this.name = name;
		this.id = id;
		this.age = age;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
}

/*
 * Main class wich does the operations
 */
public class Serialization {
	
	private static final String FILE_PATH = new String("./data/person.ser");


	/*
	 * Writes the class to the file
	 */
	static void writeClass() {
		try {
			Person p = new Person(1,"Bob",20);
			FileOutputStream fos = new FileOutputStream(FILE_PATH);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(p);
			os.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Reads file and prints contents
	 * 
	 */
	static void readClass() {
		try {
			FileInputStream fis = new FileInputStream(FILE_PATH);
			ObjectInputStream is = new ObjectInputStream(fis);
			Person p = (Person)is.readObject();
			System.out.println("Id : " + p.getId() + " Name : " + p.getName() + " Age : " + p.getAge());
			is.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String args[]) {
		writeClass();
		readClass();
	}
}
