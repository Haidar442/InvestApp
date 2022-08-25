package com.coding.investapp.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coding.investapp.domain.model.Option

@Entity(tableName = "option_table")
data class OptionDto(
    @PrimaryKey val id: String,
    val name: String,
    val risk_score: Int,
    val short_description: String
)

fun OptionDto.toOption(): Option {
    return Option(
        id = id,
        name = name,
        risk_score = risk_score,
        short_description = short_description
    )
}