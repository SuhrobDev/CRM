package com.soul.crm.domain.sign_up.validates

import androidx.core.text.isDigitsOnly
import com.soul.crm.data.base.CheckResult
import javax.inject.Inject

class ValidatePhoneNumber @Inject constructor(){
    fun validatePhoneNumber(phoneNumber: String): CheckResult {
        if (phoneNumber.length < 7) {
            return CheckResult(error = "Number is not correct", check = false)
        }
        if (!phoneNumber.isDigitsOnly()) {
            return CheckResult(error = "Number is not correct", check = false)
        }
        return CheckResult(check = true)
    }
}