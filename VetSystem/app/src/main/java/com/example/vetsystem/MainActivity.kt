package com.example.vetsystem

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.DbHelper

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

        try{
            val dbHelper = DbHelper(this)
            dbHelper.deleteDatabase(this)
            val db = dbHelper.writableDatabase
            val mensaje: String = if (db != null) {
                "La base de datos se creó correctamente."
            } else {
                "Hubo un error al crear la base de datos."
            }
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
            Log.d("MiApp",mensaje)

            //INSERT DATA
            val correoTest = "1234"
            val passwordTest = "1234"
            val result = dbHelper.insertDataUsuarios(correoTest, passwordTest)

            if (result != -1L) {
                Log.d("MiApp","Datos insertados correctamente.")
            } else {
                Log.d("MiApp","Error al insertar datos.")
            }

            val loginButton = findViewById<Button>(R.id.login)
            val registroButton = findViewById<Button>(R.id.registro)
            loginButton.setOnClickListener {
                val inputCorreo = findViewById<EditText>(R.id.correo).text.toString()
                val inputPassword = findViewById<EditText>(R.id.password).text.toString()
                //val intent = Intent(this, ItemsActivity::class.java)
                if(dbHelper.login(inputCorreo,inputPassword,this)){
                    Toast.makeText(this,"Login exitoso", Toast.LENGTH_SHORT).show()
                    //startActivity(intent)
                }else{
                    Toast.makeText(this,"Correo o password incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
            registroButton.setOnClickListener {
                //val intent = Intent(this, RegistroActivity::class.java)
                Log.d("MiApp", "Antes de iniciar RegistroActivity")
                //startActivity(intent)
                Log.d("MiApp", "Después de iniciar RegistroActivity")

            }
        }catch (e:Exception){
            Log.d("MiApp",e.toString())
        }

    }
}