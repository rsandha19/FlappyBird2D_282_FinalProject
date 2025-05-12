package Code;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Skins {
	private int costBird1=500;
	private int costBird2=2000;
	private int costBird3=10000;
	private int balance;
	private int[] list=new int[3];
	private Points points=new Points();
	public Skins() {
		balance=points.getPoints();
		
		 try {
	    	 String link = new String("resources/data/skins.txt");
	    	 File inFile=new File(link);
	    	 Scanner sc=new Scanner(inFile);
	    	 if(sc.hasNextLine()) {
	    		 String line=sc.nextLine();
	    		 String[] values=line.split(",");
	    		 for (int i = 0; i < values.length && i < list.length; i++) {
	                    list[i] = Integer.parseInt(values[i].trim());
	    	 
	    	 }
	    	 }
	    	 sc.close();
		 }catch(IOException e) {
	    		 e.printStackTrace();
	    	 }
	}
	private void saveSkins() {
		try {
			String link = "resources/data/skins.txt";
			PrintWriter writer = new PrintWriter(link);
			writer.println(list[0] + "," + list[1] + "," + list[2]);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int bird1Status() {
		return list[0];
	}
	public int bird2Status() {
		return list[1];
	}
	public int bird3Status() {
		return list[2];
	}
	public void buyBird1() {
		if(balance>=costBird1) {
			
		list[0]=1;
		balance=balance-costBird1;
		points.setPoints(balance);
		saveSkins();
		}
	}
	public void buyBird2() {
		if(balance>=costBird2) {
			list[1]=1;
			balance=balance-costBird2;
			points.setPoints(balance);
			saveSkins();
			}
	}
	public void buyBird3() {
		if(balance>=costBird3) {
			list[2]=1;
			balance=balance-costBird3;
			points.setPoints(balance);
			saveSkins();
			}
		}
}
