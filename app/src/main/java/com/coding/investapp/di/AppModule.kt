package com.coding.investapp.di

import android.app.Application
import androidx.room.Room
import com.coding.investapp.common.Constants
import com.coding.investapp.data.local.InvestDatabase
import com.coding.investapp.data.remote.InvestApi
import com.coding.investapp.data.repository.InvestRepositoryImpl
import com.coding.investapp.data.repository.RoomInvestRepositoryImpl
import com.coding.investapp.domain.repository.InvestRepository
import com.coding.investapp.domain.repository.RoomInvestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGithubReposApi(): InvestApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InvestApi::class.java)
    }

    @Provides
    @Singleton
    fun provideInvestDatabase(app: Application): InvestDatabase {
        return Room.databaseBuilder(
            app,
            InvestDatabase::class.java,
            InvestDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRoomInvestRepository(db: InvestDatabase): RoomInvestRepository {
        return RoomInvestRepositoryImpl(db.portfolioDao, db.optionDao, db.historicalDao)
    }

    @Provides
    @Singleton
    fun provideInvestRepository(api: InvestApi): InvestRepository {
        return InvestRepositoryImpl(api)
    }

}