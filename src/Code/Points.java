package Code;
import java.io.*;
import java.util.Scanner;

public class Points{
	int points;
     Points(){
    	 try {
    	 String link = new String("resources/data/points.txt");
    	 File inFile=new File(link);
    	 Scanner sc=new Scanner(inFile);
    	 if(sc.hasNextInt()) {
    		 points=sc.nextInt();   		 
    	 }else {
    		 points=0;
    		
    	 }
    	 sc.close();
    	 }catch(IOException e) {
    		 e.printStackTrace();
    		 points=0;
    	 }
    	 
     }
     public int getPoints() {
    	 return points;
     }
     public void setPoints(int newPoints) {
    	    try {
    	        FileWriter fw = new FileWriter("resources/data/points.txt");
    	        fw.write(String.valueOf(newPoints));
    	        fw.close();
    	        this.points = newPoints;
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	}

}


