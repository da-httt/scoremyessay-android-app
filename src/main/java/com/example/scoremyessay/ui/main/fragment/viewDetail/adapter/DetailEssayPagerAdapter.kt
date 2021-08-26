package com.example.scoremyessay.ui.main.fragment.viewDetail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.scoremyessay.ui.main.fragment.viewDetail.fragment.AIFixEssayFragment
import com.example.scoremyessay.ui.main.fragment.viewDetail.fragment.FeedBackFragment
import com.example.scoremyessay.ui.main.fragment.viewDetail.fragment.GradeAndJudgeFragment
import com.example.scoremyessay.ui.main.fragment.viewDetail.fragment.NormalFixEssayFragment

class DetailEssayPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GradeAndJudgeFragment()
            1 -> AIFixEssayFragment()
            2 -> NormalFixEssayFragment()
            3 -> FeedBackFragment()
            else -> throw IllegalArgumentException("Không tìm thấy fragment")
        }
    }
}