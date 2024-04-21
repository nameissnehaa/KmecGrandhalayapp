package com.example.kmecgrandhalay

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.kmecgrandhalay.databinding.ActivityLoginBinding
import com.example.kmecgrandhalay.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()


        binding.button1.setOnClickListener {
            val email = binding.editText1.text.toString()
            val password = binding.editText2.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() ){
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this,SecondActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else
                    {
                        Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        binding.txtview1.setOnClickListener{
            val editText=Intent(this,MainActivity::class.java)
            startActivity(editText)
            finish()
        }

    }
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit")
        builder.setMessage("Are you sure you want to exit?")
        builder.setPositiveButton("Yes")
        { _: DialogInterface, _: Int -> super.onBackPressed()
        }
        builder.setNegativeButton("No")
        { dialog: DialogInterface, _: Int -> dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }


}
