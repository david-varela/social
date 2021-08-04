package dev.davidvarela.social.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.davidvarela.social.domain.Result
import dev.davidvarela.social.domain.entities.PostPreview
import dev.davidvarela.social.domain.useCases.PostsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostsViewModel(private val postsUseCase: PostsUseCase) : ViewModel() {
    private val _postsPreviews: MutableLiveData<List<PostPreview>> = MutableLiveData()
    val postsPreviews: LiveData<List<PostPreview>> = _postsPreviews

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun refreshPosts() = viewModelScope.launch {
        postsUseCase().collect {
            if (it is Result.Success)
                it.data.let { data -> _postsPreviews.postValue(data) }
            else if (it is Result.Error && !it.throwable.message.isNullOrEmpty())
                _error.postValue(it.throwable.message)
        }
    }
}
