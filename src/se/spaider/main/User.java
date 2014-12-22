package se.spaider.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class User implements Runnable {

	public BufferedReader input;
	public PrintWriter output;
	public Thread thread = new Thread(this);
	public String id = "";
	
	public User(BufferedReader input, PrintWriter output, String id){
		this.input = input;
		this.output = output;
		this.id = id;
		thread.start();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(true){
			String s;
			try {
				while((s = input.readLine()) != null){
					System.out.println("Got message from client: "+s);
					
					if(s.startsWith("httpquery")){
						String userID = s.split(",")[s.split(",").length-1];
						String request = s.split(",")[1];
						if(!request.endsWith(".html")){
							request += "/index.html";
						}
						File file = new File("www/"+request);
						if(userID.equals(id)){
							
							if(!file.exists()){
								output.println("httpresponse,"+"ERROR 404");
								continue;
							}
							
							BufferedReader docreader = new BufferedReader(new FileReader(file));
							String ss;
							String resp = "";
							while((ss = docreader.readLine()) != null){
								resp += ss;
							}
							
							output.println("httpresponse,"+resp);
							docreader.close();
							
						}
					}
					
				}
			} catch (IOException e) {
				Main.users.remove(this);
				thread.interrupt();
				thread.stop();
				e.printStackTrace();
			}
		}
		
	}

}
