package com.coding.investapp.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coding.investapp.domain.model.Historical

@Entity(tableName = "historical_table")
data class HistoricalDto(
    val benchmarkValue: Int,
    val date: String,
    @PrimaryKey val date_js: String,
    val smartWealthValue: Int
)

fun HistoricalDto.toHistorical(): Historical {
    return Historical(
        benchmarkValue = benchmarkValue,
        date = date,
        date_js = date_js,
        smartWealthValue = smartWealthValue
    )
}