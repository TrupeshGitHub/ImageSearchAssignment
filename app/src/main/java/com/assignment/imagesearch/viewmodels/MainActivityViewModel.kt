package com.assignment.imagesearch.viewmodels

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.assignment.imagesearch.model.Data
import com.assignment.imagesearch.network.RetrofitFactory
import com.assignment.imagesearch.network.SafeApiRequest

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    val isSearchApiInProgress = MutableLiveData<Boolean>()
    val isMoreApiInProgress = MutableLiveData<Boolean>()
    val pageNo = MutableLiveData<Int>()
    private var imageList = mutableListOf<Data>()
    val imageDataList = MutableLiveData<List<Data>>()

    private var lastSearch = ""

    init {
        isSearchApiInProgress.value = false
        isMoreApiInProgress.value = false
        imageDataList.value = imageList
    }

    fun getImages(context: Context, searchText: String, isPagination: Boolean) {

        if (lastSearch == searchText) {
            return
        } else {
            lastSearch = searchText
        }

        if (isPagination) {
            isMoreApiInProgress.value = true
            pageNo.value = pageNo.value?.plus(1)
        } else {
            pageNo.value = 1
            isSearchApiInProgress.value = true
        }

        val call = RetrofitFactory.makeRetrofitService(context)
        SafeApiRequest().apiRequest(
            { call.getImagesFromSearch(pageNo.value ?: 1, searchText) },
            { response ->
                if (response != null) {
                    response.imageData?.let {
                        processImageList(isPagination, it)
                    }
                }
                isSearchApiInProgress.value = false
                isMoreApiInProgress.value = false
            },
            { error ->
                isSearchApiInProgress.value = false
                isMoreApiInProgress.value = false
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            },
            { error ->
                isSearchApiInProgress.value = false
                isMoreApiInProgress.value = false
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            })

    }

    private fun processImageList(isPagination: Boolean, list: List<Data>) {

        val updatedList = mutableListOf<Data>()

        for (item in list) {
            if (item.images != null) {
                updatedList.add(item)
            }
        }

        if (isPagination) {
            imageList.addAll(updatedList)
        } else {
            imageList = updatedList
        }

        imageDataList.value = imageList
    }

}