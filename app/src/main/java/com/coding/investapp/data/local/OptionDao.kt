package com.coding.investapp.data.local

import androidx.room.*
import com.coding.investapp.data.remote.dto.OptionDto
import kotlinx.coroutines.flow.Flow

@Dao
interface OptionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOption(optionDto: OptionDto)

    @Delete
    suspend fun deleteOption(optionDto: OptionDto)

    @Query("SELECT * FROM option_table WHERE id = :id")
    suspend fun getOptionById(id: String): OptionDto?

    @Query("SELECT * FROM option_table")
    fun getOptions(): Flow<List<OptionDto>>
}





