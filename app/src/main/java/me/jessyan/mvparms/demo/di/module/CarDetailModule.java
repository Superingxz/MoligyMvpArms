package me.jessyan.mvparms.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import me.jessyan.mvparms.demo.mvp.contract.CarDetailContract;
import me.jessyan.mvparms.demo.mvp.model.CarDetailModel;


@Module
public class CarDetailModule {
    private CarDetailContract.View view;

    /**
     * 构建CarDetailModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public CarDetailModule(CarDetailContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CarDetailContract.View provideCarDetailView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    CarDetailContract.Model provideCarDetailModel(CarDetailModel model) {
        return model;
    }
}