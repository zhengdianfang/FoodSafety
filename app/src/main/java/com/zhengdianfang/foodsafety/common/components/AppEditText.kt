package com.zhengdianfang.foodsafety.common.components

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.EditText
import android.widget.TextView
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.miracleframework.utils.Image


class AppEditText : EditText {

    private val labelTextView by lazy {
        val textView = TextView(context)
        textView.setTextColor(ContextCompat.getColor(context, R.color.black))
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        textView.gravity = gravity
        textView
    }

    private val labelPaint by lazy {

        val labelPaint = Paint()
        labelPaint.isAntiAlias = true
        labelPaint.textSize = resources.getDimension(R.dimen.middle)
        labelPaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        labelPaint
    }

    private var labelText = ""

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, R.attr.editTextStyle, R.style.AppTheme_EditText) {
        init(attrs)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleRes: Int) : super(context, attrs, R.attr.editTextStyle, defStyleRes) {
        init(attrs)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    private fun getLabelRect(): Rect {
        val rect = Rect()
        paint.getTextBounds(labelText, 0, labelText.count(), rect)
        return rect
    }
    private fun init(attrs: AttributeSet) {
        val styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.AppEditText, 0, R.style.AppTheme_EditText)
        val labelTextResId = styledAttributes.getResourceId(R.styleable.AppEditText_label_text, 0)
        labelText = resources.getString(labelTextResId)
        labelTextView.text = labelText
        val marginEnd = resources.getDimension(R.dimen.label_margin_end).toInt()
        labelTextView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
        val newPaddingLeft = getLabelRect().width() + marginEnd + paddingLeft
        setPadding(newPaddingLeft, 0, 0, 0)
        styledAttributes.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val rect = getLabelRect()
        val marginEnd = resources.getDimension(R.dimen.label_margin_end).toInt()
        val bitmapWidth = rect.width() + marginEnd + labelTextView.paddingLeft
        val labelViewBitmap = Image.loadBitmapFromView(labelTextView, bitmapWidth, rect.height())
        val top = (resources.getDimension(R.dimen.edit_text_height) - rect.height()) / 2 //+ resources.getDimension(R.dimen.label_margin_top_offset)
        canvas?.drawBitmap(labelViewBitmap, labelTextView.paddingLeft.toFloat(), top, labelPaint)
    }
}
