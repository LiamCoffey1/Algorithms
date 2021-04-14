package java8;

class PrintType<P> {
	
	public <T> void printType(T type) {
		System.out.println(type);
	}
	
}

public class Generics {
	
	public static void main(String args[]) {
		new PrintType<String>().printType("String");
		new PrintType<Integer>().printType(1);
		new PrintType<Boolean>().printType(true);
	}
}
