package com.kv.pribizz.model

data class NetworkMainModel(
    val records: ArrayList<NetworkModel>,
    val total_commission: String?
) {
}