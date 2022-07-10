package com.example.codelikeapro

import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel () {
    private val repository: PostRepository = PostRepositoryImpl ()
    val data = repository.getAll()
    fun likeById (id:Long) = repository.likeById(id)
    fun repostById(id: Long) = repository.repostById (id)
}