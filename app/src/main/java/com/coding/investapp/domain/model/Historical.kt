package com.coding.investapp.domain.model

data class Historical(
    val benchmarkValue: Int,
    val date: String,
    val date_js: String,
    val smartWealthValue: Int
)