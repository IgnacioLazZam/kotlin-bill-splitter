package com.example.billsplitter

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val input1: TextInputEditText = findViewById(R.id.Cantidad)
        val input2: TextInputEditText = findViewById(R.id.Comensales)
        val resultText: TextView = findViewById(R.id.resultText)
        val btnDividir: Button = findViewById(R.id.btn_calcular)
        val total: TextView = findViewById(R.id.Total)
        val slider: Slider = findViewById(R.id.slider_val)
        val switch: MaterialSwitch = findViewById(R.id.switch_inp)


        switch.setOnClickListener(){
            if(switch.isChecked){
                slider.isEnabled = true;
            } else {
                slider.isEnabled = false
            }
        }
        btnDividir.setOnClickListener {

            var value1 = input1.text.toString().toFloat()
            var value2 = input2.text.toString().toFloat()
            var roundedTotal = value1;

            if (switch.isChecked){

                roundedTotal = when (slider.value){
                    1.0f -> value1 * 1.05f
                    2.0f -> value1 * 1.10f
                    3.0f -> value1 * 1.15f
                    4.0f -> value1 * 1.20f
                    5.0f -> value1 * 1.25f
                    else -> value1 * 1.0f
                }
            }


            total.text = "Cantidad total: $roundedTotal€";

            if (value2 != 0f) {
                var result = 0.0f;
                if (!switch.isChecked){
                    result = value1 / value2
                } else {
                    result = roundedTotal / value2
                }

                resultText.text = "Cada uno: $result€"
            } else {
                resultText.text = "Indique uno o más participantes."
            }
        }
    }
}