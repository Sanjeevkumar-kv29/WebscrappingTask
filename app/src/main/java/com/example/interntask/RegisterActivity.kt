package com.example.interntask

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var enable =0

        val regexpasswordpattern =Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}\$")
        val regexemailpattern = Regex("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")

        regbtn.setOnClickListener {

            if (regFName.text.isNullOrEmpty())
            {
                regFName.setError("This field should not be Empty")
            }
            else if (regLName.text.isNullOrEmpty())
            {
                regLName.setError("This filed should not be Empty")
            }
            else if (regPhone.text.isNullOrEmpty())
            {
                regPhone.setError("This field should not be Empty")
            }
            else if (regEmail.text.isNullOrEmpty())
            {
                regEmail.setError("This field should not be Empty")
            }

            else if (!(regEmail.text.toString().matches(regexemailpattern))) // Regx for email
            {
                regEmail.setError("Invalid Email")
                emailalert.visibility = View.VISIBLE
            }



            else if (regPassword.text.isNullOrEmpty())
            {
                regPassword.setError("This field should not be Empty")
            }
            else if (!(regPassword.text.toString().matches(regexpasswordpattern))) // Regx for pass
            {
                regPassword.setError("")
                passalert.visibility = View.VISIBLE
            }
            else if (regCPassword.text.isNullOrEmpty())
            {
                regCPassword.setError("This field should not be Empty")
            }
            else if (regCPassword.text.toString()!=regPassword.text.toString())
            {
                regPassword.setError("Password not Matched with Confirm password")

            }
            else if (cnfchk.isChecked)
            {
                regbtn.setTextColor(Color.parseColor("#FFFFFF"))
                savedata(regFName.text.toString()+regLName.text.toString(),
                        regPhone.text.toString(),
                        regEmail.text.toString(),
                        regPassword.text.toString()
                        )
                Toast.makeText(this,"success", Toast.LENGTH_SHORT).show()
            }
            else{
                cnfchk.setError("Please Agree TNC")
            }




        }
        cnfchk.setOnClickListener {

            if (enable%2==0)
                regbtn.setTextColor(Color.parseColor("#FFFFFF"))
            else
                regbtn.setTextColor(Color.parseColor("#50FFFFFF"))

            enable +=1
        }

        reg2log.setOnClickListener { startActivity(Intent(this ,LoginActivity::class.java))
             finish()}

    }

    private fun savedata(RegName: String, RegPhoneNo: String, RegEmail: String, RegPassword: String) {

        val spf :SharedPreferences = getSharedPreferences("REGUSERDATA",0)

        spf.edit().putString("Name",RegName).apply()
        spf.edit().putString("Phone",RegPhoneNo).apply()
        spf.edit().putString("Email",RegEmail).apply()
        spf.edit().putString("Password",RegPassword).apply()
        Toast.makeText(this,"Registered Successfully", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,LoginActivity::class.java))
        finish()

    }


}