package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
    }

    fun onDigit(view : View) {
        binding.tvInput.append((view as Button).text)
        lastNumeric = true
        // this function shows numbers in text view
    }

    fun onClear(view: View) {
        binding.tvInput.text = ""
        //this function clears the text view by CLR button
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            binding.tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
        // this function puts decimal point on right place
    }

    fun onEqual(view : View) {
        var prefixValue = ""
        if (lastNumeric) {
            val tv_value = binding.tvInput.text.toString()

            try {
                if (tv_value.startsWith("-")) {
                    prefixValue = "-"
                    tv_value.substring(1)
                }

                //minues
                if (tv_value.contains("-")) {
                    val splitTextView = tv_value.split("-")
                    var first = splitTextView[0]
                    var second = splitTextView[1]

                    if (!prefixValue.isEmpty()) {
                        first = prefixValue + first
                    }

                    binding.tvInput.text = removeZeroAfterDot(
                        (first.toDouble() - second.toDouble()).toString()
                    )

                }
                //multiply
                else if (tv_value.contains("*")) {
                    val splitTextView = tv_value.split("*")
                    var first = splitTextView[0]
                    var second = splitTextView[1]

                    if (!prefixValue.isEmpty()) {
                        first = prefixValue + first
                    }

                    binding.tvInput.text = removeZeroAfterDot(
                        (first.toDouble() * second.toDouble()).toString()
                    )

                }
                //addition
                else if (tv_value.contains("+")) {
                    val splitTextView = tv_value.split("+")
                    var first = splitTextView[0]
                    var second = splitTextView[1]

                    if (!prefixValue.isEmpty()) {
                        first = prefixValue + first
                    }

                    binding.tvInput.text = removeZeroAfterDot(
                        (first.toDouble() + second.toDouble()).toString())

                }
                //division
                else if (tv_value.contains("/")) {
                    val splitTextView = tv_value.split("/")
                    var first = splitTextView[0]
                    var second = splitTextView[1]


                    if (!prefixValue.isEmpty()) {
                        first = prefixValue + first
                    }

                    if (second == "0") {
                        binding.tvInput.setText("Wrong!")
                    } else {
                        binding.tvInput.text = removeZeroAfterDot(
                            (first.toDouble() / second.toDouble()).toString())
                    }

                }

            } catch (error : ArithmeticException) {
                error.printStackTrace()
            } // remember to search a bout these 2

        }
    }

    private fun removeZeroAfterDot(res:String) :String {
        var value = res
        if (res.contains(".0")) {
            value = res.substring(0,res.length-2)
        }
        return value
        //this functione remove zero after dot , because values are Double
    }

    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(binding.tvInput.text.toString())) {
            binding.tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false

        }
    }


    private fun isOperatorAdded(value : String) : Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/" ) || value.contains("*")
                    || value.contains("-") || value.contains("+")
        }
    }

}