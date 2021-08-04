package dev.davidvarela.social.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.davidvarela.social.domain.Result
import dev.davidvarela.social.domain.useCases.UserUseCase
import dev.davidvarela.social.domain.useCases.NumCommentsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostDetailViewModel(
    private val userUseCase: UserUseCase,
    private val numCommentsUseCase: NumCommentsUseCase
) : ViewModel() {
    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private val _imageUrl: MutableLiveData<String> = MutableLiveData()
    val imageUrl: LiveData<String> = _imageUrl

    private val _numComments: MutableLiveData<Int> = MutableLiveData()
    val numComments: LiveData<Int> = _numComments

    fun getImageUrl(userId: Int) = viewModelScope.launch {
        userUseCase(userId).collect {
            when {
                it is Result.Success -> it.data.let { data -> _imageUrl.postValue(data.imageUrl) }
                it is Result.Error && !it.throwable.message.isNullOrEmpty() -> _error.postValue(it.throwable.message)
            }
        }
    }

    fun getNumComments(postId: Int) = viewModelScope.launch {
        numCommentsUseCase(postId).collect {
            when {
                it is Result.Success -> it.data.let { data -> _numComments.postValue(data) }
                it is Result.Error && !it.throwable.message.isNullOrEmpty() -> _error.postValue(it.throwable.message)
            }
        }
    }
}
