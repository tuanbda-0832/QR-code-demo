package com.sun.qrcodedemo.ui.base

/**
 * Created by Duong Tuan on 23/09/2019.
 */
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.qrcodedemo.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {

    // loading flag
    val isLoading = MutableLiveData<Boolean>().apply { value = false }

    // error message
    val errorMessage = MutableLiveData<String>()

    // optional flags
    val noInternetConnectionEvent = SingleLiveEvent<Unit>()
    val connectTimeoutEvent = SingleLiveEvent<Unit>()
    val forceUpdateAppEvent = SingleLiveEvent<Unit>()
    val serverMaintainEvent = SingleLiveEvent<Unit>()

    // rx
//    private val compositeDisposable = CompositeDisposable()
//    fun addDisposable(disposable: Disposable) = compositeDisposable.add(disposable)

    // coroutines

    // exception handler for coroutine
    private val exceptionHandler = CoroutineExceptionHandler { context, throwable ->
        viewModelScope.launch {
            onLoadFail(throwable)
        }
    }
    /*
    private val viewModelJob = SupervisorJob()
    protected val ioContext = viewModelJob + Dispatchers.IO
    protected val uiContext = viewModelJob + Dispatchers.Main
    protected val ioScope = CoroutineScope(ioContext)
    protected val uiScope = CoroutineScope(uiContext)
    protected val ioScopeError = CoroutineScope(ioContext + exceptionHandler)
    protected val uiScopeError = CoroutineScope(uiContext + exceptionHandler)
    */
    // viewModelScope with exception handler
    protected val viewModelScopeExceptionHandler = viewModelScope + exceptionHandler

    /**
     * handle throwable when load fail
     */
    open suspend fun onLoadFail(throwable: Throwable) {
        withContext(Dispatchers.Main) {
            when (throwable) {
                // case no internet connection
                is UnknownHostException -> {
                    noInternetConnectionEvent.call()
                }
                // case request time out
                is SocketTimeoutException -> {
                    connectTimeoutEvent.call()
                }
                else -> {
                    // convert throwable to base exception to get error information
//                    val baseException = convertToBaseException(throwable)
//                    when (baseException.httpCode) {
//                        HttpURLConnection.HTTP_UNAUTHORIZED -> {
//                            errorMessage.value = baseException.message
//                        }
//                        HttpURLConnection.HTTP_INTERNAL_ERROR -> {
//                            errorMessage.value = baseException.message
//                        }
//                        else -> {
//                            errorMessage.value = baseException.message
//                        }
//                    }
                }
            }
            hideLoading()
        }
    }

    open fun showError(e: Throwable) {
        errorMessage.value = e.message
    }

    fun showLoading() {
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }
}