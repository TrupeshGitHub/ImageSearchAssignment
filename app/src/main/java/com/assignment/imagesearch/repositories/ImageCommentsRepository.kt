package com.assignment.imagesearch.repositories

import com.assignment.imagesearch.data.dao.ImageCommentDao
import com.assignment.imagesearch.model.ImageCommentEntity

class ImageCommentsRepository(private val dao: ImageCommentDao) {

    /**
     * insert new Image comment in database
     *
     * @param commentEntity
     */
    suspend fun insertComment(commentEntity: ImageCommentEntity) {
        dao.insertImageComment(commentEntity)
    }

    /**
     * get All Comment related to Image
     *
     * @param dataID
     * @return
     */
    suspend fun getAllImageComments(dataID: String): List<ImageCommentEntity> {
        return dao.getAllImageComments(dataID)
    }
}