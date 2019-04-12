package com.gradle.annotationprocessorbarebones

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var thing: Thing

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.textView).text = thing.string
    }
}

@Module
object MainActivityModule {
    @Provides
    @JvmStatic
    fun provideThing() = Thing("Hola!")
}
