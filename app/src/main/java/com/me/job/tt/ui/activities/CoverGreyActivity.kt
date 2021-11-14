package com.me.job.tt.ui.activities

import android.R.attr
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.me.job.R
import android.text.BoringLayout

import android.R.attr.textSize

import android.graphics.Shader.TileMode

import android.graphics.LinearGradient

import android.graphics.Shader
import android.text.Html
import androidx.core.view.doOnPreDraw
import kotlinx.android.synthetic.main.activity_cover_grey.*


class CoverGreyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cover_grey)

var myText="Economic Crisic on Myanmar"

            tv_content.doOnPreDraw {

                if ( myText.length>33) {
                    tv_content.text =
                        Html.fromHtml(
                            myText.substring(
                                0,
                                30
                            ) + "..."
                        )
                    tv_content.maxLines = 1
                }else{
                    tv_content.text = myText
                }

        }

    }
}