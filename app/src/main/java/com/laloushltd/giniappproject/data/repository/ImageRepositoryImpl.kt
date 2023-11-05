package com.laloushltd.giniappproject.data.repository

import com.laloushltd.giniappproject.data.database.ImageDatabase
import com.laloushltd.giniappproject.data.remote.PixaBayApi
import com.laloushltd.giniappproject.data.remote.dto.Hit
import com.laloushltd.giniappproject.domain.model.ImageData
import com.laloushltd.giniappproject.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val api: PixaBayApi,
    private val db: ImageDatabase
): ImageRepository {
    override suspend fun getRemoteImages(): List<Hit> {
        return api.searchImages().hits
    }

    override suspend fun saveImage(data: ImageData) {
        db.imageDao.insertImage(data)
    }

    override suspend fun getImages(): Flow<List<ImageData>> {
        return db.imageDao.getImages()
    }
}