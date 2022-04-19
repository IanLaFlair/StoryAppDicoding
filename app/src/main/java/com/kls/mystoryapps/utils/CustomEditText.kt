package com.kls.mystoryapps.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.kls.mystoryapps.R

class CustomEditText: AppCompatEditText, View.OnTouchListener{

    private lateinit var warningImage: Drawable

    private fun init(){
        warningImage = ContextCompat.getDrawable(context, R.drawable.ic_baseline_warning_24) as Drawable

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length < 6){
                    showWarning()
                }else{
                    hideWarning()
                }
            }
            override fun afterTextChanged(s: Editable) {
                // Do nothing.
            }
        })
    }

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun showWarning() {
        setWarningDrawables(endOfTheText = warningImage)
    }
    private fun hideWarning() {
        setWarningDrawables()
    }

    private fun setWarningDrawables(startOfTheText: Drawable? = null, topOfTheText:Drawable? = null, endOfTheText:Drawable? = null, bottomOfTheText: Drawable? = null){
        // Sets the Drawables (if any) to appear to the left of,
        // above, to the right of, and below the text.
        setCompoundDrawablesWithIntrinsicBounds(startOfTheText, topOfTheText, endOfTheText, bottomOfTheText)
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null) {
            val clearButtonStart: Float
            val clearButtonEnd: Float
            var isClearButtonClicked = false
            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                clearButtonEnd = (warningImage.intrinsicWidth + paddingStart).toFloat()
                when {
                    event.x < clearButtonEnd -> isClearButtonClicked = true
                }
            } else {
                clearButtonStart = (width - paddingEnd - warningImage.intrinsicWidth).toFloat()
                when {
                    event.x > clearButtonStart -> isClearButtonClicked = true
                }
            }
            if (isClearButtonClicked) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        warningImage = ContextCompat.getDrawable(context, R.drawable.ic_baseline_warning_24) as Drawable
                        showWarning()
                        if (v != null) {
                            Toast.makeText(v.context, "asdas", Toast.LENGTH_SHORT).show()
                        }
                        return true
                    }
                    MotionEvent.ACTION_UP -> {

                        warningImage = ContextCompat.getDrawable(context, R.drawable.ic_baseline_warning_24) as Drawable
                        when {
                            text != null -> text?.clear()
                        }
                        hideWarning()
                        return true
                    }
                    else -> return false
                }
            }
        }
        return false
    }

}