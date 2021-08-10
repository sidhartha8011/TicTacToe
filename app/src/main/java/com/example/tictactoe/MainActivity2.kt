package com.example.tictactoe

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity2 : AppCompatActivity() {

    private var player1turn=true
    private var player1turns= mutableListOf<Int>()
    private var player2turns= mutableListOf<Int>()

    private var possiblewin:Array<List<Int>> = arrayOf(

        listOf(1,2,3),
        listOf(4,5,6),
        listOf(7,8,9),

        listOf(1,4,7),
        listOf(2,5,8),
        listOf(3,6,9),

        listOf(1,5,9),
        listOf(3,5,7),
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val tf:TextView=findViewById(R.id.textView7)
        val tf2:TextView=findViewById(R.id.textView6)
        tf2.text=intent.getStringExtra("person1")
        tf.text=intent.getStringExtra("person2")

        createBoard()

    }

    private fun createBoard()
    {
        var counter =1
        val board:LinearLayout=findViewById(R.id.board)

        val para1=LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            0
        )
        val para2=LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        for(i in 1..3){
            val linearLayout= LinearLayout(this)
            linearLayout.orientation=LinearLayout.HORIZONTAL
            linearLayout.layoutParams=para1
            para1.weight=1F

            for (j in 1..3){
                val button= Button(this)
                button.id=counter
                button.textSize=43F
                button.setTextColor(ContextCompat.getColor(this, R.color.design_default_color_error))
                button.layoutParams=para2
                para2.weight=1F
                button.setOnClickListener {
                    if(player1turn){
                        player1turns.add(button.id)
                        button.text="O"
                        button.isEnabled=false
                        if (checkWin(player1turns,player2turns))
                            showMessage(findViewById(R.id.textView6))
                        else
                        {
                            player1turn=false
                            toggelTurn(findViewById(R.id.textView5),findViewById(R.id.textView4))
                        }
                    }
                    else{
                        player2turns.add(button.id)
                        button.text="X"
                        button.isEnabled=false
                        if (checkWin(player2turns,player1turns))
                            showMessage(findViewById(R.id.textView7))
                        else
                        {
                            player1turn=true
                            toggelTurn(findViewById(R.id.textView4),findViewById(R.id.textView5))
                        }
                    }
                }
                linearLayout.addView(button)
                counter++
            }
            board.addView(linearLayout)
        }
    }
    private fun checkWin(check:List<Int>,full:List<Int>):Boolean{
        var win=false
        if (check.size>=3){
            run loop@{
                possiblewin.forEach {
                    if (check.containsAll(it)) {
                        win=true
                        return@loop
                    }
                }
            }
        }
        if(!win){
            if((check.size+full.size)==9)
            {
                val intent=Intent(this,MainActivity3::class.java).apply {
                    putExtra("Winner","OOPS!!! ITS A DRAW")
                }
                startActivity(intent)
            }
        }
    return win
    }
    private fun showMessage(person:TextView){
        val playername=person.text.toString()
        val intent=Intent(this,MainActivity3::class.java).apply {
            putExtra("Winner","Congrats!! $playername won")
        }
        startActivity(intent)

    }
    private fun toggelTurn(on:TextView,off:TextView){
        on.setTextColor(ContextCompat.getColor(this, R.color.purple_500)
        )
        on.setTypeface(null,Typeface.BOLD)
        off.setTextColor(ContextCompat.getColor(this, R.color.grey)
        )
        off.setTypeface(null,Typeface.NORMAL)
    }
}