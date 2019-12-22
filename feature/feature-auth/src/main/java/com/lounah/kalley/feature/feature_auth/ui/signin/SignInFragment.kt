package com.lounah.kalley.feature.feature_auth.ui.signin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.lounah.kalley.core.architecture.base.BaseFragment
import com.lounah.kalley.core.architecture.redux.bind
import com.lounah.kalley.core.di.Di
import com.lounah.kalley.feature.feature_auth.R
import com.lounah.kalley.feature.feature_auth.ui.signin.AuthAction.*
import com.lounah.kalley.feature.feature_auth.ui.signin.AuthEffect.*
import kotlinx.android.synthetic.main.fragment_signin.*

internal class SignInFragment : BaseFragment(R.layout.fragment_signin) {

    private val presenter: AuthPresenter = Di.get()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username.onTextChange { presenter.accept(OnUsernameChanged(it)) }
        password.onTextChange { presenter.accept(OnPasswordChanged(it)) }
        presenter.bind(this, onStateChanged = ::handleStateChange, onEffect = ::handleEffect)
    }

    private fun handleStateChange(state: AuthState) {

    }

    private fun handleEffect(effect: AuthEffect) {
        when (effect) {
            is ProceedAuthButtonStateChanged -> signIn.isEnabled = effect.enabled
        }
    }

    private fun EditText.onTextChange(listener: (String) -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
                    = listener(s?.toString().orEmpty())
        })
    }
}