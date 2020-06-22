/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dogfsm;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nijor
 */
public class DogFSM {
    private static int count=0;
    private static int  chase=0;
    public static abstract class State {
        protected Scanner input = new Scanner(System.in);
        protected Random random = new Random();
        public State() {}
        public abstract void enter();
        public abstract void exit();
        public abstract int updateAndCheckTransitions();
    }
    
    public static class Idle extends State {
        public Idle() {
            super();
                 }
        public void enter() {
            System.out.println("The dog is sitiing.");
        }
        public void exit() {            
            System.out.print("The dog is going to ");
        }
        public int updateAndCheckTransitions() {        
            int check,check2,check3 ;
            //if it more than 3 times nothings near or its night it goes to State 9- Bored state
                if(count==3){count=0;
                return 9; 
                  }
            System.out.print("Is it  night, yet ? (1 or 0) ");
            check = input.nextInt();
            if (check == 0) { //  Ask if anthing is near by if its still day
                System.out.println("No. its still day.");
                System.out.print("Is there anything near by ? (1 or 0) ");
                check2=input.nextInt();
                if(check2==1){ // Asks if there is an food or person near by 
                 check=0;
                 System.out.print("Is it food or person ? (1 or 0) ");
                check3=input.nextInt();
                // if its is food goes to State 2- eating state or if it is object goes to state 3- Identifying  person
                if(check3==1){
                    return 2;
                }
                else {return 3;}
                }
                count++;
                return 0;
            }
            //Goes to sleeping state if it is night
            else {
                return 1;
            } 
        }
    }
    
    
    public static class Sleeping extends State {
        public Sleeping() {
            super();
        }
        public void enter() {
            System.out.println(" sleep");
            System.out.println("The dog is sleeping.");
        }
        public void exit() {            
            System.out.println("The dog woke up");
        }
        public int updateAndCheckTransitions() {        
            int check;
            System.out.print("Is it  morning ? (1 or 0) ");
            check = input.nextInt();
            //Goes to  State 1 Idle if its morning else stays in the same state
            if (check == 0) {
                System.out.println("No.It is still night.");
                return 1;
            }
            else {
                return 0;
            } 
        }
    }
    public static class Eating extends State {
        public Eating() {
            super();
        }
        public void enter() {
            System.out.println(" eat");
            System.out.println("The dog is eating.");
        }
        public void exit() {            
            System.out.println("The dog is done eating");
        }
        public int updateAndCheckTransitions() {        
            int check;
            System.out.print("Is he done eating ? (1 or 0) ");
            check = input.nextInt();
            // Goes to State 4- Loving else stays in the same state-eating
            if (check == 0) {
                System.out.println("No.He is still eating.");
                return 2;
            }
            else {
                return 4;
            } 
        }
    }
    
    public static class Person extends State {
        public Person() {
            super();
        }
        public void enter() {
            System.out.println(" Sniff");
            System.out.println("The dog is sniff.");
        }
        public void exit() {            
            System.out.println("The dog is done sniffing");
        }
        public int updateAndCheckTransitions() {        
            int check,check2;
            System.out.print("Is it  owner ? (1 or 0) ");
            check = input.nextInt();
            // ask if it the owner or not if it is the owner asks if he ha ball to play with
            if (check == 1) {
                System.out.print("Does he has a ball ? (1 or 0) ");
                check2 = input.nextInt();
                // if he has ball goes to state 6 playig else goes to state 0- idle
                if(check2==1){
                    return 6;
                }
                else{
                return 0;
                }
            }
            //goes to stae 5 barking if not the owner
            else {
                System.out.println("Its an intruder");
                return 5;
            } 
        }
    }
    public static class Loving extends State {
        public Loving() {
            super();
        }
        public void enter() {
            System.out.println("The dog is loving the owner.");
        }
        public void exit() {            
            System.out.println("The dog is done loving");
        }
        public int updateAndCheckTransitions() {        
            int check;
            System.out.print("Are you done loving ? (1 or 0) ");
            check = input.nextInt();
            // Goes to Idle state if done loving else stays in the same state
            if (check == 0) {
                System.out.println("Still loving.");
                return 4;
            }
            else {
                return 0;
            } 
        }
    }
    
      public static class Barking extends State {
        public Barking() {
            super();
        }
        public void enter() {
              System.out.println("The dog is barking at an intruder.");
        }
        public void exit() {            
            System.out.print("The dog is ");
        }
        public int updateAndCheckTransitions() {        
            int check,check2;
            //Asks if the intruder is coming near if it is goes to chasing state 7
             System.out.print("Is the intruder coming near ? (1 or 0) ");
            check = input.nextInt();
            //if he is not coming near asks if he gone yet if not keeps barking 3 times then goes to chasing state
            if (check == 0) {
                 System.out.print("Is he gone ? (1 or 0) ");
                check2 = input.nextInt();
                if(check2==1){ 
                    return 0; 
                }
                else {
                    chase++;
                    if(chase==3){chase=0;return 7;} 
                    System.out.println("Still Barking");
                    return 5;
                }
              }
            else {
                return 7;
            } 
        }
    }
      
      public static class Playing extends State {
        public Playing() {
            super();
        }
        public void enter() {
              System.out.println(" is going to play");
              System.out.println("The dog is going to play.");
        }
        public void exit() {            
            System.out.println("The dog is done playing");
        }
        public int updateAndCheckTransitions() {        
            int check;
            System.out.print("Is the dog  done playing ? (1 or 0) ");
            check = input.nextInt();
            //Stays in same state till playing and goes to State 4 loving if he is done playing
            if (check == 0) {
                System.out.println("Still playing.");
                return 6;
            }
            else {
                return 4;
            } 
        }
    }
      
      public static class Chasing extends State {
        public Chasing() {
            super();
        }
        public void enter() {
              System.out.println("now going to chase   the intruder.");
              System.out.println("The dog is chasing the intruder.");
        }
        public void exit() {            
            System.out.println("The dog is done chasing");
        }
        public int updateAndCheckTransitions() {        
            int check,check2;
            // if the intruder has run away far he goes to idle state else he keeps  chasing and
            //if the dogs catches the intruder goes to state 8- biting state 
            System.out.print("Did the intruder run away  ? (1 or 0) ");
            check = input.nextInt();
            if (check == 0) {
                System.out.println("Chasing");
                System.out.print("Did the dog catch the intruder ?(1 or 0)");
                check2=input.nextInt();
                if(check2==1){
                    return 8;
                }
                else{
                    System.out.println("Still chasing.");
                return 7;
                }
            }
            else {
                return 0;
            } 
        }
    }
        public static class Biting extends State {
        public Biting() {
            super();
        }
        public void enter() {
              System.out.println("The dog caught the intruder.");
        }
        public void exit() {            
            System.out.println("The dog bit the intruder");
        }
        public int updateAndCheckTransitions() {        
            int check;
            System.out.print("Is the intruder  down ? (1 or 0) ");
            check = input.nextInt();
            //keeps biting until the intruder is down if the intruder is down then goes to idle state
            if (check == 0) {
                System.out.println("Still biting.");
                return 8;
            }
            else {
                return 0;
            } 
        }
    }
    
      public static class Bored extends State {
        public Bored() {
            super();
        }
        public void enter() {
               System.out.println("bored state");
              System.out.println("The dog is bored.");
        }
        public void exit() {            
            System.out.println("The dog is ");
        }
        public int updateAndCheckTransitions() {        
            int check;
            System.out.print("Does  dog want to  play or eat? (1 or 0) ");
            check = input.nextInt();
            //Dog chooses between playing or eating. If he wants to eat goes to state 2 else goes to state 6
            if (check == 1) {
                return 6;
            }
            else {
                return 2;
            } 
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                /* IMPORTANT! Change the 3 to the number of states you have ***/
        int numberOfStates = 10;
        State[] states = new State[numberOfStates];

        /* IMPORTANT! Modify this code to create each of your states ***/
        states[0] = new Idle();
        states[1] = new Sleeping();
        states[2] = new Eating();
        states[3] = new Person();
        states[4] = new Loving();
        states[5] = new Barking();
        states[6]= new Playing();
        states[7]= new Chasing();
        states[8]= new Biting();
        states[9]= new Bored();
        /**** End of code to modify ****/

        int currentState = 0;
        int nextState;  

        states[0].enter();
        while(true) {
            nextState = states[currentState].updateAndCheckTransitions();
            if (nextState != currentState) {
                states[currentState].exit();
                currentState = nextState;
                states[currentState].enter();
            }
        }

    }
    
}
