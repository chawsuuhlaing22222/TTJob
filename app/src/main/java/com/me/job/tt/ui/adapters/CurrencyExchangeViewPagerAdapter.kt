package com.me.job.tt.ui.adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.me.job.tt.data.remote.response.CurrencyExchangeData
import com.me.job.tt.ui.fragments.CurrencyFragment

class CurrencyExchangeViewPagerAdapter(var fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
       return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
              // FoundationPostFragment.newInstance(foundationId,"Posts")
                Fragment()
            }
            1->{
                CurrencyFragment.newInstance("123","123")

            }
            2->{
                //FoundationVideoFragment.newInstance(foundationId,"Videos", foundationProfileRequest)
                Fragment()
            }
            else -> Fragment()
        }
    }
}