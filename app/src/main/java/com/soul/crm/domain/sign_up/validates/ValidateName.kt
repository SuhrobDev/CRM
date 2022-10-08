package com.soul.crm.domain.sign_up.validates

import com.soul.crm.data.base.CheckResult
import javax.inject.Inject

class ValidateName @Inject constructor(){
    fun validateName(name: String): CheckResult {
        if (name.length < 3) {
            return CheckResult(error = "Name length must be more than 3 length", check = false)
        }
        return CheckResult(check = true)
    }
}