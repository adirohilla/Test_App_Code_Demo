package com.kv.pribizz.model

data class UserModel(
    val userid: String,
    val name: String,
    val username: String?,
    var token: String?,
    val mobile: String,
    val email: String?,
    var kyc_verified: String?,
    val user_type: String,
    var balance: String?,
    var account_name: String?,
    var bank_name: String?,
    var account_no: String?,
    var ifsc_code: String?,
    var profile_image: String?,
    var upi_id: String?,
    var pan_number: String?,
    var pan_name: String?,
    var dob: String?,
    val referral_code: String?,
    val total_investment: String?,
    val total_return: String?,
    val current_plan: ArrayList<CurrentPlanModel>?
) {
    fun kycRejected(): Boolean {
        return kyc_verified.equals("R", true) || kyc_verified.equals("N", true)
    }

    fun getPlan(): CurrentPlanModel? {
        return if (!current_plan.isNullOrEmpty()) current_plan.get(current_plan.size-1) else null
    }
    /*
    {
  "code": 0,
  "error": false,
  "message": "Login user data",
  "data": {
    "userid": "15",
    "name": "Test",
    "username": "7597843161",
    "token": "Basic NzU5Nzg0MzE2MTplMTBhZGMzOTQ5YmE1OWFiYmU1NmUwNTdmMjBmODgzZQ==",
    "mobile": "7597843161",
    "email": null,
    "kyc_verified": "N",
    "ringer": "1",
    "vibration": "1",
    "user_type": "USER",
    "balance": "0.00",
    "account_name": null,
    "bank_name": null,
    "account_no": null,
    "ifsc_code": null,
    "profile_image": null,
    "upi_id": null
  }
}  */
}