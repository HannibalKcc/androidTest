package com.example.android_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, Main2Activity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent("com.example.activitytest.ACTION_START")
            startActivity(intent)
        }

        button3.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, Main3Activity::class.java)
            startActivity(intent)
        }

        button4.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, MapboxSymbolDemo::class.java)
            startActivity(intent)
        }
    }
}
