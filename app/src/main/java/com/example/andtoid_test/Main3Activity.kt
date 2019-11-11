package com.example.andtoid_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.flutter.facade.Flutter

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val flutterView = Flutter.createView(this, this.lifecycle, "defaultRoute") // 传入路由标识符
        setContentView(flutterView) // 用FlutterView替代Activity的ContentView
    }
}
