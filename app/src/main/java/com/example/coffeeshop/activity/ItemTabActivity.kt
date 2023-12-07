package com.example.coffeeshop.activity

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.coffeeshop.MainActivity
import com.example.coffeeshop.R
import com.example.coffeeshop.fragment.CartHistoryFragment
import com.example.coffeeshop.fragment.CoffeeTeaFragment
import com.google.android.material.tabs.TabLayout

class ItemTabActivity : AppCompatActivity() {
    private var previousTabIndex: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coffee_order_tab)

        supportActionBar?.hide()

        val customToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.custom_toolbar)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val btnCart = findViewById<ImageView>(R.id.btnCart)

        val btnLogout = findViewById<ImageView>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            logoutUser()
        }

        val titleTextView = TextView(this)
        titleTextView.text = "커피 주문"
        titleTextView.setTextColor(resources.getColor(R.color.white)) // 색상은 프로젝트 리소스에 맞게 변경
        titleTextView.textSize = 18f
        titleTextView.gravity = Gravity.CENTER

        val params = androidx.appcompat.widget.Toolbar.LayoutParams(
            androidx.appcompat.widget.Toolbar.LayoutParams.MATCH_PARENT,
            androidx.appcompat.widget.Toolbar.LayoutParams.WRAP_CONTENT
        )

        titleTextView.layoutParams = params
        customToolbar.addView(titleTextView)

        tabLayout.getTabAt(0)?.select()
        tabLayout.getTabAt(0)?.view?.setBackgroundResource(android.R.color.white)
        replaceFragment(
            CoffeeTeaFragment.newInstance("coffeeTea"),
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
        // TabSelectedListener를 통해 선택된 탭의 배경색 변경
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val currentIndex = tab?.position ?: 0
                val enterAnimation =
                    if (currentIndex > previousTabIndex) R.anim.slide_in_right else R.anim.slide_in_left
                val exitAnimation =
                    if (currentIndex > previousTabIndex) R.anim.slide_out_left else R.anim.slide_out_right

                tab?.view?.setBackgroundResource(android.R.color.white)
                when (tab?.position) {
                    0 -> replaceFragment(
                        CoffeeTeaFragment.newInstance("coffeeTea"),
                        enterAnimation,
                        exitAnimation
                    )

                    1 -> replaceFragment(
                        CoffeeTeaFragment.newInstance("smoothieJuice"),
                        enterAnimation,
                        exitAnimation
                    )

                    2 -> replaceFragment(
                        CoffeeTeaFragment.newInstance("dessert"),
                        enterAnimation,
                        exitAnimation
                    )
                }

                previousTabIndex = currentIndex
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.view?.setBackgroundResource(android.R.color.transparent)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // 탭이 다시 선택되었을 때의 동작
            }
        })

        btnCart.setOnClickListener {
            val fragment = CartHistoryFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.itemContainer, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
    private fun replaceFragment(fragment: Fragment, enterAnim: Int, exitAnim: Int) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.setCustomAnimations(
            enterAnim,
            exitAnim,
            R.anim.slide_in_left, // 들어올 때의 애니메이션 (역방향)
            R.anim.slide_out_right // 나갈 때의 애니메이션 (역방향)
        )

        transaction.replace(R.id.itemContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun logoutUser() {
        // 로그인 상태 정보 삭제
        val sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("loggedInUserName")
        editor.apply()

        Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}