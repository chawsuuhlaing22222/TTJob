package com.me.job.tt.ui.activities

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.me.job.R
import com.me.job.tt.ui.adapters.CurrencyExchangeViewPagerAdapter
import com.me.job.tt.viewModels.FoundationInfoViewModel
import kotlinx.android.synthetic.main.activity_currency_exchange.*

class CurrencyExchangeActivity : BaseActivity() {

    private lateinit var mFoundationViewModel: FoundationInfoViewModel
    //private lateinit var currencyExchangeRequest: FoundationProfileRequest
    lateinit var currencyExchangeAdapter: CurrencyExchangeViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_exchange)

       // mFoundationViewModel = ViewModelProviders.of(this@CurrencyExchangeActivity)
        //    .get(FoundationInfoViewModel::class.java)


       // mFoundationViewModel.getCurrencyExchangeList()
        observe()

        setUpUi()

    }

    fun observe(){

        currencyExchangeAdapter=CurrencyExchangeViewPagerAdapter(this)
    }

    private fun setUpUi(){


        viewpager2_currency.adapter=currencyExchangeAdapter
        viewpager2_currency.orientation= ViewPager2.ORIENTATION_HORIZONTAL
        TabLayoutMediator(tab_bar_currency,viewpager2_currency){
                tab,position->

            tab.text=when(position){
                0->"OFFERS"
                1->"CURRENCIES"
                2->"FREE COINS"
                else -> "NULL"
            }

        }.attach()

        /*for(i in 0 until tab_bar_currency.tabCount){

              tab_bar_currency.getTabAt(i).set
        }*/

    }
}