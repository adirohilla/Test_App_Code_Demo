package com.kv.pribizz.utils

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern

open class ValidateFun() {
    protected fun emailValidation(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    protected fun validateUPI(upi: String?): Boolean {
        val VALID_EMAIL_ADDRESS_REGEX: Pattern =
            Pattern.compile("^(.+)@(.+)$", Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(upi)
        return matcher.find()
    }
}

object FieldValidator : ValidateFun() {
    fun emailValidate(email: String?): Validator {

//        if (email == null || email.isNullOrBlank()) {
//            return Validator.Error("Please enter email")
//        }
        if (!email.isNullOrBlank() && !emailValidation(email)) {
            return Validator.Error("Please enter valid email")
        }
        return Validator.Success()
    }

    fun mobileValidate(mobile: String?): Validator {
        if (TextUtils.isEmpty(mobile)) {
            return Validator.Error("Please enter mobile")
        }
        if (mobile != null && mobile.length < 10) {
            return Validator.Error("Please enter valid mobile")
        }
        return Validator.Success()
    }

    fun nameValidate(name: String?): Validator {
        if (TextUtils.isEmpty(name)) {
            return Validator.Error("Please enter name")
        }
        if (name != null && name.length < 3) {
            return Validator.Error("Please enter valid name")
        }
        return Validator.Success()
    }

    fun upiValidate(upi: String?): Validator {
//        if (upi.isNullOrEmpty()) {
//            return Validator.Error("Please enter upi")
//        }
        if (!upi.isNullOrEmpty() && !validateUPI(upi)) {
            return Validator.Error("Please enter valid upi")
        }
        return Validator.Success()
    }

    fun bankNameValidate(name: String?): Validator {
        if (name.isNullOrEmpty()) {
            return Validator.Error("Please enter bank name")
        }

        return Validator.Success()
    }

    fun dobValidate(dob: String?): Validator {
        if (dob.isNullOrEmpty()) {
            return Validator.Error("Please select dob")
        }

        if (dob.length < 8) {
            return Validator.Error("Please select dob")
        }

        return Validator.Success()
    }

    fun acNoValidate(no: String?): Validator {
        if (no.isNullOrEmpty()) {
            return Validator.Error("Please enter account number")
        }
        if (no.length < 5) {
            return Validator.Error("Please enter valid account number")
        }

        return Validator.Success()
    }

    fun panValidate(pan: String?): Validator {
        if (pan.isNullOrEmpty()) {
            return Validator.Error("Please enter pancard number")
        }
        if (pan.length < 5) {
            return Validator.Error("Please enter valid pancard number")
        }

        return Validator.Success()
    }

    fun ifscValidate(ifsc: String?): Validator {
        if (ifsc.isNullOrEmpty()) {
            return Validator.Error("Please enter ifsc code")
        }
        if (ifsc.length < 5) {
            return Validator.Error("Please enter valid ifsc code")
        }

        return Validator.Success()
    }
}