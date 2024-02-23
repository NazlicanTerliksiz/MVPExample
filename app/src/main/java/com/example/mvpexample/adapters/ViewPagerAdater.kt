package com.example.mvpexample.adapters

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mvpexample.view.RickAndMorty.RickAndMortyFragment
import com.example.mvpexample.view.simpson.SimpsonFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0->{
                SimpsonFragment()
            }
            1->{
                RickAndMortyFragment()
            }
            else -> {
                throw Resources.NotFoundException("Position not found")
            }
        }
    }
}