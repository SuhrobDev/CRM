package com.soul.crm.domain.sign_up.validates

import com.soul.crm.data.base.CheckResult
import javax.inject.Inject

class ValidateLastName @Inject constructor(){
    fun validateLastname(lastname: String): CheckResult {
        if (lastname.length < 3) {
            return CheckResult(error = "Last name length must be more than 3 length", check = false)
        }
        return CheckResult(check = true)
    }
}