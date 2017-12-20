/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import model.Logic;

/**
 *
 * @author Lazarko
 */
public class ServerThread implements Runnable {
    
    private Socket client;
    private BufferedReader fromClient;
    private PrintWriter toClient;
    String msg;
    private final static String QUIT_MSG = "QUIT";
    String play;
    
    
    public ServerThread(Socket client){
        this.client = client;
    }
    @Override
    public void run() {
        try{
            fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            toClient = new PrintWriter(client.getOutputStream(), false);
        }catch(IOException e){
            e.printStackTrace();
        }

        try{
            
            String input = fromClient.readLine();
            System.out.println("------------------");
            System.out.println("INPUT: " + input);

            play = Logic.randomizeGuess();

            System.out.println("GENERATED:" + play);

            if (input.equalsIgnoreCase(QUIT_MSG)) {
                System.out.println("DISCONNECTED");
                toClient.println(QUIT_MSG);
                toClient.flush();
                disconnect();
            } else if (input.equalsIgnoreCase(Logic.ROCK_STR)) {
                msg = Logic.rock(play);
                toClient.println(msg);
                toClient.flush();
                System.out.println("RESULT: " + msg);
                this.run();
            } else if (input.equalsIgnoreCase(Logic.PAPER_STR)) {
                msg = Logic.paper(play);
                toClient.println(msg);
                toClient.flush();
                System.out.println("RESULT: " + msg);
                this.run();
            } else if (input.equalsIgnoreCase(Logic.SCISSOR_STR)) {
                msg = Logic.scissor(play);
                toClient.println(msg);
                toClient.flush();
                System.out.println("RESULT: " + msg);
                this.run();
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void disconnect(){
        try{
            client.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Client disconnected");
    }
}
