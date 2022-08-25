package com.coding.investapp.data.local

import androidx.room.*
import com.coding.investapp.data.remote.dto.PortfolioDto
import kotlinx.coroutines.flow.Flow

@Dao
interface PortfolioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPortfolio(portfolioDto: PortfolioDto)

    @Delete
    suspend fun deletePortfolio(portfolioDto: PortfolioDto)

    @Query("SELECT * FROM portfolio_table WHERE id = :id")
    suspend fun getPortfolioById(id: String): PortfolioDto?

    @Query("SELECT * FROM portfolio_table")
    fun getPortfolio(): Flow<List<PortfolioDto>>
}





