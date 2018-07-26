package test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	public static void main(String a[]){
        String confFile = "applicationContext.xml";
        ConfigurableApplicationContext context 
                                = new ClassPathXmlApplicationContext(confFile);
        MyDbConfig dbConfig = (MyDbConfig) context.getBean("dbConfig");
        System.out.println(dbConfig.toString());
        context.close();
    }
}
