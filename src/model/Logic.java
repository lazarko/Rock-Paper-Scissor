/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 * @author Lazarko
 */
public class Logic {
    public final static String WIN_MSG = "WIN";
    public final static String LOSE_MSG = "LOSE";
    public final static String DRAW_MSG = "DRAW";
    
    public final static String ROCK_STR = "ROCK";
    public final static String SCISSOR_STR = "SCISSOR";
    public final static String PAPER_STR = "PAPER";
    public final static String QUIT_STR = "QUIT";
    

    public static String randomizeGuess(){
        int min = 1;
        int max = 3;
        Random r = new Random();
        int number = r.nextInt((max - min) + 1) + 1;
        switch(number){
            case 1:
                return ROCK_STR;
            case 2:
                return PAPER_STR;
            case 3:
                return SCISSOR_STR;
            default:
                return null;
        }
    }


    public static String rock(String play){
        if (play.equalsIgnoreCase(PAPER_STR)) {
            return LOSE_MSG;
        }else if(play.equalsIgnoreCase(ROCK_STR)){
            return DRAW_MSG;
        }else{
            return WIN_MSG;
        }
    }
    public static String paper(String play){
        if(play.equalsIgnoreCase(SCISSOR_STR)){
            return LOSE_MSG;
        }else if(play.equalsIgnoreCase(PAPER_STR)){
            return DRAW_MSG;
        }else{
            return WIN_MSG;
        }

    }
    public static String scissor(String play){
        if(play.equalsIgnoreCase(ROCK_STR)){
            return LOSE_MSG;
        }else if(play.equalsIgnoreCase(SCISSOR_STR)){
            return DRAW_MSG;
        }else{
            return WIN_MSG;
        }

    }
    
}
