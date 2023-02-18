package com.example.takeimageapp.images.presentation

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout

interface CustomTextInputLayout {

    fun changeErrorEnabled(value: Boolean)
    fun changeIsHintEnabled(value: Boolean)
    fun showError(errorMessage: String)
}

class BaseCustomTextInputLayout : TextInputLayout, CustomTextInputLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun changeErrorEnabled(value: Boolean) {
        isErrorEnabled = value
    }

    override fun changeIsHintEnabled(value: Boolean) {
        isHintEnabled = value
    }

    override fun showError(errorMessage: String) {
        error = errorMessage
    }
}