package com.example.codelikeapro

import androidx.lifecycle.LiveData

interface PostRepository {
    fun getAll (): LiveData <List<Post>>
    fun likeById (id: Long)
    fun repostById (id: Long)
}