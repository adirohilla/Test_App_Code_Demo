package com.kv.pribizz.model.response

import com.kv.pribizz.model.BaseResponseModel

data class ContactDetailsResponseModel(
    val data: ContactDetailsModel
) : BaseResponseModel() {
}

data class ContactDetailsModel(
    val EMAIL: String,
    val upi_number: String,
    val bank_name: String,
    val bank_account_number: String,
    val bank_ifsc_code: String,
    val bank_account_name: String,
    val whatsapp_number: String
)
