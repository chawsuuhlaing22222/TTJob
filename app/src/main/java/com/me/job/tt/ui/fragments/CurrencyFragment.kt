package com.me.job.tt.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.me.job.R
import com.me.job.tt.data.remote.response.CurrencyExchangeData
import com.me.job.tt.data.remote.response.StockItem
import com.me.job.tt.data.remote.response.StockList
import com.me.job.tt.ui.adapters.CurrencyAdapter
import com.me.job.tt.ui.adapters.SpinnerDropDownAdapter
import com.me.job.tt.ui.delegate.ChildGemDelegate
import com.me.job.tt.ui.delegate.CurrencyFragmentDelegate
import com.me.job.tt.ui.delegate.SpinnerDelegate
import com.me.job.tt.viewModels.FoundationInfoViewModel
import kotlinx.android.synthetic.main.fragment_currency.*
import kotlinx.android.synthetic.main.fragment_currency.view.*
import java.util.ArrayList





// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val CLIST = "curList"
private lateinit var mFoundationViewModel: FoundationInfoViewModel
private lateinit var currencyAdapter: CurrencyAdapter
private lateinit var spinnerAdapter: SpinnerDropDownAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [CurrencyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CurrencyFragment : BaseFragment(), CurrencyFragmentDelegate, ChildGemDelegate,SpinnerDelegate {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
     private  var clist:ArrayList<StockList> =arrayListOf()
    private var selected=0
    lateinit var mView:View
    private var posSelection=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        //view model
        mFoundationViewModel = ViewModelProviders.of(this@CurrencyFragment)
            .get(FoundationInfoViewModel::class.java)

        //get currency stock list
        mFoundationViewModel.getStockList(1,4)

        //get currency exchange list
        mFoundationViewModel.getCurrencyExchangeList()

        mView= inflater.inflate(R.layout.fragment_currency, container, false)

        currencyAdapter= CurrencyAdapter(this.requireContext(),this,this)

        mView.rv_gem_coin.adapter= currencyAdapter
        mView.rv_gem_coin.layoutManager=LinearLayoutManager(this.context,LinearLayoutManager.VERTICAL,false)

        observe()


       /* mView.tv_currency_value.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if(selected){

                Log.i("clist",clist?.size.toString())
                    for(a in clist!!.indices){

                        for(i in clist!![a].stockList.indices){

                            clist!![a].stockList[i].price==clist!![a].stockList[i].quantity*(tv_currency_value) as Int


                        }
                    }

                    currencyAdapter.clearData()
                    currencyAdapter.setNewDataList(clist!!)
                    currencyAdapter.notifyDataSetChanged()

                }
            }


        })


      */


        return mView

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CurrencyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CurrencyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)

                }
            }


    }

    fun observe(){

        mFoundationViewModel.mStockListResponse.observe(viewLifecycleOwner,{

            if(it.success){


                it.data?.let {

                    clist?.addAll(it.items as ArrayList<StockList>)


                    tv_currency_value.text=it.currentCurrency.exchageRate.toString()
                    tv_currency_code.text=it.currentCurrency.currencyCode
                    currencyAdapter.setNewData(it.items as MutableList<StockList>)





                }

            }else{
                Toast.makeText(this.context,it.error.toString(),Toast.LENGTH_SHORT).show()
                Log.e("currency",it.error.toString())
            }

        })



        mFoundationViewModel.mCurrencyExchangeListResponse.observe(viewLifecycleOwner,{ it ->

            if(it.success){
                it.data?.let {


                    spinnerAdapter= SpinnerDropDownAdapter(requireContext(),it)
                    mView.spinner.adapter= spinnerAdapter

                   /* val arrayAdapter: ArrayAdapter<CurrencyExchangeData> =  ArrayAdapter(activity,
                        com.me.job.R.layout.spinner_layout,it)

                    arrayAdapter.setDropDownViewResource(com.me.job.R.layout.spinner_drop_down);
                    mView.spinner.setAdapter(arrayAdapter);*/

                    //spinnerAdapter.setNewData(it)

                    for(d in it){
                        if(d.currencyCode==tv_currency_code.text){
                            posSelection=it.indexOf(d)
                        }
                    }
                    Toast.makeText(this.context,posSelection.toString(),Toast.LENGTH_SHORT).show()
                    mView.spinner.setSelection(posSelection)

                    //selected listener
                    mView.spinner.onItemSelectedListener=object :
                        AdapterView.OnItemSelectedListener {

                        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                            var c:CurrencyExchangeData= p0?.getItemAtPosition(p2) as CurrencyExchangeData
                            tv_currency_value.text=c.exchangeRate.toString()
                            tv_currency_code.text=c.currencyCode

                            selected++

                            if(selected>1){

                                Log.i("csize",clist?.size.toString())
                                for(a in clist!!.indices){

                                    for(i in clist!![a].stockList.indices){

                                        clist!![a].stockList[i].price= (clist!![a].stockList[i].quantity*c.exchangeRate).toInt()


                                    }
                                }

                                currencyAdapter.clearData()
                                currencyAdapter.setNewDataList(clist!!)
                                currencyAdapter.notifyDataSetChanged()
                            }



                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }


                    }



                }
            }else{
                Toast.makeText(this.context,it.error.toString(),Toast.LENGTH_SHORT).show()
               Log.e("currencyexchange",it.error.toString())
           }


        } )







    }

    override fun onClick(data: StockList) {
        Toast.makeText(this.context,data.stockList.size.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun childGemOnClik(data:StockItem) {
        Toast.makeText(this.context,data.quantity.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun spinnerClick() {
        TODO("Not yet implemented")
    }
}