package com.laloushltd.giniappproject.domain.use_case

import com.laloushltd.giniappproject.common.Resource
import com.laloushltd.giniappproject.data.remote.dto.toImageData
import com.laloushltd.giniappproject.domain.model.ImageData
import com.laloushltd.giniappproject.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetImagesUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    operator fun invoke(): Flow<Resource<List<ImageData>>> = flow {
        try {
            emit(Resource.Loading())
            repository.getImages().onEach {
                emit(Resource.Success(it))
            }
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}