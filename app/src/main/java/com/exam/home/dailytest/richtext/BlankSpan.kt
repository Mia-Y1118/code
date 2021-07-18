package com.exam.home.dailytest.richtext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.text.TextUtils
import android.text.style.ReplacementSpan
import android.util.Log
import android.widget.TextView
import com.exam.home.dailytest.Utils

/**
 * Created by Yangxy on 2019-11-21
 * description --  代替空格标签的span
 */
class BlankSpan(val context: Context,
                tvColor: Int,
                private val content: String,
                lineColor: Int
) : ReplacementSpan() {

    //横线上面可以显示index
    private var index: Int = 0

    fun setIndex(index: Int) {
        this.index = index
    }


    private var spanClickListener: BlankListener? = null
    private var linePaint: Paint? = null
    //自定义的线的宽度
    private var lineWidth: Int = Utils.dip2px(context, 60f).toInt()
    private var lineHeight: Float = Utils.dip2px(context, 1f)

    private var tvPaint: Paint? = null
    private var textSize: Float = Utils.dip2px(context, 16f)

    fun addClickListener(clickListener: BlankListener) {
        this.spanClickListener = clickListener
    }

    fun setClick(text: TextView, index: Int) {
        spanClickListener?.onBlankClick(text, index, this)
    }

    init {
        linePaint = Paint()
        linePaint?.color = lineColor
        linePaint?.isAntiAlias = true
        linePaint?.strokeWidth = lineHeight
        linePaint?.style = Paint.Style.FILL

        tvPaint = Paint()
        tvPaint?.color = tvColor
        tvPaint?.strokeWidth = lineHeight
        tvPaint?.isAntiAlias = true
        tvPaint?.textSize = textSize
    }

    override fun getSize(paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        return lineWidth
    }

    override fun draw(canvas: Canvas, text: CharSequence?, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        Log.d("yxy", "paint = $linePaint")

        //获取文字的底部
        val tvBottom = paint.fontMetrics.bottom
        //划线的底部
        val lineBottom = tvBottom + y

        val tvAboveLine = if (TextUtils.isEmpty(content)) index.toString() else content
        //drawText,截取text，显示不够用...表示
        val ellipsize = TextUtils.ellipsize(tvAboveLine, paint as TextPaint, lineWidth.toFloat(), TextUtils.TruncateAt.END)
        val tvStart = (lineWidth - tvAboveLine.length * textSize) / 2
        canvas.drawText(ellipsize, 0, ellipsize.length, x + tvStart, y.toFloat() - 2 * lineHeight, tvPaint ?: return)

        canvas.drawLine(x, lineBottom - 3 * lineHeight, x + lineWidth, lineBottom - 3 * lineHeight, linePaint ?: return)
    }


    interface BlankListener {
        /**
         * 点击某个空格
         */
        fun onBlankClick(text: TextView, index: Int, span: BlankSpan)
    }
}