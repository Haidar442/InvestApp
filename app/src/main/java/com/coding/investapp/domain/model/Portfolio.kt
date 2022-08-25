package com.coding.investapp.domain.model

data class Portfolio(
    val balance: Int=0,
    val created_at: String="",
    val id: String="",
    val investment_type: String="",
    val latest_daily_earning: Int=0,
    val modified_risk_score: Int=0,
    val name: String="",
    val total_earnings: Int=0
)