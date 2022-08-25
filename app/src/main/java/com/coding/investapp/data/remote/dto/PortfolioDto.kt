package com.coding.investapp.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coding.investapp.domain.model.Portfolio

@Entity(tableName = "portfolio_table")
data class PortfolioDto(
    val balance: Int,
    val created_at: String,
    @PrimaryKey val id: String,
    val investment_type: String,
    val latest_daily_earning: Int,
    val modified_risk_score: Int,
    val name: String,
    val total_earnings: Int
)

fun PortfolioDto.toPortfolio(): Portfolio {
    return Portfolio(
        balance = balance,
        created_at = created_at,
        id = id,
        investment_type = investment_type,
        latest_daily_earning = latest_daily_earning,
        modified_risk_score = modified_risk_score,
        name = name,
        total_earnings = total_earnings

    )
}