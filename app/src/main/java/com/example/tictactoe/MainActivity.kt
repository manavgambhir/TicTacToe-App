package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

private var Player1:String = ""
private var Player2:String = ""

class MainActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var board: Array<Array<Button>>
    var PLAYER=true
    var TURN_COUNT=0
    var boardStatus=Array(3){IntArray(3)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Player1= intent.getStringExtra("P1").toString()
        Player2= intent.getStringExtra("P2").toString()

        displayTV.text="$Player1's Turn"

        board= arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )

        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        resetBtn.setOnClickListener {
            PLAYER=true
            TURN_COUNT=0
            updateDisplay("$Player1's Turn")
            initializeBoardStatus()
        }
    }

    private fun initializeBoardStatus() {
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1
            }
        }
        for(i in board){
            for(button in i){
                button.isEnabled=true
                button.text=""
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.button1->{
                updateValue(row=0,col=0,player=PLAYER)
            }
            R.id.button2->{
                updateValue(row=0,col=1,player=PLAYER)
            }
            R.id.button3->{
                updateValue(row=0,col=2,player=PLAYER)
            }
            R.id.button4->{
                updateValue(row=1,col=0,player=PLAYER)
            }
            R.id.button5->{
                updateValue(row=1,col=1,player=PLAYER)
            }
            R.id.button6->{
                updateValue(row=1,col=2,player=PLAYER)
            }
            R.id.button7->{
                updateValue(row=2,col=0,player=PLAYER)
            }
            R.id.button8->{
                updateValue(row=2,col=1,player=PLAYER)
            }
            R.id.button9->{
                updateValue(row=2,col=2,player=PLAYER)
            }
        }
        TURN_COUNT++
        //Switching player
        PLAYER=!PLAYER
        if(PLAYER){
            updateDisplay("$Player1's Turn")
        }
        else{
            updateDisplay("$Player2's Turn")
        }
        if(TURN_COUNT==9){
            updateDisplay("Its a Draw")
        }
        checkWinner()
    }

    private fun checkWinner() {
        //Horizontal rows
        for(i in 0..2){
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][1]==boardStatus[i][2]){
                if(boardStatus[i][0]==1){
                    updateDisplay("$Player1 Wins!!")
                    disableBtn()
                    break;
                }
                else if(boardStatus[i][0]==0){
                    updateDisplay("$Player2 Wins!!")
                    disableBtn()
                    break;
                }
            }
        }

        //Vertical Cols
        for(i in 0..2){
            if(boardStatus[0][i]==boardStatus[1][i] && boardStatus[1][i]==boardStatus[2][i]){
                if(boardStatus[0][i]==1){
                    updateDisplay("$Player1 Wins!!")
                    disableBtn()
                    break
                }
                else if(boardStatus[0][i]==0){
                    updateDisplay("$Player2 Wins!!")
                    disableBtn()
                    break
                }
            }
        }

        //First Diagonal
        if(boardStatus[0][0]==boardStatus[1][1] && boardStatus[1][1]==boardStatus[2][2]){
            if(boardStatus[0][0]==1){
                disableBtn()
                updateDisplay("$Player1 Wins!!")
//                disableBtn()
            }
            else if(boardStatus[0][0]==0){
                disableBtn()
                updateDisplay("$Player2 Wins!!")
            }
        }
        //Second Diagonal
        if(boardStatus[0][2]==boardStatus[1][1] && boardStatus[1][1]==boardStatus[2][0]){
            if(boardStatus[0][2]==1){
                disableBtn()
                updateDisplay("$Player1 Wins!!")
//                disableBtn()
            }
            else if(boardStatus[0][2]==0){
                disableBtn()
                updateDisplay("$Player2 Wins!!")
//                disableBtn()
            }
        }

    }

    private fun disableBtn() {
        for(i in board){
            for(button in i){
                button.isEnabled=false
            }
        }
    }

    private fun updateDisplay(text:String) {
        displayTV.text=text
//        displayTV.textSize=30sp
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text=if(player) "X" else "O"
        val value=if(player) 1 else 0
        board[row][col].apply {
            isEnabled=false
            setText(text)
        }
        boardStatus[row][col]=value
    }
}