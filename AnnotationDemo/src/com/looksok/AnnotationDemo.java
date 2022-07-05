package com.looksok;

public class AnnotationDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("\n-------- AnnotationDemo: looksok.wordpress.com --------\n");
		PersonInfo p = new PersonInfo("John", "Doe", "john@example.com", "+48 123-456-789", 999);
		
		System.out.println(p.toString(false));
		System.out.println(p.toString(true));
	}

}
