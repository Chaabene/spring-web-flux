package com.quick.start.subscriber;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;

public class BackPressureSubscriber<T> extends BaseSubscriber<T> {
	

	
	public void hookOnSubscribe(Subscription subscription) {
        //requested the first item on subscribe
		subscription.request(1);
    }

    public void hookOnNext(T value) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("======>>>>>>" + value);
        request(1);
    }
    
    
    
}
