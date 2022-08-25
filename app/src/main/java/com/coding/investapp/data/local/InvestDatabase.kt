package com.coding.investapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.coding.investapp.data.remote.dto.HistoricalDto
import com.coding.investapp.data.remote.dto.OptionDto
import com.coding.investapp.data.remote.dto.PortfolioDto


@Database(
    entities = [PortfolioDto::class, OptionDto::class, HistoricalDto::class],
    version = 1
)
abstract class InvestDatabase : RoomDatabase() {

    abstract val portfolioDao: PortfolioDao
    abstract val optionDao: OptionDao
    abstract val historicalDao: HistoricalDao

    companion object {
        const val DATABASE_NAME = "invest_db"
    }
}