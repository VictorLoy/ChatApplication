/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatappliaction;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author victor
 */
public class Client {

    private User theUser;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String toBeSent;
    
    public Client(String word){
        toBeSent=word;
        
    }
    public void setUser(String name,String email){
        theUser=new User(name,email);
    }
    public void connect(int port){
        
        try{
            InetAddress add;
            add=InetAddress.getLocalHost();
            socket=new Socket(add.getHostName(),port);
            
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());
            theUser.setDataStream(socket);
            
//            OutputStream outToServer = socket.getOutputStream();
//            DataOutputStream out = new DataOutputStream(outToServer);
//            out.writeUTF(toBeSent);
            theUser.sendString(toBeSent);
            
            
        }catch(UnknownHostException e){
            System.out.println("Unknown host");
            e.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    
        public void getMessage(){
            System.out.println(theUser.getMessage());
        }
    
    
    
}
