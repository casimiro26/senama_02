package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var txtResultado: TextView
    private var expresion = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtResultado = findViewById(R.id.txtResultado)

        // Configurar botones numéricos
        val botonesNumericos = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        botonesNumericos.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                agregarTexto((it as Button).text.toString())
            }
        }

        // Configurar operadores
        val botonesOperadores = mapOf(
            R.id.btnSumar to "+",
            R.id.btnRestar to "-",
            R.id.btnMultiplicar to "*",
            R.id.btnDividir to "/",
            R.id.btnPotencia to "^",
            R.id.btnPunto to "."
        )

        botonesOperadores.forEach { (id, operador) ->
            findViewById<Button>(id).setOnClickListener {
                agregarTexto(operador)
            }
        }

        // Paréntesis
        findViewById<Button>(R.id.btnParentesis).setOnClickListener {
            agregarTexto(if (expresion.count { it == '(' } > expresion.count { it == ')' }) ")" else "(")
        }

        // Borrar un carácter
        findViewById<Button>(R.id.btnBorrar).setOnClickListener {
            if (expresion.isNotEmpty()) {
                expresion = expresion.dropLast(1)
                txtResultado.text = expresion
            }
        }

        // Limpiar
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            expresion = ""
            txtResultado.text = "0"
        }

        // Calcular resultado
        findViewById<Button>(R.id.btnIgual).setOnClickListener {
            try {
                val resultado = ExpressionBuilder(expresion).build().evaluate()
                txtResultado.text = resultado.toString()
                expresion = resultado.toString()
            } catch (e: Exception) {
                txtResultado.text = "Error"
                expresion = ""
            }
        }
    }

    private fun agregarTexto(texto: String) {
        expresion += texto
        txtResultado.text = expresion
    }
}