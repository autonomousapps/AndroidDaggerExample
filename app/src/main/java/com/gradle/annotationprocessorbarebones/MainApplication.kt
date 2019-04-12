package com.gradle.annotationprocessorbarebones

import android.app.Activity
import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Inject
import javax.inject.Singleton

class MainApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerMainApplicationComponent.builder()
            .app(this)
            .build()
            .inject(this)
    }

}

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ScreenBindingModule::class
    ]
)
interface MainApplicationComponent {
    fun inject(app: MainApplication)

    @Component.Builder
    interface Builder {
        fun build(): MainApplicationComponent
        @BindsInstance
        fun app(app: Context): Builder
    }
}

@Module
abstract class ScreenBindingModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity
}