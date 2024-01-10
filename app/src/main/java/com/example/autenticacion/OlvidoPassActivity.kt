package com.example.autenticacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.autenticacion.databinding.ActivityOlvidoPassBinding

class OlvidoPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityOlvidoPassBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}