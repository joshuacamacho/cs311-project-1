/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs311.project.pkg1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Josh
 */
public class Cs311Project1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner filein = new Scanner(new FileInputStream("input.txt"));
        int count=1;
        while(filein.hasNext()){
            //initialize Dfsa
            System.out.println("Finite State Automaton #"+count);
            count++;
            Dfsa dfsa = new Dfsa();
            dfsa.setNumStates(filein.nextLine());
            dfsa.setFinalStates(filein.nextLine());
            dfsa.setAlphabet(filein.nextLine());
            //Set delta function values
            while(filein.hasNext()){
                String next = filein.nextLine();
                if(next.length()==0){
                    dfsa.dump();
                    System.out.printf("%50s %10s\n","ε",dfsa.evalString(next));
                    break;
                }
                if(next.charAt(0)=='(') dfsa.deltaPush(next);
                else{
                    dfsa.dump();
                    System.out.printf("%50s %10s\n",next,dfsa.evalString(next));
                    break;
                }
            }
            //Check strings
            while(filein.hasNext()){
                String next = filein.nextLine();
                if(next.equals("////")) break;
                System.out.printf("%50s %10s\n",next,dfsa.evalString(next));
            }
        
        }
       
    }
    
    
    
}
