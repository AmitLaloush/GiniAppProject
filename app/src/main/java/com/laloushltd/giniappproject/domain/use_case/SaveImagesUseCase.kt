package com.laloushltd.giniappproject.domain.use_case

import android.util.Log
import com.laloushltd.giniappproject.common.Resource
import com.laloushltd.giniappproject.data.remote.dto.toImageData
import com.laloushltd.giniappproject.domain.model.ImageData
import com.laloushltd.giniappproject.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class SaveImagesUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    suspend fun invoke(images: List<ImageData>) {
        Log.d("AMIT555", "Started")
        images.forEach {
            repository.saveImage(it)
        }
        val response = repository.getImages().collect {
            Log.d("AMIT555", "DATA ${it}")
        }
    }
}