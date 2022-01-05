package com.example.a7_calculadoradeimc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Double.parseDouble

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculate = btCalculate
        val result = result

        btCalculate.setOnClickListener {
            val weight = parseDouble(weight.getText().toString())
            val height = parseDouble(height.getText().toString())
            var imc = weight/(height*height/10000)
            imc = String.format("%.2f",imc).toDouble()

            val bodyState = when {
                imc < 18.0 -> "Abaixo do peso"
                imc < 25.0 -> "Peso normal"
                imc < 30.0 -> "Sobre peso"
                imc < 35.0 -> "Obeso"
                else -> "IMC inválido"
            }

            result.text = "Peso: $weight Kg\nAltura: ${height/100} m\nIMC: $imc \n$bodyState"
        }
    }
}