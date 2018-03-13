/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.ConsoleApplication.lib;

import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author vdsbt33
 */
public class ConsoleText {
    Scanner scan;
    String titleMessage;

    public ConsoleText() {
        this.scan = new Scanner(System.in);
        this.titleMessage = new String();
    }
    
    public PrintStream getOutput(){
        return System.out;
    }
    
    public Scanner getInput(){
        return this.scan;
    }
    
    public void br(){
        System.out.print("\n");
    }
    
    public void br(int amount){
        for (int i = 0; i < amount; i++){
            br();
        }
    }
    
    public void print(String output){
        System.out.print(output);
    }
    
    public void println(String output){
        System.out.print(output + "\n");
    }
    
    public String printr(String output){
        System.out.print(output);
        return scan.nextLine();
    }
    
    public String printrln(String output){
        System.out.print(output + "\n");
        return scan.nextLine();
    }
    
    public String read(){
        return scan.nextLine();
    }
    
    public int readInt(){
        return scan.nextInt();
    }
    
    public long readLong(){
        return scan.nextLong();
    }
    
    public float readFloat(){
        return scan.nextFloat();
    }
    
    public double readDouble(){
        return scan.nextDouble();
    }
    
    public void setTitleMessage(String message){
        this.titleMessage = message + "\n";
        for (int i = 0; i < message.length(); i++){
            this.titleMessage += "=";
        }
        this.titleMessage += "\n";
    }
    
    public void showTitleMessage(){
        if (!this.titleMessage.equals("")) {
            this.print(titleMessage);
        } else {
            this.print("Title Message not set");
        }
    }
    
    public void showTitleMessage(String message){
        if (!this.titleMessage.equals("")) {
            message += "\n";
            for (int i = 0; i < message.length(); i++){
                message += "=";
                print(message);
            }
            message += "\n";
            this.print(message);
        } else {
            this.print("Title Message not set");
        }
    }
    
    public void showSubtitleMessage(String message){
        String subtitleMessage = message + "\n";
        if (!message.equals("")){
            for (int i = 0; i < message.length(); i++){
                subtitleMessage += "-";
            }
            subtitleMessage += "\n";
            this.print(subtitleMessage);
        } else {
            this.print("Subtitle Message not set");
        }
    }
    
    public void pause(){
        this.println("Pressione ENTER para continuar.");
        this.read();
    }
}
