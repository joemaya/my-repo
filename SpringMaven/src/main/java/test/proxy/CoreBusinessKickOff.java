package test.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoreBusinessKickOff {
    
    @Autowired
    CoreBusinessSubordinate subordinate;
 
    // getter/setters
    
    public void kickOff() {
        System.out.println("I do something big");
        subordinate.doSomethingBig();
        subordinate.doSomethingSmall(4);
    }
}