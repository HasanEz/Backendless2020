package com.example.backendless2020

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init Backendless

        Backendless.setUrl(Defaults.SERVER_URL)
        Backendless.initApp(applicationContext,Defaults.APPLICATION_ID,Defaults.API_KEY)

        //LOGIN BUTTON

        btnLogin.setOnClickListener(View.OnClickListener {

            val userName = etemail.text.toString()
            val password = etpass.text.toString()

          //  loginPb.setVis(View.VISIBLE)

            Backendless.UserService.login(userName,password,object :AsyncCallback<BackendlessUser?> {


                override fun handleFault(fault: BackendlessFault?) {

                    loginPb.isInvisible
                    Toast.makeText(applicationContext,"Login Failed : ${fault?.message}",Toast.LENGTH_SHORT).show()
                    Log.e("erroooooooorrrr",fault?.message)
                }

                override fun handleResponse(response: BackendlessUser?) {

                    loginPb.isInvisible
                    Toast.makeText(applicationContext,"Login OK ",Toast.LENGTH_SHORT).show()


                    startActivity(Intent(this@MainActivity,ProductsActivity::class.java))

                }
            })
        })
    }
}
