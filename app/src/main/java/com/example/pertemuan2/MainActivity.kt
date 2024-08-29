package com.example.pertemuan2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var tvInput: TextView
    private lateinit var tvOperator: TextView
    private var currentInput: String = ""
    private var operator: String = ""
    private var firstValue: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
        tvOperator = findViewById(R.id.tvOperator)

        val listener = View.OnClickListener { v ->
            val button = v as Button
            val buttonText = button.text.toString()

            when (buttonText) {
                "C" -> clear()
                "=" -> calculate()
                in "/*+-" -> setOperator(buttonText)
                else -> appendInput(buttonText)
            }
        }

        val buttonIDs = arrayOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnAdd,
            R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide, R.id.btnEquals, R.id.btnClear
        )

        buttonIDs.forEach { id ->
            findViewById<Button>(id).setOnClickListener(listener)
        }
    }

    private fun appendInput(value: String) {
        currentInput += value
        tvInput.text = currentInput
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            firstValue = currentInput.toDouble()
            operator = op
            currentInput = ""
            tvOperator.text = operator
        }
    }

    private fun calculate() {
        if (currentInput.isNotEmpty() && operator.isNotEmpty()) {
            val secondValue = currentInput.toDouble()
            val result = when (operator) {
                "+" -> firstValue + secondValue
                "-" -> firstValue - secondValue
                "*" -> firstValue * secondValue
                "/" -> if (secondValue != 0.0) firstValue / secondValue else 0.0
                else -> 0.0
            }

            // Menampilkan Toast dengan hasil perhitungan
            Toast.makeText(this, "Result: $result", Toast.LENGTH_SHORT).show()

            tvInput.text = result.toString()
            tvOperator.text = ""
            currentInput = result.toString()
            operator = ""
        }
    }


    private fun clear() {
        currentInput = ""
        operator = ""
        firstValue = 0.0
        tvInput.text = "0"
        tvOperator.text = ""
    }
}
