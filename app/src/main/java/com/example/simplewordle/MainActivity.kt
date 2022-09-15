package com.example.simplewordle

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.example.simplewordle.FourLetterWordList.FourLetterWordList.getRandomFourLetterWord

class MainActivity : AppCompatActivity() {
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wordToGuess: String = getRandomFourLetterWord()
        val button = findViewById<Button>(R.id.button)
        val guess1 = findViewById<TextView>(R.id.Guess1)
        val guess1Check = findViewById<TextView>(R.id.Guess1Check)
        val guess1Text = findViewById<TextView>(R.id.Guess1Text)
        val check1Text = findViewById<TextView>(R.id.Check1Text)
        val guess2 = findViewById<TextView>(R.id.Guess2)
        val guess2Check = findViewById<TextView>(R.id.Guess2Check)
        val guess2Text = findViewById<TextView>(R.id.Guess2Text)
        val check2Text = findViewById<TextView>(R.id.Check2Text)
        val guess3 = findViewById<TextView>(R.id.Guess3)
        val guess3Check = findViewById<TextView>(R.id.Guess3Check)
        val guess3Text = findViewById<TextView>(R.id.Guess3Text)
        val check3Text = findViewById<TextView>(R.id.Check3Text)
        val targetWord = findViewById<TextView>(R.id.TargetWord)
        val confettiImage = findViewById<ImageView>(R.id.ConfettiImage)

        guess1.visibility = View.INVISIBLE
        guess1Check.visibility = View.INVISIBLE
        guess1Text.visibility = View.INVISIBLE
        check1Text.visibility = View.INVISIBLE
        guess2.visibility = View.INVISIBLE
        guess2Check.visibility = View.INVISIBLE
        guess2Text.visibility = View.INVISIBLE
        check2Text.visibility = View.INVISIBLE
        guess3.visibility = View.INVISIBLE
        guess3Check.visibility = View.INVISIBLE
        guess3Text.visibility = View.INVISIBLE
        check3Text.visibility = View.INVISIBLE
        targetWord.visibility = View.INVISIBLE
        confettiImage.visibility = View.INVISIBLE

        targetWord.text = wordToGuess

        button.setOnClickListener {

            val simpleEditText = findViewById<EditText>(R.id.et_simple)
            var userGuess: String = simpleEditText.text.toString()

            if (!isAlphabet(userGuess)) {
                Toast.makeText(it.context, "You can only use letters!", Toast.LENGTH_SHORT).show()
            }
            else if (userGuess.length != 4) {
                Toast.makeText(it.context, "You need to enter a four-letter word!", Toast.LENGTH_SHORT).show()
            }
            else {
                simpleEditText.getText().clear()
                closeKeyBoard()
                counter++

                userGuess = userGuess.uppercase()
                var resultOfGuess: String = checkGuess(userGuess, wordToGuess)

                if (counter == 1) {
                    guess1Text.text = userGuess
                    check1Text.text = resultOfGuess
                    guess1.visibility = View.VISIBLE
                    guess1Check.visibility = View.VISIBLE
                    guess1Text.visibility = View.VISIBLE
                    check1Text.visibility = View.VISIBLE

                    if (resultOfGuess == "OOOO") {
                        confettiImage.visibility = View.VISIBLE
                        targetWord.visibility = View.VISIBLE
                        button.isEnabled = false
                        button.isClickable = false
                        button.setBackgroundColor(Color.parseColor("#b0b5b8"))
                        closeKeyBoard()
                    }
                }
                else if (counter == 2) {
                    guess2Text.text = userGuess
                    check2Text.text = resultOfGuess
                    guess2.visibility = View.VISIBLE
                    guess2Check.visibility = View.VISIBLE
                    guess2Text.visibility = View.VISIBLE
                    check2Text.visibility = View.VISIBLE

                    if (resultOfGuess == "OOOO") {
                        confettiImage.visibility = View.VISIBLE
                        targetWord.visibility = View.VISIBLE
                        button.isEnabled = false
                        button.isClickable = false
                        button.setBackgroundColor(Color.parseColor("#b0b5b8"))
                        closeKeyBoard()
                    }
                }
                else if (counter == 3) {
                    button.isEnabled = false
                    button.isClickable = false
                    button.setBackgroundColor(Color.parseColor("#b0b5b8"))
                    closeKeyBoard()

                    guess3Text.text = userGuess
                    check3Text.text = resultOfGuess
                    guess3.visibility = View.VISIBLE
                    guess3Check.visibility = View.VISIBLE
                    guess3Text.visibility = View.VISIBLE
                    check3Text.visibility = View.VISIBLE

                    targetWord.visibility = View.VISIBLE

                    if (resultOfGuess == "OOOO") {
                        confettiImage.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String, wordToGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

    private fun isAlphabet(string: String): Boolean {
        return string.all { it.isLetter() }
    }

    private fun closeKeyBoard() {
        val theView = this.currentFocus
        if (theView != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(theView.windowToken, 0)
        }
    }
}

