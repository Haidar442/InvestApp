package com.coding.investapp.data.repository

import com.coding.investapp.data.remote.InvestApi
import com.coding.investapp.data.remote.dto.HistoricalDto
import com.coding.investapp.data.remote.dto.OptionDto
import com.coding.investapp.data.remote.dto.PortfolioDto
import com.coding.investapp.domain.repository.InvestRepository
import javax.inject.Inject

class InvestRepositoryImpl @Inject constructor(
    private val api: InvestApi
) : InvestRepository {


    override suspend fun getPortfoliosList(): List<PortfolioDto> {
        return api.getPortfolios()
    }

    override suspend fun getOptionsList(): List<OptionDto> {
        return api.getOptions()
    }

    override suspend fun getHistoricalsList(): List<HistoricalDto> {
        return api.getHistoricals()
    }
}

