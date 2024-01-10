package com.example.autenticacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.example.autenticacion.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    public lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bLogin.setOnClickListener{
            login()
        }

        binding.bReguster.setOnClickListener{
            startActivity(Intent(this, RegistroActivity::class.java))
        }

    }

    private fun login(){
        if (binding.tbEmail.text.isNotEmpty() && binding.tbPasss.text.isNotEmpty()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.tbEmail.text.toString(),
                binding.tbPasss.text.toString()
            )
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        val intent = Intent(this, Bienvenida::class.java)
                        startActivity(intent)
                        Log.d("MainActivity", "Inicio de sesión exitoso. Redirigiendo a Bienvenida.")
                    }
                    else{
                        Toast.makeText(this, "Error en la autenticación", Toast.LENGTH_SHORT).show()
                        Log.e("MainActivity", "Error en la autenticación: ${it.exception}")
                    }
                }
        }
        else{
            Toast.makeText(this, "Debe completar los campos", Toast.LENGTH_SHORT).show()
        }
    }
}