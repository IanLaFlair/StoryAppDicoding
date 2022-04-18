package com.kls.mystoryapps.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.kls.mystoryapps.R

class CustomEditText: AppCompatEditText{

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
                } else if (s.length == 0 || s.length>6) hideWarning()
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

    private fun setWarningDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText:Drawable? = null,
        endOfTheText:Drawable? = null,
        bottomOfTheText: Drawable? = null
    ){
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }



}