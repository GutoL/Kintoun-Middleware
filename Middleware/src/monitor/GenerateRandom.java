/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.util.Random;

/**
 *
 * @author guto
 */
public class GenerateRandom {
    
    
    Random gen;
    int not;
    int limit;


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
