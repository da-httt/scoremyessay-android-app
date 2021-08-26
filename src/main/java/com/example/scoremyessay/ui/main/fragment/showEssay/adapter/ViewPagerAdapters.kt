package com.example.scoremyessay.ui.main.fragment.showEssay.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.scoremyessay.ui.main.fragment.showEssay.cancelEssay.CancelEssayFragment
import com.example.scoremyessay.ui.main.fragment.showEssay.doneEssay.DoneEssayFragment
import com.example.scoremyessay.ui.main.fragment.showEssay.onGoingEssay.OnGoingEssayFragment
import com.example.scoremyessay.ui.main.fragment.showEssay.waitingEssay.WaitingEssayFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> WaitingEssayFragment()
            1-> OnGoingEssayFragment()
            2-> DoneEssayFragment()
            3-> CancelEssayFragment()
            else -> throw IllegalArgumentException("Không tìm thấy fragment")
        }
    }
}
