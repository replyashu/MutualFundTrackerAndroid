package com.ashu.mftracker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashu.mftracker.data.response.MutualFundWithoutNav
import com.ashu.mftracker.data.response.RegisterResponse
import com.ashu.mftracker.global.network.Resource
import com.ashu.mftracker.repository.FundsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val fundsRepository: FundsRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _fetchMutualFunds = MutableLiveData<Resource<MutualFundWithoutNav>>()

    val fetchMutualFunds: LiveData<Resource<MutualFundWithoutNav>>
        get() = _fetchMutualFunds

    fun retrieveMutualFunds() = viewModelScope.launch {
        _fetchMutualFunds.postValue(Resource.loading(null))
        try {
            fundsRepository.fetchMutualFundsWithoutNav().let {
                if (it.isSuccessful) {
                    _fetchMutualFunds.postValue(Resource.success(it.body()))
                } else {
                    _fetchMutualFunds.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        } catch (e: Exception) {
            _fetchMutualFunds.postValue(Resource.error("error fetching", null))
        }

    }
}