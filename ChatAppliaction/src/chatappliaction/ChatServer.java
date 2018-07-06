/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatappliaction;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author victor
 */
public class ChatServer{
  
    private static final int PORT = 1001;

    static HashSet<String> names = new HashSet<String>();

    static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        InetAddress inetAddress = InetAddress. getLocalHost();
        System.out.println("IP Address:- " + inetAddress. getHostAddress());
        try {
            while (true) {
                new ServerHandler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }

    }
}
        
    

