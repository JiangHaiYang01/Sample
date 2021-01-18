package com.allens.sample_proxy.staticProxy;

public class BuyProxy implements OnBuyListener{

    //代理对象
    public OnBuyListener listener;

    public BuyProxy(OnBuyListener listener){
        this.listener = listener;
    }

    @Override
    public void OnBuy() {
        System.out.println("购买之前");
        listener.OnBuy();
        System.out.println("购买之后");
    }
}
