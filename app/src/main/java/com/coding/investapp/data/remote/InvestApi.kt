package com.coding.investapp.data.remote

import com.coding.investapp.data.remote.dto.HistoricalDto
import com.coding.investapp.data.remote.dto.OptionDto
import com.coding.investapp.data.remote.dto.PortfolioDto

import retrofit2.http.GET

interface InvestApi {


    @GET("portfolios")
    suspend fun getPortfolios(): List<PortfolioDto>

    @GET("options")
    suspend fun getOptions(): List<OptionDto>

    @GET("historical")
    suspend fun getHistoricals(): List<HistoricalDto>
}