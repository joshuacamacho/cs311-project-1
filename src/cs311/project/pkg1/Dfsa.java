/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs311.project.pkg1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Josh
 */
public class Dfsa {
    private int num_states;
    private List final_states;
    private Map alphabet;
    private Map delta;
    private int currentState;
    
    Dfsa(){
        num_states = 0;
        final_states = new ArrayList();
        alphabet = new HashMap();
        delta = new HashMap();
        currentState=0;
    }
    
    public void setFinalStates(String final_states){
        final_states = final_states.replaceAll(" ", "");
        for(int i=0; i<final_states.length(); i++){
            this.final_states.add( Character.getNumericValue(final_states.charAt(i)) );
        }
    }
    
    public void setNumStates(String num){
        num_states = Integer.parseInt(num);
    }
    
    public void setAlphabet(String alphabet){
        alphabet = alphabet.replaceAll(" ", "");
        for(int i=0; i<alphabet.length(); i++){
            this.alphabet.put( alphabet.charAt(i), i);
        }
    }
    
    
    //Input letter from alphabet
    //Return numerical representation of that letter
    public int getLetterValue(char letter){
        return (int)this.alphabet.get(letter);
    }
    
    // Gives a value for delta
    //  A sequence of transitions of the form (p a q)
    // p current state
    // a input
    // q next state
    public void deltaPush(String set){
        set = set.replaceAll(" ", "");
        if(set.charAt(0)=='('){
            set =  set.substring(1);
        }
        if(set.charAt(set.length()-1)==')'){
            set = set.substring(0, set.length()-1);
        }
        
        String stateInput = set.substring(0,2);
        int nextState = getLetterValue(set.charAt(2));
        delta.put(stateInput, nextState);
    }
    
    public String evalString(String input){
        currentState=0;
        for(int i=0; i<input.length(); i++){
            String stateInput = Integer.toString(currentState)+ input.charAt(i);
            if(delta.containsKey(stateInput)){
                currentState = (int)delta.get(stateInput);
            }else{
                //if a deadend was reached return false
                //this allows partial language definitions
                return "Reject";
            }
        }
        if(final_states.contains(currentState)){
            return "Accept";
        } else {
            return "Reject";
        }
    }
    
    public void dump(){
        System.out.println("\n-----\n(1) number of States: "+num_states);
        System.out.print("(2) final States: ");
        for(int i=0; i<final_states.size(); i++){
            System.out.print(final_states.get(i)+ " ");
        }
        System.out.println("\n(3) alphabet:");
        alphabet.forEach((k,v)->System.out.print(k+", "));
        System.out.println("\n(4) Delta function");
        delta.forEach((k,v)->System.out.println("Initial State + Input : " + k + " Next State : " + v));
    }
}
