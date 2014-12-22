package se.spaider.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Main{

	
	ServerSocket ssocket;
	Socket socket;
	public static String ip = "localhost";
	public static int port = 3264;
	public static BufferedReader input;
	public static PrintWriter output;
	public static Random random = new Random();

	public static ArrayList<User> users = new ArrayList<User>();

	public Main(){
		try {
			ssocket = new ServerSocket(port);
			System.out.println("Server started");
			while(true){
				socket = ssocket.accept();
				System.out.println(socket.getInetAddress() + "Has connected");
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				output = new PrintWriter(socket.getOutputStream(),true);
				
				String id = IDGenerator.getRandomID();
				
				User u = new User(input,output,id);
				u.output.println("yourid,"+id);
				users.add(u);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args){
		new Main();
	}



}
