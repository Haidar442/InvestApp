package com.coding.investapp.data.repository

import com.coding.investapp.data.local.HistoricalDao
import com.coding.investapp.data.local.OptionDao
import com.coding.investapp.data.local.PortfolioDao
import com.coding.investapp.data.remote.dto.HistoricalDto
import com.coding.investapp.data.remote.dto.OptionDto
import com.coding.investapp.data.remote.dto.PortfolioDto
import com.coding.investapp.domain.repository.RoomInvestRepository
import kotlinx.coroutines.flow.Flow

class RoomInvestRepositoryImpl(
    private val portfolioDao: PortfolioDao,
    private val optionDao: OptionDao,
    private val historicalDao: HistoricalDao
) : RoomInvestRepository {
    override fun getPortfolios(): Flow<List<PortfolioDto>> {
        return portfolioDao.getPortfolio();
    }

    override suspend fun getPortfolioById(id: String): PortfolioDto? {
        return portfolioDao.getPortfolioById(id)
    }

    override suspend fun insertPortfolio(portfolioDto: PortfolioDto) {
         portfolioDao.insertPortfolio(portfolioDto)
    }

    override suspend fun deletePortfolio(portfolioDto: PortfolioDto) {
         portfolioDao.deletePortfolio(portfolioDto)
    }

    override fun getOptions(): Flow<List<OptionDto>> {
      return  optionDao.getOptions()
    }

    override suspend fun insertOption(optionDto: OptionDto) {
         optionDao.insertOption(optionDto)
    }

    override fun getHistoricals(): Flow<List<HistoricalDto>> {
        return historicalDao.gethistoricals()
    }

    override suspend fun insertHistorical(historicalDto: HistoricalDto) {
        historicalDao.insertHistorical(historicalDto)
    }
}