package com.example.firebaselogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebaselogin.databinding.ActivityLoginBinding
import com.example.firebaselogin.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()

        val email=binding.loginEmail.text.toString()
        val password=binding.loginPassword.text.toString()

        if (email.isNotEmpty()&&password.isNotEmpty()){
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){ task->
                if(task.isSuccessful){
                    Toast.makeText(this,"Login Succesful",Toast.LENGTH_LONG).show()
                    val intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"Login Failed!",Toast.LENGTH_LONG).show()
                }


            }
        }else{
            Toast.makeText(this,"Please enter Email and Password",Toast.LENGTH_LONG).show()
        }

        binding.signuptext.setOnClickListener{
            val intent1=Intent(this,SignUpActivity::class.java)
            startActivity(intent1)
            finish()
        }

     }

}