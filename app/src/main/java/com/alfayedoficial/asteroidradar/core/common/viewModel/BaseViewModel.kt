package com.alfayedoficial.asteroidradar.core.common.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfayedoficial.coreModel.dataState.Failure
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel(){

    protected val mViewModelScope: CoroutineScope
        get() = viewModelScope

    override fun onCleared() {
        super.onCleared()
        mViewModelScope.cancel()
    }

    fun Failure.validationError(isExit : (() -> Unit)? =  null):String{
        var mes : String? = null
        when(this) {
            is Failure.NetworkConnection -> {
                mes = this.message
            }
            is Failure.ThrowableFailure -> {
                mes = this.message
            }
            is Failure.ServerError -> {
                when (this) {
                    is Failure.ServerError.NotFound -> {
                        mes = this.message
                    }
                    is Failure.ServerError.BadRequest -> {
                        mes = this.message
                    }
                    is Failure.ServerError.Unauthorized -> {
                        mes = this.message
                        //TODO clear token and redirect to login screen
                    }
                    is Failure.ServerError.Forbidden -> {
                        mes = this.message
                    }
                    is Failure.ServerError.MethodNotAllowed -> {
                        mes = this.message
                    }
                    is Failure.ServerError.UnProcessableEntity -> {
                        mes = this.message
                    }
                    is Failure.ServerError.InternalServerError -> {
                        mes = this.message
                        if (isExit != null) {
                            isExit()
                        }
                    }
                    is Failure.ServerError.Unknown -> {
                        mes = this.message
                    }
                }
            }
        }

       return mes?:"Please try again later"
    }

}