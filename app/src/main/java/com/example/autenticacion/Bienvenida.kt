package com.example.autenticacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.autenticacion.databinding.ActivityBienvenidaBinding
import com.example.autenticacion.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Bienvenida : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityBienvenidaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Bienvenida"

        val baseDatos = FirebaseFirestore.getInstance()


        //Añadir un nuevo coche conociendo su ID que es la matricula
        binding.bSave.setOnClickListener{
            if(binding.tbBrand.text.isNotEmpty() && binding.tbModel.text.isNotEmpty() &&
                binding.tbLicensePlate.text.isNotEmpty() && binding.tbColor.text.isNotEmpty()){
                baseDatos.collection("coches").document(binding.tbLicensePlate.text.toString())
                    .set(mapOf(
                        "color" to binding.tbColor.text.toString(),
                        "marca" to binding.tbBrand.text.toString(),
                        "modelo" to binding.tbModel.text.toString()
                    ))
            }
            else{
                Toast.makeText(this, "Algun campo esta vacio", Toast.LENGTH_SHORT).show()
            }
        }

        //Eliminar un registro sabiendo su ID (matricula)
        binding.bDelete.setOnClickListener{
            baseDatos.collection("coches")
                .document(binding.tbLicensePlate.text.toString())
                .delete()
        }

        //Actualizar un registro sabiendo su ID (matricula)
        binding.bEdit.setOnClickListener{
            if(binding.tbBrand.text.isNotEmpty() && binding.tbModel.text.isNotEmpty() &&
                binding.tbLicensePlate.text.isNotEmpty() && binding.tbColor.text.isNotEmpty()){
                baseDatos.collection("coches").document(binding.tbLicensePlate.text.toString())
                    .update(mapOf(
                        "color" to binding.tbColor.text.toString(),
                        "marca" to binding.tbBrand.text.toString(),
                        "modelo" to binding.tbModel.text.toString()
                    ))
            }
            else{
                Toast.makeText(this, "Algun campo esta vacio", Toast.LENGTH_SHORT).show()
            }
        }

        val bundle = intent.extras
        val nombre = bundle?.getString("nombreUsuario")
        binding.tbWelcome.text = "Bienvenido, $nombre"

        binding.bLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, MainActivity::class.java))
        }

    }


}