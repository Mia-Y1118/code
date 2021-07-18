package com.exam.home.dailytest.richtext

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.exam.home.dailytest.R
import kotlinx.android.synthetic.main.rich_text_layout.view.*

/**
 * Created by Yangxy on 2019-11-21
 * description --
 */
class RichTextView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    def: Int = 0
) : FrameLayout(context, attr, def), IRichTV {

    init {
        LayoutInflater.from(context).inflate(R.layout.rich_text_layout, this, true)
    }

    override fun loadContent(content: String) {
        val manager = RichSpanManager(context, tv)
        manager.handBlankTag(content)
    }
}