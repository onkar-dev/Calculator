package com.example.calculator_kotlin

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.buttons.*
import kotlinx.android.synthetic.main.input.*
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class MainActivity : AppCompatActivity() {
    private var process: String = ""
    private var checkedBracket: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tvinput = findViewById<EditText>(R.id.tvInput)
        buttonc.setOnClickListener {
            tvinput.setText("")
            tvoutput.text = ""
        }
        buttonx.setOnClickListener {
            if (tvinput.getText().toString().isEmpty()) {
                Toast.makeText(this@MainActivity, "Text Field Is Empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                process = tvinput.text.toString()
                tvinput.setText(process.substring(0, process.length - 1))
            }
        }
        //Number

        button0.setOnClickListener(View.OnClickListener {
            process = tvinput.text.toString()
            tvinput.setText(process + "0").toString()
        })
        button1.setOnClickListener {
            process = tvinput.getText().toString()
            tvinput.setText(process + "1").toString()
        }
        button2.setOnClickListener {
            process = tvinput.text.toString()
            tvinput.setText(process + "2").toString()
        }
        button3.setOnClickListener {
            process = tvinput.text.toString()
            tvinput.setText(process + "3").toString()
        }
        button4.setOnClickListener {
            process = tvinput.text.toString()
            tvinput.setText(process + "4").toString()
        }
        button5.setOnClickListener {
            process = tvinput.text.toString()
            tvinput.setText(process + "5").toString()
        }
        button6.setOnClickListener {
            process = tvinput.text.toString()
            tvinput.setText(process + "6").toString()
        }
        button7.setOnClickListener {
            process = tvinput.text.toString()
            tvinput.setText(process + "7").toString()
        }
        button8.setOnClickListener {
            process = tvinput.text.toString()
            tvinput.setText(process + "8").toString()
        }
        button9.setOnClickListener {
            process = tvinput.text.toString()
            tvinput.setText(process + "9").toString()
        }

        //Operator
        buttonp.setOnClickListener {
            process = tvinput.text.toString()
            tvinput.setText(process + "%").toString()
        }
        buttonadd.setOnClickListener {
            process = tvinput.text.toString()
            tvinput.setText(process + "+").toString()
        }
        buttonmulti.setOnClickListener {
            process = tvinput.text.toString()
            tvinput.setText(process + "*").toString()
        }
        buttonsub.setOnClickListener {
            process = tvinput.text.toString()
            tvinput.setText(process + "-").toString()
        }
        buttondivide.setOnClickListener {
            process = tvinput.text.toString()
            tvinput.setText(process + "/").toString()
        }
        buttondot.setOnClickListener {
            process = tvinput.text.toString()
            tvinput.setText(process + ".").toString()
        }
        buttonbracket.setOnClickListener(View.OnClickListener {
            if (checkedBracket) {
                process = tvinput.text.toString()
                tvinput.setText(process + ")")
                checkedBracket = false
            } else {
                process = tvinput.text.toString()
                tvinput.setText(process + "(")
                checkedBracket = true
            }
        })
        buttonequal.setOnClickListener {
            process = tvinput.text.toString()
            process = process.replace("×".toRegex(), "*")
            process = process.replace("%".toRegex(), "/100")

            val rhino: Context = Context.enter()
            rhino.optimizationLevel = -1
            var finalResults = ""
            finalResults = try {
                val scriptable: Scriptable = rhino.initStandardObjects()
                rhino.evaluateString(scriptable, process, "javascript", 1, null).toString()
            } catch (e: Exception) {
                ""
            }
            if (tvinput.text.toString().isEmpty()) {
                Toast.makeText(this@MainActivity, "Text Field Is Empty", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                tvoutput.text = finalResults
            }
        }
    }
}
