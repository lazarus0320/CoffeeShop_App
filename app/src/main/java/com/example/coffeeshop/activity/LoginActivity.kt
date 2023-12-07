package com.example.coffeeshop.activity

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshop.MainActivity
import com.example.coffeeshop.R
import com.example.coffeeshop.config.DBHelper
import com.example.coffeeshop.entity.User

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val userIdEditText: EditText = findViewById(R.id.editTextUserId)
        val passwordEditText: EditText = findViewById(R.id.editTextPassword)
        val loginButton: Button = findViewById(R.id.buttonLogin)

        loginButton.setOnClickListener {
            val userId = userIdEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (userId.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "사용자 이름과 비밀번호를 모두 입력 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dbHelper = DBHelper(this)
            val isAuthenticated = dbHelper.authenticateUser(userId, password)

            if (isAuthenticated) {
                val userName = dbHelper.getUsernameByUserId(userId)
                if (userName != null) {
                    loginUser(userName)

                    val intent = Intent(this, ItemTabActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            else if (userId == "admin" && password == "admin") {
                val intent = Intent(this, AdminOrderActivity::class.java)
                Toast.makeText(this, "로그인 성공: 관리자님 반갑습니다!!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }

            else {
                Toast.makeText(this, "잘못된 사용자 이름 또는 비밀번호입니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun loginUser(userName: String) {
        saveLoggedInUserName(userName)
        Toast.makeText(this, "로그인 성공: ${userName}님 반갑습니다!!", Toast.LENGTH_SHORT).show()

    }

    private fun saveLoggedInUserName(userName: String?) {
        val sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("loggedInUserName", userName)
        editor.apply()
    }
}