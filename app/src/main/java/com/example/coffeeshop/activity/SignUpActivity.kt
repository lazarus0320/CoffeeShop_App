package com.example.coffeeshop.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshop.MainActivity
import com.example.coffeeshop.R
import com.example.coffeeshop.config.DBHelper

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        val userIdEditText: EditText = findViewById(R.id.editTextId)
        val passwordEditText: EditText = findViewById(R.id.editTextPassword)
        val userNameEditText: EditText = findViewById(R.id.editTextName)
        val signUpButton: Button = findViewById(R.id.buttonSignUp)

        signUpButton.setOnClickListener {
            val userId= userIdEditText.text.toString()
            val password = passwordEditText.text.toString()
            val userName = userNameEditText.text.toString()

            // 여기에 회원가입 로직 구현 (예: 입력값 검증, 데이터베이스 저장 등)

            if (userName.isEmpty() || userId.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "모든 필드를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Proceed with further registration logic
            registerUser(userId, password, userName)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun registerUser(userId: String, password: String, userName: String) {
        try {
            // 회원가입 로직
            val dbHelper = DBHelper(this)

            if (dbHelper.userExists(userId)) {
                Toast.makeText(this, "이미 등록된 유저입니다.", Toast.LENGTH_SHORT).show()
                return
            }

            dbHelper.addUser(userId, password, userName)
            Toast.makeText(this, "환영합니다, ${userName}님!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "오류가 발생했습니다: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}