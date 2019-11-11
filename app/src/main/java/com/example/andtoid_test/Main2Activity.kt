package com.example.android_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button_1.setOnClickListener{
            Toast.makeText(this@Main2Activity, "you clicked button!", Toast.LENGTH_LONG).show()
        }

        button_2.setOnClickListener{
            finish()
        }
    }
}
