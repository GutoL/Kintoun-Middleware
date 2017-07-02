/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.util.Random;

/**
 * Class that return a random number.
 * @author guto
 */
public class GenerateRandom {
    
    
    Random gen;
    int not;
    int limit;

    /**
     * Return a random number. Recieves two parameters: 'limit' is a limit range (from 0 to 'limit').
     * Return a number different of 'not'.
     * @param not 
     * @param limit 
     */
    public GenerateRandom(int not, int limit) {
        this.not = not;
        this.limit = limit;
        this.gen = new Random();
    }

     
    public int generate(){
        
        int result;
        
        while(true) {
        
            result = gen.nextInt(limit);
            if(result != not){
                
                return result;
            }
        }
    }
        
    
    
}
