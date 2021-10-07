package com.me.job.tt.ui.fragments

import android.app.Dialog
import android.app.ProgressDialog
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment


open class BaseFragment : Fragment() {

    private var mProgressBar: ProgressDialog? = null
    protected var isLoading = false



    protected fun showLoading() {
        if (!isLoading) {

            mProgressBar = ProgressDialog.show(context, "", "loading...")
            if (mProgressBar != null) {
                mProgressBar!!.show()
            }
            isLoading = true
        }
    }
    protected fun showLoading(message: String) {
        if (!isLoading) {

            mProgressBar = ProgressDialog.show(context, "", message)
            if (mProgressBar != null) {
                mProgressBar!!.show()
            }
            isLoading = true
        }
    }

    protected fun hideLoading() {
        if (isLoading) {
            if (mProgressBar != null && mProgressBar!!.isShowing) {
                mProgressBar!!.dismiss()
            }
            isLoading = false
        }
    }

    protected fun showPromptDialog(errorMsg: String) {
        val alertDialog: AlertDialog? = this?.let {
            val builder = AlertDialog.Builder(context!!)
            builder.apply {
                setPositiveButton(
                    "OK"
                ) { dialog, id ->
                    dialog.dismiss()
                }
            }
            builder.create()
        }
        alertDialog!!.setMessage(errorMsg)
        alertDialog!!.show()
    }

}