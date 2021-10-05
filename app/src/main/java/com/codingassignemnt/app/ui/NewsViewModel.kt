package com.codingassignemnt.app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingassignemnt.app.domain.NewsRepository
import com.codingassignemnt.app.domain.NewsService
import com.codingassignemnt.app.domain.util.UiMapper
import kotlinx.coroutines.*

class NewsViewModel : ViewModel() {

    private var mainRepository: NewsRepository
    private val uiMapper = UiMapper()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val errorMessage = MutableLiveData<String>()
    val newsListLiveData = MutableLiveData<List<NewsUiModel>>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()

    init {
        val retrofitService = NewsService.getInstance()
        mainRepository = NewsRepository(retrofitService)
    }

    fun getNews() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAllNews()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val newsList = response.body()?.articles?.map { uiMapper.mapToUiNewsModel(it) }
                    this@NewsViewModel.newsListLiveData.postValue(newsList)
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}