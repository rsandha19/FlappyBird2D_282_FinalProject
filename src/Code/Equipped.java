package Code;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Equipped {
	private int[] list=new int[4];
	public Equipped(){
		 try {
	    	 String link = new String("resources/data/equipped.txt");
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
	public void equipSkin(int g){
		for(int i=0;i<4;i++) {
			if(i==g) {
				list[i]=1;
			}else {
				list[i]=0;
			}
		}
		saveEquipped();
		
	}
	private void saveEquipped() {
		try {
			String link = "resources/data/equipped.txt";
			PrintWriter writer = new PrintWriter(link);
			writer.println(list[0] + "," + list[1] + "," + list[2] + "," + list[3]);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getEquippedIndex() {
		for (int i = 0; i < list.length; i++) {
			if (list[i] == 1) {
				return i;
			}
		}
		return -1;
	}

}

