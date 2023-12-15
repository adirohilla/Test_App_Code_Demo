package com.kv.pribizz.model.response

import com.kv.pribizz.model.BaseResponseModel

data class AdminAcDetailsResponseModel(
    val data: AcModel
) : BaseResponseModel()

/*
{"code":0,"error":false,"message":"Account Details","data":{"upi_number":"qwerty123456","bank_name":"Axis","bank_account_number":"12312332112","bank_ifsc_code":"12312311","bank_account_name":"Testing"}}
 */
data class AcModel(
    val upi_number: String,
    val bank_name: String,
    val bank_account_number: String,
    val bank_ifsc_code: String,
    val bank_account_name: String
)
