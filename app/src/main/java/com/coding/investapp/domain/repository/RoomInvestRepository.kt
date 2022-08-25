package com.coding.investapp.domain.repository


import com.coding.investapp.data.remote.dto.HistoricalDto
import com.coding.investapp.data.remote.dto.OptionDto
import com.coding.investapp.data.remote.dto.PortfolioDto
import kotlinx.coroutines.flow.Flow


interface RoomInvestRepository {


    fun getPortfolios(): Flow<List<PortfolioDto>>

    suspend fun getPortfolioById(id: String): PortfolioDto?

    suspend fun insertPortfolio(portfolioDto: PortfolioDto)

    suspend fun deletePortfolio(portfolioDto: PortfolioDto)

    fun getOptions(): Flow<List<OptionDto>>

    suspend fun insertOption(optionDto: OptionDto)

    fun getHistoricals(): Flow<List<HistoricalDto>>

    suspend fun insertHistorical(historicalDto: HistoricalDto)
}