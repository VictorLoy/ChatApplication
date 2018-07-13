/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatappliaction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author victor
 */
public class Client {
    private String ipAdd, name, email;
    
    
    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Chatter");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);

    /**
     * Constructs the client by laying out the GUI and registering a
     * user with the textfield so that pressing Return in the
     * listener sends the textfield contents to the server.  
     */
    public Client() {

        // Layout GUI
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "South");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();

        // Add Listeners
        textField.addActionListener(new ActionListener() {
            /**
             * Responds to pressing the enter key in the textfield by sending
             * the contents of the text field to the server.    Then clear
             * the text area in preparation for the next message.
             */
            public void actionPerformed(ActionEvent e) {
   
                out.println(textField.getText());
                textField.setText("");
                
            }
        });
    }

    /**
     * Prompt for and return the address of the server.
     */
    private String getServerAddress() {
        return JOptionPane.showInputDialog(
            frame,
            "Enter IP Address of the Server:",
            "Welcome to the Chatter",
            JOptionPane.QUESTION_MESSAGE);
    }
    private String getEmail(){
        return JOptionPane.showInputDialog(
            frame,
            "Enter your email:",
            "Welcome to the Chatter",
            JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Prompt for and return the desired screen name.
     */
    private String getName() {
        return JOptionPane.showInputDialog(
            frame,
            "Choose a screen name:",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Connects to the server then enters the processing loop.
     */
    public void run() throws IOException {

        // Make connection and initialize streams
        String serverAddress="";
        if(checkFile("profile.txt")){
                readProfile();
                serverAddress=ipAdd;   
            }else{
                serverAddress = getServerAddress();
                email=getEmail();
                ipAdd=serverAddress;
            }
        
        Socket socket = new Socket(serverAddress, 1001);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server, according to the protocol.
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                if(checkFile("profile.txt")){
                    readProfile();
                   out.println(name); 
                }else{
                    name=getName();
                    out.println(name);
                }
                
            } else if (line.startsWith("NAMEACCEPTED")) {
                textField.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
              
                messageArea.append(line.substring(8) + "\n");
		  
            }
            writeProfile();
        }
        
    }
    public boolean checkFile(String fileName){
        File f = new File(fileName);
        if(f.exists() && !f.isDirectory()) { 
        return true;
        }
        return false;
    }
    public void readProfile(){
        
        File inputFile=new File("profile.txt");//Put the fiel name here
        try{
        Scanner in= new Scanner(inputFile);
        ipAdd=in.nextLine();
        name=in.nextLine();
        email=in.nextLine();
        in.close();
        }catch(FileNotFoundException ex){
            System.out.println("File not found");
        }
    }
    public void writeProfile(){
        File outputFile=new File("profile.txt");
        try{
        PrintWriter fileOut=new PrintWriter(outputFile);
        fileOut.println(ipAdd);
        fileOut.println(name);
        fileOut.println(email);
        fileOut.close();
        
        }catch(FileNotFoundException ex){
            System.out.println("File not found");
        }
    }

    /**
     * Runs the client as an application with a closeable frame.
     */
    }
    
