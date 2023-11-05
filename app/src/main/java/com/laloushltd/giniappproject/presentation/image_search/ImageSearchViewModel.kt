package com.laloushltd.giniappproject.presentation.image_search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laloushltd.giniappproject.common.Resource
import com.laloushltd.giniappproject.domain.model.ImageData
import com.laloushltd.giniappproject.domain.use_case.GetImagesUseCase
import com.laloushltd.giniappproject.domain.use_case.GetRemoteImageUseCase
import com.laloushltd.giniappproject.domain.use_case.SaveImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageSearchViewModel @Inject constructor(
    private val getRemoteImagesData: GetRemoteImageUseCase,
    private val getImages: GetImagesUseCase,
    val saveImagesUseCase: SaveImagesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ImageSearchState())
    val state: State<ImageSearchState> = _state

    init {
        getRemoteImages()
        getImages.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ImageSearchState(isLoading = false, images = result.data ?: mutableListOf(), error = null)
                }
                is Resource.Error -> {
                    _state.value = ImageSearchState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ImageSearchState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getRemoteImages() {
        getRemoteImagesData.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    updateDataBase(result.data)
                }
                is Resource.Error -> {
                    _state.value = ImageSearchState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ImageSearchState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun updateDataBase(data: List<ImageData>?) {
        data?.let { images ->
            viewModelScope.launch {
                saveImagesUseCase.invoke(images = images)
            }
        }

    }
}