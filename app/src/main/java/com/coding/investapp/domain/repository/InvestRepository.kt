package com.coding.investapp.domain.repository


import com.coding.investapp.data.remote.dto.HistoricalDto
import com.coding.investapp.data.remote.dto.OptionDto
import com.coding.investapp.data.remote.dto.PortfolioDto


interface InvestRepository {

    suspend fun getPortfoliosList(): List<PortfolioDto>
    suspend fun getOptionsList(): List<OptionDto>
    suspend fun getHistoricalsList(): List<HistoricalDto>

}