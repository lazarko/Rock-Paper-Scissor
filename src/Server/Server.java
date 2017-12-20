/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Lazarko
 */
public class Server {
    
    private static final int PORT_NUMBER = 9393;
    public static void main(String []args){
        Server server = new Server();
        server.init();
    }

    public void init(){
        try{
            ServerSocket listener = new ServerSocket(PORT_NUMBER);
            System.out.println("SERVER IS RUNNING");
            while(true){
                Socket client = listener.accept();
                System.out.println("Accepted");
                startHandler(client);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void startHandler(Socket socket){ 
        ServerThread st = new ServerThread(socket);
        Thread thread = new Thread(st);
        thread.start();
    }

    
}
