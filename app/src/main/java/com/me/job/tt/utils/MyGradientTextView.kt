package com.me.job.tt.utils

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import android.widget.TextView
import com.me.job.R

class MyGradientTextView : androidx.appcompat.widget.AppCompatTextView {

   // var primaryColor:


    constructor(context:Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        if(changed){
            paint.shader=LinearGradient(0f,0f,width.toFloat(),height.toFloat(),
               resources.getColor(R.color.grey),resources.getColor(R.color.grey),Shader.TileMode.CLAMP)
        }
    }

}