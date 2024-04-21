package com.example.kmecgrandhalay

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.google.android.material.navigation.NavigationView

class SecondActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var imageView: ImageView

    @SuppressLint("MissingInflatedId", "RestrictedApi")
    lateinit var toggle: ActionBarDrawerToggle

    @SuppressLint("RestrictedApi", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        imageView = findViewById(R.id.imgv)

        imageView.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)


        }


        navView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open_nav,R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState==null) {
            supportFragmentManager.beginTransaction()

        }





    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
         when(item.itemId){
             R.id.nav_home ->{
                 val sa = Intent(this, SecondActivity::class.java )
                 startActivity(sa)
                 finish()
             }
             R.id.nav_ctb -> supportFragmentManager.beginTransaction()
                 .replace(R.id.fragment_container,bookfragment()).commit()

             R.id.nav_log -> {
                 val sa = Intent(this, LoginActivity::class.java )
                 startActivity(sa)
                 finish()
             }

         }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun logout(){
        val sharedPreferences = getSharedPreferences("login_status",Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()

        editor.putBoolean("is_logged_in",false)
        editor.apply()

        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
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
