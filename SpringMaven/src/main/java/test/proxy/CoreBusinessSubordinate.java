package test.proxy;

import org.springframework.stereotype.Component;

@Component("mySubordinate")
public class CoreBusinessSubordinate {
     public void doSomethingBig() {
         System.out.println("I did something small");
     }
     public void doSomethingSmall(int x){
         System.out.println("I also do something small but with an int");    
     }
 }