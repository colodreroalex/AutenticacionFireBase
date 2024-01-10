package com.example.autenticacion


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.autenticacion.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth

class RegistroActivity : AppCompatActivity() {

    lateinit var binding : ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bRegister.setOnClickListener{
            register()
        }
    }

    private fun register(){
        binding.bRegister.setOnClickListener{
            if(binding.etEmail.text.isNotEmpty() && binding.etPass.text.isNotEmpty()){

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.etEmail.text.toString(),
                    binding.etPass.text.toString()
                )
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this, Bienvenida::class.java).apply {
                                putExtra("nombreUsuario", binding.etNombre.text.toString())
                            }
                            startActivity(intent)
                            Log.d("RegistroActivity", "Registro exitoso. Redirigiendo a MainActivity.")
                        }
                        else{
                            Toast.makeText(this, "Error en la autenticación", Toast.LENGTH_SHORT).show()
                            Log.e("RegistroActivity", "Error en la autenticación: ${it.exception}")
                        }
                    }
            }
            else{
                Toast.makeText(this, "Debe completar los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }






}