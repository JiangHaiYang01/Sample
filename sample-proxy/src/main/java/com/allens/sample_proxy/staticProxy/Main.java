package com.allens.sample_proxy.staticProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
//        ScalpersImpl proxyStatic = new ScalpersImpl();
//        BuyProxy proxy = new BuyProxy(proxyStatic);
//        proxy.OnBuy();


        OnBuyListener proxy = (OnBuyListener) Proxy.newProxyInstance(
                OnBuyListener.class.getClassLoader(),
                new Class[] { OnBuyListener.class },
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("OnBuy")) {
                    System.out.println("Good morning, " + args);
                }
                return null;
            }
        });
        proxy.OnBuy();


    }
}
