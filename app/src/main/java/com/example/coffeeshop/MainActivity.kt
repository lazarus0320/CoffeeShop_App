package com.example.coffeeshop
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.coffeeshop.databinding.ActivityMainBinding
import com.example.coffeeshop.fragment.CoffeeTeaFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private var previousTabIndex: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        var binding = ActivityMainBinding.inflate(layoutInflater) // 뷰 바인딩 초기화
        val view = binding.root // 뷰 바인딩의 루트 뷰 가져오기
        setContentView(view)

        // 이제 XML 레이아웃의 뷰에 바인딩된 변수를 사용할 수 있습니다.
        binding.rootLayout // XML에서 지정한 ID를 사용하여 레이아웃에 액세스

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
        binding.customToolbar.addView(titleTextView)

        binding.tabLayout.getTabAt(0)?.select()
        binding.tabLayout.getTabAt(0)?.view?.setBackgroundResource(android.R.color.white)
        replaceFragment(CoffeeTeaFragment.newInstance("coffeeTea"), R.anim.slide_in_right, R.anim.slide_out_left)
        // TabSelectedListener를 통해 선택된 탭의 배경색 변경
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val currentIndex = tab?.position ?: 0
                val enterAnimation = if (currentIndex > previousTabIndex) R.anim.slide_in_right else R.anim.slide_in_left
                val exitAnimation = if (currentIndex > previousTabIndex) R.anim.slide_out_left else R.anim.slide_out_right

                tab?.view?.setBackgroundResource(android.R.color.white)
                when (tab?.position) {
                    0 -> replaceFragment(CoffeeTeaFragment.newInstance("coffeeTea"), enterAnimation, exitAnimation)
                    1 -> replaceFragment(CoffeeTeaFragment.newInstance("smoothieJuice"), enterAnimation, exitAnimation)
                    2 -> replaceFragment(CoffeeTeaFragment.newInstance("dessert"), enterAnimation, exitAnimation)
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
    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment, enterAnim: Int, exitAnim: Int) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

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
}