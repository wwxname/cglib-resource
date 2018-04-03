package org.wwx.cglib.source.test;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackHelper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

/**
 * Hello world!
 *
 */
class Dao {
	public void say() {
		System.err.println("hello world");
	}
}

class DaoProxy implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("before method run...");
		Thread.sleep(100);
		Object result = proxy.invokeSuper(obj, args);
		Thread.sleep(100);
		System.out.println("after method run...");
		return result;
	}

}

class DaoProxyCallbackHelper extends CallbackHelper {

	public DaoProxyCallbackHelper(Class superclass, Class[] interfaces) {
		super(superclass, interfaces);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object getCallback(Method method) {
		// TODO Auto-generated method stub
		Boolean b = method.getName() == "say";
		if(b==true && method.getReturnType() == void.class){
			return new DaoProxy();
		}else{
			return NoOp.INSTANCE;
		}
	}

}

public class App {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Dao.class);
		 enhancer.setCallbackFilter(new DaoProxyCallbackHelper(Dao.class,new Class[0]));
		enhancer.setCallbacks(new DaoProxyCallbackHelper(Dao.class,new Class[0]).getCallbacks());
		Callback[] cs = new DaoProxyCallbackHelper(Dao.class,new Class[0]).getCallbacks();
		Dao sample = (Dao) enhancer.create();

		sample.say();
		sample.hashCode();

	}
}
