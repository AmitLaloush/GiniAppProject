package com.laloushltd.giniappproject.di

import android.app.Application
import androidx.room.Room
import com.laloushltd.giniappproject.common.Constants
import com.laloushltd.giniappproject.data.database.ImageDatabase
import com.laloushltd.giniappproject.data.remote.PixaBayApi
import com.laloushltd.giniappproject.data.repository.ImageRepositoryImpl
import com.laloushltd.giniappproject.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): PixaBayApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PixaBayApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImageRepository(api: PixaBayApi, db: ImageDatabase): ImageRepository {
        return ImageRepositoryImpl(api, db)
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): ImageDatabase {
        return Room.databaseBuilder(
            app,
            ImageDatabase::class.java,
            ImageDatabase.DATABASE_NAME
        ).build()
    }

}