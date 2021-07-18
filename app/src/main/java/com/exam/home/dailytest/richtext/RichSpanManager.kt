package com.exam.home.dailytest.richtext

import android.R
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spannable
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import android.widget.Toast
import java.util.regex.Pattern


/**
 * Created by Yangxy on 2019-11-21
 * description -- 富文本的管理类
 */
class RichSpanManager(private var context: Context, private var textView: TextView) : BlankSpan.BlankListener {

    private val FILL_TAG = "&nbsp;<edit>&nbsp;"//替换成自己识别的blankTag
    private val FILL_TAG_NAME = "edit"
    private val blankTag = "__blank__placeholder" //后端的空格

    @TargetApi(Build.VERSION_CODES.N)
    fun handBlankTag(tv: String) {
        textView.movementMethod = method
        var content = tv
        if (content.contains(" img")) {
            content = content.replace(" img", "img")
        }

        //空格的tag用的img标签，后面带一个__blank__placeholder
        val blankTagList = findBlankTagList(content, "img")
        blankTagList.forEach {
            content = content.replace(it, FILL_TAG)
        }
        var index = 1
        val tagHandler = Html.TagHandler { opening, tag, output, xmlReader ->
            if (opening && tag.equals(FILL_TAG_NAME, true)) {
                val textColorRes = context.resources.getColor(R.color.holo_red_dark)
                val lineColorRes: Int = context.resources.getColor(R.color.black)
                val span = BlankSpan(context, textColorRes, "", lineColorRes)
                span.setIndex(index++)
                span.addClickListener(this)
                output.setSpan(span, output.length - 1, output.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }

        val spanned: Spanned? = if (Build.VERSION.SDK_INT >= 24) {
            Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT, FrescoImageGetter(context, textView), tagHandler)
        } else {
            Html.fromHtml(content, FrescoImageGetter(context, textView), tagHandler)
        }
        textView.text = spanned
    }

    /**
     * 过滤返回其中的空格标签列表
     */
    private fun findBlankTagList(content: String, imgTag: String): List<String> {
        val blankList = ArrayList<String>()
        if (content.isNullOrEmpty() || imgTag.isNullOrEmpty()) {
            return blankList
        }
        val regex = "<\\s*$imgTag\\s+([^>]+)\\s*>"
        val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(content)
        while (matcher.find()) {
            val item = matcher.group()
            if (!TextUtils.isEmpty(item) && item.contains(blankTag)) {
                blankList.add(item)
            }
        }
        return blankList
    }

    override fun onBlankClick(text: TextView, index: Int, span: BlankSpan) {
        Toast.makeText(context, textView.text, Toast.LENGTH_SHORT).show()
    }

    //TextView触摸事件-->Span点击事件
    private val method = object : LinkMovementMethod() {
        override fun onTouchEvent(widget: TextView, buffer: Spannable, event: MotionEvent): Boolean {
            val action = event.action

            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {

                val x = event.x.toInt()
                val y = event.y.toInt()
                val layout = widget.layout
                val line = layout.getLineForVertical(y)
                val off = layout.getOffsetForHorizontal(line, x.toFloat())
                //判断点击的是否为span
                val link = buffer.getSpans<BlankSpan>(off, off, BlankSpan::class.java)
                Log.d("yxy", "link = $link")
                if (link.isNotEmpty()) {
                    //Span的点击事件
                    if (action == MotionEvent.ACTION_UP) {
                        link[0].setClick(widget, 1)
                    }
                    return true
                }
            }
            return false
        }
    }
}