package com.soul.crm.domain.sign_up.validates

import com.soul.crm.data.base.CheckResult
import javax.inject.Inject

class ValidatePassword @Inject constructor(){
    fun validatePassword(password: String): CheckResult {
        if (password.length <= 6) {
            return CheckResult(error = "Minimum 6 Character Password", check = false)
        }
        if (!password.matches(".*[A-Z].*".toRegex())) {
            return CheckResult(error = "Must Contain 1 Upper-case Character", check = false)
        }
        if (!password.matches(".*[a-z].*".toRegex())) {
            return CheckResult(error = "Must Contain 1 Lower-case Character", check = false)
        }
        if (!password.matches(".*[@#\$%^_].*".toRegex())) {
            return CheckResult(error = "Must Contain 1 Special Character (@#\$%^_)", check = false)
        }
        return CheckResult(check = true)
    }
}