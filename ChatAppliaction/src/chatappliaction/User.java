/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatappliaction;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author victor
 */
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private DataInputStream in;
    private DataOutputStream out;
    
    public User(String fName,String e){
        firstName=fName;
        lastName=null;
        email=e;
    }
    public void setDataStream(Socket s){
        try{
        in=new DataInputStream(s.getInputStream());
        System.out.println("Successfully set input stream for user "+ this.firstName);
        
        out=new DataOutputStream(s.getOutputStream());
        System.out.println("Succesfully set ouput stream for user "+this.firstName);
        }catch(IOException e){
            System.out.println("Could not set inputand output stream");
            e.printStackTrace();
        }
    }
    public void sendString(String toBeSent){
        if(out!=null){
            try{
            out.writeUTF(toBeSent);
            }catch(IOException e){
                System.out.println("Could not send the string");
            }
        }else{
            System.out.println("The DataOutputstream is null ");
        }
    }
    public String getMessage(){
        try{
        return in.readUTF();
        }catch(IOException ex){
            System.out.println("Could not print out the word");
        }
        String str="ERROR: COULD NOT RECEIVE MESSAGE";
        return str;
    }
    
}
