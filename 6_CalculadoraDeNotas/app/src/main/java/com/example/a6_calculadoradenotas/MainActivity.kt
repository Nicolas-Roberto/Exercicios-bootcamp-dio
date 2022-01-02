package com.example.a6_calculadoradenotas

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalcular = btnCalcular
        val resultado = resultado

        btnCalcular.setOnClickListener {
            val nota1 = Integer.parseInt(nota_1.text.toString())
            val nota2 = Integer.parseInt(nota_2.text.toString())
            val nota3 = Integer.parseInt(nota_3.text.toString())
            val nota4 = Integer.parseInt(nota_4.text.toString())
            val faltas = Integer.parseInt(faltas.text.toString())

            val notaFinal = (nota1 + nota2 + nota3 + nota4) / 4

            if(notaFinal >= 6 && faltas <= 25) {
                resultado.text = "Aluno aprovado \n Nota Final: $notaFinal \n Faltas: $faltas"
                resultado.setTextColor(Color.GREEN)
            } else {
                resultado.text = "Aluno reprovado \n Nota Final: $notaFinal \n Faltas: $faltas"
                resultado.setTextColor(Color.RED)
            }
        }
    }
}