package com.jess.arms.rxbase;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * 用于管理单个presenter的RxBus的事件和Rxjava相关代码的生命周期处理
 * Created by xsf
 * on 2016.08.14:50
 */
public class RxManager {
    public RxBus mRxBus = RxBus.getInstance();
    //管理rxbus订阅
    private Map<String, Observable<?>> mObservables = new HashMap<>();
    /*管理Observables 和 Subscribers订阅*/
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    /**Com
     * RxBus注入监听
     * @param eventName
     * @param consumer
     */
    public <Disposable>void on(String eventName, Consumer<Disposable> consumer) {
        Observable<Disposable> mObservable = mRxBus.register(eventName);
        mObservables.put(eventName, mObservable);
        /*订阅管理*/
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwable -> throwable.printStackTrace());
    }

    /**
     * 单纯的Observables 和 Subscribers管理
     * @param m
     */
    public void add(Disposable m) {
        /*订阅管理*/
        compositeDisposable.add(m);
    }
    /**
     * 单个presenter生命周期结束，取消订阅和所有rxbus观察
     */
    public void clear() {
        compositeDisposable.clear();// 取消所有订阅
        for (Map.Entry<String, Observable<?>> entry : mObservables.entrySet()) {
            mRxBus.unregister(entry.getKey(), entry.getValue());// 移除rxbus观察
        }
    }
    //发送rxbus
    public void post(Object tag, Object content) {
        mRxBus.post(tag, content);
    }
}
