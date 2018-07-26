package test.proxy;

import org.springframework.asm.commons.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class PojoEnhancer {

	public static PojoManager getInstance(){
	    Enhancer e = new Enhancer();
	    e.setSuperclass(PojoEnhancer.class);
//	    e.setCallback(new MethodInterceptor() {
//			
//			public Object intercept(Object target, Method method, 
//			           Object[] args, MethodProxy methodProxy)
//					throws Throwable {
//
//			       if (method.getName().startsWith("get")){ // or use  custom annotations
//			    	   PojoManager p = (PojoManager) target;
//			        if (!p.isInitialized()){
//			          p.init();
//			        }
//			       }
//			        return methodProxy.invokeSuper(target, args);
//			      
//			}
//		});

	    return (PojoManager) e.create();

	  }
}
