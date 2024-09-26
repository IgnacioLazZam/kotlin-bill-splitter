package com.example.billsplitter

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
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

        btnDividir.setOnClickListener {
            // Convertimos el texto a float y hacemos la división
            val value1 = input1.text.toString().toFloatOrNull() ?: 0f
            val value2 = input2.text.toString().toFloatOrNull() ?: 1f // Evitamos división por 0 con 1 por defecto

            total.text = "Cantidad total: $value1";

            if (value2 != 0f) {
                val result = value1 / value2
                resultText.text = "Cada uno: $result€"
            } else {
                resultText.text = "Indique uno o más participantes."
            }
        }
    }
}