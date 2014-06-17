package eu.balumonster.minestorm4j;

import java.util.Scanner;

public class TestClient {

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		boolean go=true;
		Minestorm minestorm=new Minestorm("localhost", 45342);
		minestorm.connect();
		while(go){
			String c=sc.next();
			String[] a=c.split(" ");
			switch (a[0]) {
			case "start":
				minestorm.startServer("test", "");
				break;
			case "stop":
				minestorm.stopServer("test");
				break;
			case "quit":
				go=false;
				break;
			default:
				System.out.println("Unknown Command");
				break;
			}
		}
		sc.close();
	}
	
}
