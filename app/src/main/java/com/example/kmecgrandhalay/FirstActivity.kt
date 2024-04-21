package com.example.kmecgrandhalay

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class FirstActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val proceedbutton = findViewById<Button>(R.id.proceedbutton)

        proceedbutton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        if(SessionManager(this).isLoggedIn()){
            redirectToSecondActivity()
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
    private fun redirectToSecondActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()

    }

}