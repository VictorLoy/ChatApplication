/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatappliaction;

import java.net.*;
import javax.swing.JFrame;

/**
 *
 * @author victor
 */
public class ChatAppliaction {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }

    
}
