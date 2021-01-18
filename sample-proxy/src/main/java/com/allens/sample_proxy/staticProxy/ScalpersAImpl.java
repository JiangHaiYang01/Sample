package com.allens.sample_proxy.staticProxy;

import com.allens.sample_proxy.staticProxy.OnBuyListener;

/**
 * 黄牛
 */
public class ScalpersAImpl implements OnBuyListener {
    @Override
    public void OnBuy() {
        System.out.println("购买苹果12");
    }
}
