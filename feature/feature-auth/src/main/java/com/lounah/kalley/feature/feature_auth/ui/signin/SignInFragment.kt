package com.lounah.kalley.feature.feature_auth.ui.signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.lounah.kalley.core.architecture.base.BaseFragment
import com.lounah.kalley.feature.feature_auth.R
import com.lounah.kalley.feature.feature_auth.ui.signin.di.SignInComponent
import kotlinx.android.synthetic.main.fragment_signin.*

internal class SignInFragment : BaseFragment(R.layout.fragment_signin) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val component = SignInComponent(
            changeSignInState = { { signIn.isEnabled = it } },
            showLoading = { progressBar.visibility = View.VISIBLE },
            showError = {
                Toast.makeText(context, R.string.auth_error, Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            },
            onAuthSucceed = {
                progressBar.visibility = View.GONE
                startMainActivity()
            },
            closeAuth = parentActivity::finish
        )
        val events = SignInEventsImpl(
            unbindEvent = unbindEvent,
            usernameChanged = username.textChanges(),
            passwordChanged = password.textChanges(),
            closeAuth = close.clicks(),
            signInClicked = signIn.clicks()
        )

        component.binder().bind(events)
    }

    private fun startMainActivity() {
        parentActivity
            .packageManager
            .getLaunchIntentForPackage("com.lounah.kalley")
            .also(::startActivity)
    }
}