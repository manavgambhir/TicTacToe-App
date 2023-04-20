package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btnStart.setOnClickListener{
            val name1=etP1.text.toString()
            val name2=etP2.text.toString()
            if(name1.isEmpty() || name2.isEmpty()){
                Toast.makeText(this,"Please Enter the Names of the Player(s)",Toast.LENGTH_SHORT).show()
            }
            else{
                val intent=Intent(this,MainActivity::class.java)
                intent.putExtra("P1",name1)
                intent.putExtra("P2",name2)
                startActivity(intent)
            }
        }
    }
}