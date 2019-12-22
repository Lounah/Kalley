package com.lounah.kalley.feature.feature_auth.domain

internal abstract class RegexpValidator : (String) -> Boolean {
    abstract val regexp: Regex

    override fun invoke(value: String): Boolean = regexp.matches(value)
}

internal class PasswordValidator(
    override val regexp: Regex = "^(?=.*[a-z])(?=\\S+\$).{8,}\$".toRegex()
) : RegexpValidator()

internal class UsernameValidator(
    override val regexp: Regex = "^[a-zA-Z0-9._-]{5,}\$".toRegex()
) : RegexpValidator()