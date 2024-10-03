package com.example.test2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPref = getSharedPreferences("com.example.test2", MODE_PRIVATE)
        val editor = sharedPref.edit()


        binding.btnLogin.setOnClickListener {

            val etName = findViewById<EditText>(R.id.editTextName)
            val etPassword = findViewById<EditText>(R.id.editTextPassword)

            if (etName.text != null || etPassword.text != null) {

                if (binding.checkBox.isChecked) {

                    editor.putString(binding.checkBox.toString(), "true")
                    editor.apply()
                    editor.putString("name", binding.editTextName.text.toString())
                    editor.commit()
                    editor.putString("password", binding.editTextPassword.text.toString())
                    editor.commit()


                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                    intent.putExtra("name", binding.editTextName.text.toString())
                    intent.putExtra("password", binding.editTextPassword.text.toString())
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@MainActivity, "Login with save data", Toast.LENGTH_SHORT).show()

                } else {

                    editor.putString(binding.checkBox.toString(), "false")
                    editor.apply()
                    editor.putString("", binding.editTextName.text.toString())
                    editor.commit()
                    editor.putString("", binding.editTextPassword.text.toString())
                    editor.commit()

                    val intent = Intent(this@MainActivity, MainActivity3::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@MainActivity, "Login without save data", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@MainActivity, "Enter data!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClear.setOnClickListener {
            editor.clear()
            editor.apply()
            binding.editTextName.text.clear()
            binding.editTextPassword.text.clear()
            Toast.makeText(this@MainActivity, "Cleared!", Toast.LENGTH_SHORT).show()


        }

    }
}