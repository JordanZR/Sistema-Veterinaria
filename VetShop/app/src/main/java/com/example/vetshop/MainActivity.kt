package com.example.vetshop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val loginButton: Button = findViewById(R.id.login_button)

        loginButton.setOnClickListener {
            // Mostrar un mensaje de bienvenida
            // Snackbar.make(it, "Bienvenido", Snackbar.LENGTH_SHORT).show()
            // Crear un Intent para abrir la actividad HolaActivity
            val intent = Intent(this, Menu::class.java)
            // Iniciar la actividad HolaActivity
            startActivity(intent)
        }

    }
}