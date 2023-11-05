package com.laloushltd.giniappproject.domain.repository

import com.laloushltd.giniappproject.data.remote.dto.Hit
import com.laloushltd.giniappproject.domain.model.ImageData
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun getRemoteImages(): List<Hit>

    suspend fun saveImage(data: ImageData)

    suspend fun getImages(): Flow<List<ImageData>>

}