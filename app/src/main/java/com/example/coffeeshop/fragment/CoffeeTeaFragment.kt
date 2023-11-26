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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트의 레이아웃을 인플레이트합니다.
        val view = inflater.inflate(R.layout.coffee_tea, container, false)

        // RecyclerView를 찾습니다.
        val recyclerView: RecyclerView = view.findViewById(R.id.coffeeTeaView)

        // RecyclerView에 어댑터를 연결합니다.
        recyclerView.adapter = CoffeeTeaAdapter(getCoffeeTeaData())

        // 그리드 레이아웃 매니저를 설정합니다.
        val layoutManager = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layoutManager

        return view
    }

    private fun getCoffeeTeaData(): List<CoffeeTeaItem> {

        return listOf(
            CoffeeTeaItem(R.drawable.americano, "아메리카노", 5000),
            CoffeeTeaItem(R.drawable.cafelatte, "카페라떼", 6000),
            CoffeeTeaItem(R.drawable.banillabean, "바닐라빈 라떼", 4500),
            CoffeeTeaItem(R.drawable.coldbrew, "콜드브루", 5500)
        )
    }
}