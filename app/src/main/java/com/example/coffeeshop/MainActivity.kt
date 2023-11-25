package com.example.coffeeshop
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        var binding = ActivityMainBinding.inflate(layoutInflater) // 뷰 바인딩 초기화
        val view = binding.root // 뷰 바인딩의 루트 뷰 가져오기
        setContentView(view)

        // 이제 XML 레이아웃의 뷰에 바인딩된 변수를 사용할 수 있습니다.
        binding.rootLayout // XML에서 지정한 ID를 사용하여 레이아웃에 액세스
    }
}