package com.lounah.kalley.feature.feature_auth.ui.signin

import android.os.Bundle
import android.view.View
import com.lounah.kalley.core.architecture.base.BaseFragment
import com.lounah.kalley.core.architecture.extensions.onTextChange
import com.lounah.kalley.core.architecture.redux.bind
import com.lounah.kalley.core.di.Di
import com.lounah.kalley.feature.feature_auth.R
import com.lounah.kalley.feature.feature_auth.ui.signin.AuthAction.*
import com.lounah.kalley.feature.feature_auth.ui.signin.AuthEffect.*
import kotlinx.android.synthetic.main.fragment_signin.*
import kotlin.LazyThreadSafetyMode.*

internal class SignInFragment : BaseFragment(R.layout.fragment_signin) {

    private val presenter: SignInPresenter by lazy(NONE) { Di.get<SignInPresenter>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindings()
    }

    private fun initBindings() {
        username.onTextChange { presenter.accept(OnUsernameChanged(it)) }
        password.onTextChange { presenter.accept(OnPasswordChanged(it)) }
        signIn.setOnClickListener { presenter.accept(OnSignInClicked()) }
        presenter.bind(this, onStateChanged = ::handleStateChange, onEffect = ::handleEffect)
    }

    private fun handleStateChange(state: AuthState) {

    }

    private fun handleEffect(effect: AuthEffect) {
        when (effect) {
            is ProceedAuthButtonStateChanged -> signIn.isEnabled = effect.enabled
        }
    }
}