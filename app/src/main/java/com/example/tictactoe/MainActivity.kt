package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button:Button=findViewById(R.id.button)
        val tf:EditText=findViewById(R.id.editTextTextPersonName)
        val tf2:EditText=findViewById(R.id.editTextTextPersonName2)

        button.setOnClickListener {
            val intent=Intent(this,MainActivity2::class.java).apply {
                putExtra("person1",tf.text.toString())
                putExtra("person2",tf2.text.toString())
            }
            startActivity(intent)
        }


    }
}