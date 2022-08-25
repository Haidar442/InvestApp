package com.coding.investapp.data.local

import androidx.room.*
import com.coding.investapp.data.remote.dto.HistoricalDto
import com.coding.investapp.data.remote.dto.PortfolioDto
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoricalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistorical(historicalDto: HistoricalDto)

    @Delete
    suspend fun deletePortfolio(historicalDto: HistoricalDto)

    @Query("SELECT * FROM historical_table")
    fun gethistoricals(): Flow<List<HistoricalDto>>
}





