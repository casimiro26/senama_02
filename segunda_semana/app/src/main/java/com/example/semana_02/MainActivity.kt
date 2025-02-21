package com.example.semana_02

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnerConversion = findViewById<Spinner>(R.id.spinner_conversion)
        val editInput = findViewById<EditText>(R.id.edit_input)
        val textResultado = findViewById<TextView>(R.id.text_resultado)
        val btnConvertir = findViewById<Button>(R.id.btn_convertir)
        val btnLimpiar = findViewById<Button>(R.id.btn_limpiar)
        val btnSalir = findViewById<Button>(R.id.btn_salir)

        // Opciones del spinner
        val opciones = arrayOf(
            "Celsius a Fahrenheit", "Fahrenheit a Celsius",
            "Metros a Pies", "Pies a Metros",
            "Soles a Dólares", "Soles a Euros"
        )

        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones)
        spinnerConversion.adapter = adaptador

        btnConvertir.setOnClickListener {
            val inputTexto = editInput.text.toString()
            if (inputTexto.isEmpty()) {
                Toast.makeText(this, "Ingrese un valor", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val valor = inputTexto.toDouble()
            val resultado = when (spinnerConversion.selectedItem.toString()) {
                "Celsius a Fahrenheit" -> (valor * 9 / 5) + 32
                "Fahrenheit a Celsius" -> (valor - 32) * 5 / 9
                "Metros a Pies" -> valor * 3.281
                "Pies a Metros" -> valor / 3.281
                "Soles a Dólares" -> valor / 3.68  // Suponiendo 1 USD = 3.8 PEN
                "Soles a Euros" -> valor / 3.86    // Suponiendo 1 EUR = 4.1 PEN
                else -> 0.0
            }

            textResultado.text = "Resultado: %.2f".format(resultado)
        }

        btnLimpiar.setOnClickListener {
            editInput.text.clear()
            textResultado.text = "Resultado: "
        }

        btnSalir.setOnClickListener {
            finish()
        }
    }
}
