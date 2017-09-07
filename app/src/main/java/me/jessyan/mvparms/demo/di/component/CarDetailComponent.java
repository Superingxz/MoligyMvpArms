package me.jessyan.mvparms.demo.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;
import me.jessyan.mvparms.demo.di.module.CarDetailModule;
import me.jessyan.mvparms.demo.mvp.ui.activity.CarDetailActivity;

@ActivityScope
@Component(modules = CarDetailModule.class, dependencies = AppComponent.class)
public interface CarDetailComponent {
    void inject(CarDetailActivity activity);
}