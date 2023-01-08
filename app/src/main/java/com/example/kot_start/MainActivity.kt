package com.example.kot_start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

val sygns = arrayOf('+', '-', '*', '/')
val brackets = arrayOf('(', ')')
val point = '.'

var havePoint = false
var bracketCombination = Bracket()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0.setOnClickListener { SetTextFields('0') }
        btn_1.setOnClickListener { SetTextFields('1') }
        btn_2.setOnClickListener { SetTextFields('2') }
        btn_3.setOnClickListener { SetTextFields('3') }
        btn_4.setOnClickListener { SetTextFields('4') }
        btn_5.setOnClickListener { SetTextFields('5') }
        btn_6.setOnClickListener { SetTextFields('6') }
        btn_7.setOnClickListener { SetTextFields('7') }
        btn_8.setOnClickListener { SetTextFields('8') }
        btn_9.setOnClickListener { SetTextFields('9') }
        point_btn.setOnClickListener { SetTextFields('.') }
        minus_btn.setOnClickListener { SetTextFields('-') }
        plus_btn.setOnClickListener { SetTextFields('+') }
        mult_btn.setOnClickListener { SetTextFields('*') }
        div_btn.setOnClickListener { SetTextFields('/') }
        openBracket_btn.setOnClickListener { SetTextFields('(') }
        closeBracket_btn.setOnClickListener { SetTextFields(')') }
        ac_btn.setOnClickListener {
            math_operation.text = ""
            result_text.text = ""
        }
        back_btn.setOnClickListener {
            val str = math_operation.text.toString()
            if (str.isNotEmpty())
                math_operation.text = str.substring(0, str.length - 1)
            result_text.text = ""
        }
        equaly_btn.setOnClickListener {

            try {
                val ex = ExpressionBuilder(math_operation.text.toString()).build()
                val result = ex.evaluate()
                val longRes = result.toLong()
                if (result == longRes.toDouble())
                    result_text.text = longRes.toString()
                else
                    result_text.text = result.toString()
            }

            catch (e: Exception) {
                result_text.text = "🛠🛠🛠"
                math_operation.text = ""
                havePoint = false
            }
        }
    }

    fun SetTextFields(symbol: Char) {
        if (result_text.text == "🛠🛠🛠")
            result_text.text = ""

        else if (result_text.text != "") {
            math_operation.text = result_text.text.toString()
            result_text.text = ""
        }

        val string = math_operation.text.toString()

        //проверка на правильную скобочную последовательность
        if (symbol in brackets) {
            if (!bracketCombination.RightBracketCombination(symbol)){
                result_text.text == "🛠🛠🛠"
                math_operation.text = ""
                havePoint = false
            }
            else{
                math_operation.append(symbol.toString())
            }
        }

        //постановка цифры
        else if (symbol.isDigit())
            math_operation.append(symbol.toString())

        //постановка знака
        else if (string.length >= 2 && symbol in sygns && string[string.length - 2] !in sygns && string[string.length - 1] != point) {
            math_operation.append(" $symbol ")
            havePoint = false
        }

        //постановка точки
        else if (string.length >= 1 && string[string.length - 1].isDigit() && symbol == point && !havePoint) {
            math_operation.append(symbol.toString())
            havePoint = true
        }
    }
}