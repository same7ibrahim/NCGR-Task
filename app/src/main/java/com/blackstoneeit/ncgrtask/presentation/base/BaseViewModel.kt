package com.blackstoneeit.ncgrtask.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blackstoneeit.ncgrtask.common.utils.SingleLiveEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import retrofit2.Response


open class BaseViewModel : ViewModel() {


    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private val _successMessage = SingleLiveEvent<String>()
    val successMessage: LiveData<String>
        get() = _successMessage

    private val _errorViewState = SingleLiveEvent<String>()
    val errorViewState: LiveData<String>
        get() = _errorViewState


    private fun showLoading() {
        _loadingState.value = true
    }

    private fun hideLoading() {
        _loadingState.value = false
    }


    protected fun <T> Flow<T>.applyLoading(): Flow<T> =
        this.onStart { showLoading() }.onCompletion { hideLoading() }

    protected fun <T> handleErrorResponse(response: Response<T>) {
        _errorViewState.value = response.message() ?: when {
            response.code() == 401 -> {
                "unauthorized"
            }
            response.code() == 404 -> {
                "not found"
            }
            response.body() == null -> {
                "No Data"
            }
            else -> {
                "Something Went Wrong"
            }
        }
    }

}
