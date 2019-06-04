package com.hondorchen.mvpapp.util;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxHelper {

    private RxHelper() {
        throw new AssertionError();
    }

    public static Observable<Integer> countcown(int initTime) {
        if (initTime < 0) {
            initTime = 0;
        }
        final int countTime = initTime;

        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .map((Long increase) ->countTime - increase.intValue())
                .take(countTime + 1)
                .subscribeOn(Schedulers.io())//io线程订阅（事件产生）
                .unsubscribeOn(Schedulers.io())//取消订阅放在io线程
                .observeOn(AndroidSchedulers.mainThread());//主线程消费事件

    }
}
