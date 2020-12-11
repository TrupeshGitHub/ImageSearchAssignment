package com.assignment.imagesearch.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assignment.imagesearch.data.database.AppDatabase
import com.assignment.imagesearch.model.ImageCommentEntity
import com.assignment.imagesearch.repositories.ImageCommentsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageDetailViewModel(application: Application) : AndroidViewModel(application) {

    val imageCommentList = MutableLiveData<List<ImageCommentEntity>>()
    private val imageCommentsRepository: ImageCommentsRepository

    init {
        val db = AppDatabase.getDatabase(application)
        imageCommentsRepository = ImageCommentsRepository(db.imageCommentDao())
    }


    fun getAllImageComment(dataID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            imageCommentList.postValue(imageCommentsRepository.getAllImageComments(dataID))
        }
    }

    fun insertComment(commentEntity: ImageCommentEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            imageCommentsRepository.insertComment(commentEntity)
            commentEntity.dataId?.let { getAllImageComment(it) }
        }
    }


}