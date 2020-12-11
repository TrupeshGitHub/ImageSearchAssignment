package com.assignment.imagesearch.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.imagesearch.model.ImageCommentEntity

@Dao
interface ImageCommentDao {

    /**
     * Insert comment in database
     *
     * @param commentEntity
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImageComment(commentEntity: ImageCommentEntity)

    /**
     * Query to get all Comments related to Image
     *
     * @param dataID
     * @return
     */
    @Query("SELECT * FROM Comments WHERE dataId = :dataID  ORDER BY timestamp DESC")
    suspend fun getAllImageComments(dataID: String): List<ImageCommentEntity>

}