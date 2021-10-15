package com.codingassignemnt.app.framework.presentation

import androidx.lifecycle.*
import com.codingassignemnt.app.business.domain.bean.Article
import com.codingassignemnt.app.business.domain.state.DataState
import com.codingassignemnt.app.business.interactor.GetArticles
import com.codingassignemnt.app.framework.presentation.MainStateEvent.GetArticlesEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class NewsListViewModel
@Inject
constructor(
    private val getArticles: GetArticles,
): ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Article>>> = MutableLiveData()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    val dataState: LiveData<DataState<List<Article>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is GetArticlesEvent -> {
                    getArticles.execute()
                        .onEach {dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope + exceptionHandler)
                }
            }
        }
    }

    private fun onError(message: String) {
        _dataState.value = DataState.Error(message)
    }

}


sealed class MainStateEvent{

    object GetArticlesEvent: MainStateEvent()

    object None: MainStateEvent()
}


















