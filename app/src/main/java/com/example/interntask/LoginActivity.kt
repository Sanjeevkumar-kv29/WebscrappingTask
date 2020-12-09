package com.example.interntask

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        logEmail.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                //logPassword.setText("Text in EditText : "+s)


                if (s.isEmpty()){

                    loginbtn.setTextColor(Color.parseColor("#80FFFFFF"))
                    logEmail.setError("This field can't be empty")

                }

                if (s.isNotEmpty()){


                    logPassword.addTextChangedListener(object : TextWatcher {

                        override fun afterTextChanged(s: Editable) {}

                        override fun beforeTextChanged(s: CharSequence, start: Int,
                                                       count: Int, after: Int) {
                        }

                        override fun onTextChanged(s: CharSequence, start: Int,
                                                   before: Int, count: Int) {
                            //logPassword.setText("Text in EditText : "+s)


                            if (s.isEmpty()){

                                logPassword.setError("This field can't be empty")
                                loginbtn.setTextColor(Color.parseColor("#80FFFFFF"))

                            }
                            if (s.isNotEmpty()){

                                loginbtn.setTextColor(Color.parseColor("#FFFFFF"))

                            }
                        }
                    })

                }


            }
        })


        loginbtn.setOnClickListener {

            if (logEmail.text.isNullOrEmpty() and logPassword.text!!.isNullOrEmpty() ){

                logEmail.setError("This field can't be empty")
                logPassword.setError("This field can't be empty")
                Toast.makeText(this,"There are some Error",Toast.LENGTH_SHORT).show()
            }
            if (logEmail.text.isNullOrEmpty() or logPassword.text!!.isNullOrEmpty() ){

                Toast.makeText(this,"There are some Error",Toast.LENGTH_SHORT).show()
            }
            else{

                loginactivityfun(logEmail.text.toString(),logPassword.text.toString())

            }

        }



        log2reg.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

    }

    private fun loginactivityfun(email: String, pass: String) {

        val spf : SharedPreferences = getSharedPreferences("REGUSERDATA",0)
        val regemail = spf.getString("Email","")
        val regpass = spf.getString("Password","")

        Log.d("SPFEmail", regemail.toString())

        if (regemail.equals(email) and regpass.equals(pass)){
            Toast.makeText(this,"Login Successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        else{
            Toast.makeText(this,"Invalid Credential", Toast.LENGTH_SHORT).show()
        }
    }


}