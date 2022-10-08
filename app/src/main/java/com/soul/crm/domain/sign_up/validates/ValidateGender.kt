package com.soul.crm.domain.sign_up.validates

import com.soul.crm.data.base.CheckResult
import javax.inject.Inject

class ValidateGender @Inject constructor() {
    fun validateGender(gender: String): CheckResult {
        if (gender.length < 3) {
            return CheckResult(error = "Gender choose required", check = false)
        }
        return CheckResult(check = true)
    }
}