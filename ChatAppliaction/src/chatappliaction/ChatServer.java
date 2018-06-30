/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatappliaction;

import java.io.*;
import java.net.*;

/**
 *
 * @author victor
 */
public class ChatServer extends Thread {
    private ServerSocket sock;
    private int portNumber;
    
    public ChatServer(int num){
        portNumber=num;
        try{
        sock=new ServerSocket(portNumber);
        System.out.println("Succesfully Connected to port "+portNumber);
        }
        catch(IOException e){
            System.out.println("Could not listen on port "+ portNumber);
        }
    }
    public void run(){
        try{
            System.out.print("Running the server at port "+portNumber);
            System.out.println("Waiting for client on port " + 
               sock.getLocalPort() + "...");
            Socket server = sock.accept();
            Socket server1 = sock.accept();
            
            
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            System.out.println("Just connected to " + server1.getRemoteSocketAddress());
            
            DataInputStream in = new DataInputStream(server.getInputStream());
            DataInputStream in1 = new DataInputStream(server1.getInputStream());
            
            System.out.println(in.readUTF()+"::::This is what was sent");
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
               + "\nGoodbye!");
            server.close();
            
            System.out.println(in1.readUTF()+"::::This is what was sent");
            DataOutputStream out1 = new DataOutputStream(server1.getOutputStream());
            out1.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
               + "\nGoodbye!");
            server1.close();
            
        }catch (SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            
         } catch (IOException e) {
            e.printStackTrace();
            
         }
    }
    
}
