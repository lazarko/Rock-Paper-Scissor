package com.networkprogramming.srpgame;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.os.Process;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    ImageButton rockBtn, scissorBtn, paperBtn;
    Button quitBtn;
    TextView textResponse;
    TextView scoreText;
    public int SCORE_INT;

    public final static String ROCK_STR = "ROCK";
    public final static String SCISSOR_STR = "SCISSOR";
    public final static String PAPER_STR = "PAPER";
    public final static String QUIT_STR = "QUIT";



    String dstAddress = "10.91.196.185" ;
    int dstPort = 9393;
    Socket clientSocket;
    PrintWriter toClient;
    BufferedReader fromClient;
    String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy p = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(p);

        try{
            clientSocket = new Socket(dstAddress, dstPort);
            fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            toClient = new PrintWriter(clientSocket.getOutputStream(), false);
            SCORE_INT = 0;

        }catch(IOException e){
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = (TextView) findViewById(R.id.score);
        textResponse = (TextView) findViewById(R.id.resultView);

        rockBtn = (ImageButton) findViewById(R.id.stoneBtn);
        scissorBtn = (ImageButton) findViewById(R.id.scissorsBtn);
        paperBtn = (ImageButton) findViewById(R.id.paperBtn);
        quitBtn = (Button) findViewById(R.id.quitBtn);

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toClient.println(QUIT_STR);
                toClient.flush();
                finish();
            }
        });

        rockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg(ROCK_STR);
                try{
                    input = fromClient.readLine();
                    textResponse.setText(input);
                    setScore(input);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        paperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg(PAPER_STR);
                try{
                    input = fromClient.readLine();
                    textResponse.setText(input);
                    setScore(input);
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        });

        scissorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg(SCISSOR_STR);
                try{
                    input = fromClient.readLine();
                    textResponse.setText(input);
                    setScore(input);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });


    }

    public void onResume(){
        super.onResume();
    }

    public void onDestroy(){

        try {
            clientSocket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }
    public void setScore(String msg){
        if("WIN".equalsIgnoreCase(msg)){
            SCORE_INT++;
            scoreText.setText(Integer.toString(SCORE_INT));
        }else if("LOSE".equalsIgnoreCase(msg)){
            SCORE_INT--;
            scoreText.setText(Integer.toString(SCORE_INT));
        }else{
            scoreText.setText(Integer.toString(SCORE_INT));
        }
    }
    public void sendMsg(String msg){
        toClient.println(msg);
        toClient.flush();
    }
}
