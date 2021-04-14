package java8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import java.util.function.Predicate;
import java.util.stream.IntStream; 


public class Lamda {
	
	
	interface Even {
		abstract boolean even(int i);
	}
	
	

	public static void main(String args[]) {
		System.out.println(add());
	}

	
	public static String add() {
		Even e = (i) -> (i%2 == 0);
		if(e.even(1)) {
			return "true";
		}
		return null;
	}
}
