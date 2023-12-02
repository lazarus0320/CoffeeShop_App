package com.example.coffeeshop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop.R
import com.example.coffeeshop.adapter.CoffeeTeaAdapter
import com.example.coffeeshop.entity.CoffeeTeaItem

class CoffeeTeaFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트의 레이아웃을 인플레이트합니다.
        val view = inflater.inflate(R.layout.coffee_tea, container, false)

        // RecyclerView를 찾습니다.
        recyclerView = view.findViewById(R.id.coffeeTeaView)

        // Arguments에서 drinkType 값을 읽어옴
        val drinkType = arguments?.getString("drinkType")

        // 새로운 객체가 생성된 경우에만 데이터 설정
        if (drinkType != null && childFragmentManager.findFragmentByTag(drinkType) == null) {
            when (drinkType) {
                "coffeeTea" -> {
                    recyclerView.adapter = CoffeeTeaAdapter(getCoffeeTeaData())
                }
                "smoothieJuice" -> {
                    recyclerView.adapter = CoffeeTeaAdapter(getSmoothieJuiceData())
                }
                "dessert" -> {
                    recyclerView.adapter = CoffeeTeaAdapter(getDessertData())
                }
            }
        }

        // 그리드 레이아웃 매니저를 설정합니다.
        val layoutManager = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layoutManager

        return view
    }

    private fun getCoffeeTeaData(): List<CoffeeTeaItem> {
        return listOf(
            CoffeeTeaItem(R.drawable.americano, "아메리카노", 4500),
            CoffeeTeaItem(R.drawable.cafelatte, "카페라떼", 6000),
            CoffeeTeaItem(R.drawable.banillabean, "바닐라빈 라떼", 5200),
            CoffeeTeaItem(R.drawable.coldbrew, "콜드브루", 5500),
            CoffeeTeaItem(R.drawable.cappuccino, "카푸치노", 5200),
            CoffeeTeaItem(R.drawable.macciato, "카라멜 마끼아또", 5500),
            CoffeeTeaItem(R.drawable.grapefruit_honey_black_tea, "자몽 허니 블랙티", 5600),
            CoffeeTeaItem(R.drawable.earl_grey_tea, "얼그레이 티", 5200)
        )
    }

    private fun getSmoothieJuiceData(): List<CoffeeTeaItem> {
        return listOf(
            CoffeeTeaItem(R.drawable.strawberry, "딸기 스무디", 6000),
            CoffeeTeaItem(R.drawable.mango, "망고 스무디", 6000),
            CoffeeTeaItem(R.drawable.yogurt, "요거트 스무디", 5800),
            CoffeeTeaItem(R.drawable.greenpuccino, "말차 프라푸치노", 6500),
            CoffeeTeaItem(R.drawable.java_chip_frappuccino, "자바칩 프라푸치노", 6500),
            CoffeeTeaItem(R.drawable.caramel_frappuccino, "카라멜 프라푸치노", 6500),
        )
    }

    private fun getDessertData(): List<CoffeeTeaItem> {
        return listOf(
            CoffeeTeaItem(R.drawable.tiramisu, "티라미수", 5200),
            CoffeeTeaItem(R.drawable.ganashu, "7레이어 가나슈", 5800),
            CoffeeTeaItem(R.drawable.raspberry, "라즈베리 쇼콜라", 6300),
            CoffeeTeaItem(R.drawable.creamcastella, "생크림 카스텔라", 5600),
            CoffeeTeaItem(R.drawable.basque_cheese_cake, "바스크 치즈 케이크", 5800),
            CoffeeTeaItem(R.drawable.parmesan_cheese_bagel, "파마산 치즈 베이글", 4500),
            CoffeeTeaItem(R.drawable.classic_scone, "클래식 스콘", 4000),
            CoffeeTeaItem(R.drawable.chocolate_walnut_bread, "초콜릿 월넛 브레드", 5500),
            CoffeeTeaItem(R.drawable.camembert_cheese_financier, "까망베르 치즈 휘낭시에", 4000),
            CoffeeTeaItem(R.drawable.dark_chocolate_cowboy_cookie, "다크 초콜릿 쿠키", 3500),
        )
    }

    companion object {
        fun newInstance(drinkType: String): CoffeeTeaFragment {
            // 탭을 누를 때마다 새로운 객체가 아닌 기존의 프래그먼트를 반환
            val existingFragment = CoffeeTeaFragment()
            val bundle = Bundle()
            bundle.putString("drinkType", drinkType)
            existingFragment.arguments = bundle
            return existingFragment
        }
    }
}
