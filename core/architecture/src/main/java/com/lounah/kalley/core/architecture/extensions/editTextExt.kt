package com.lounah.kalley.core.architecture.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

inline fun EditText.onTextChange(crossinline listener: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
                = listener(s?.toString().orEmpty())
    })
}