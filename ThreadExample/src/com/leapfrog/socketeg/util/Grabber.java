/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.socketeg.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JTextArea;

/**
 *
 * @author onesoft
 */
public class Grabber extends Thread {
    
    private String link;
    private JTextArea txtOutput;

    public Grabber(String link, JTextArea txtOutput) {
        this.link = link;
        this.txtOutput = txtOutput;
    }
    
    
    
    
    public String grab() throws IOException{
        URLConnection conn = new URL(link).openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = "";
        StringBuilder content = new StringBuilder();
        
        while((line= reader.readLine()) != null){
            content.append(line);
            content.append("\r\n");
        }
            //System.out.println(content.toString());
            reader.close();
            return content.toString();
    }

    @Override
    public void run() {
        try{
            txtOutput.setText(grab());
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
    
}
