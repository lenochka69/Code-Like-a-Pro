package com.example.codelikeapro

import androidx.lifecycle.LiveData
import com.example.codelikeapro.Post

interface PostRepository {
    fun getAll (): LiveData <List<Post>>
    fun likeById (id: Long)
    fun repostById (id: Long)
    fun removeById (id: Long)
    fun save (post: Post)

}