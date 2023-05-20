package com.blackstoneeit.ncgrtask.presentation.main.mostviewed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blackstoneeit.ncgrtask.domain.MostPopularUseCase
import com.blackstoneeit.ncgrtask.presentation.base.BaseViewModel
import com.blackstoneeit.ncgrtask.presentation.main.mostviewed.widget.ArticleUiModel
import com.blackstoneeit.ncgrtask.presentation.main.mostviewed.widget.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MostViewedViewModel @Inject constructor(
    private val repository: MostPopularUseCase
) : BaseViewModel() {

    private val _articlesList = MutableLiveData<List<ArticleUiModel>>()
    val articlesList: LiveData<List<ArticleUiModel>>
        get() = _articlesList

    init {
        getMostViewed()
    }

     private fun getMostViewed() {
        viewModelScope.launch {
            repository.getMostPopular("1", "3OXaIED4UHr0oyy0ufuAXTBQGrVlRCpZ")
                .applyLoading()
                .collect { response ->
                    if (response.isSuccessful && response.body() != null) {
                        _articlesList.value = response.body()?.results?.map {
                            it.toUiModel()
                        }
                    } else {
                        handleErrorResponse(response)
                    }
                }
        }
    }
}

