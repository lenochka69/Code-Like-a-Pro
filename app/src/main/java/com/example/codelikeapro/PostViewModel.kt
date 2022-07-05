package com.example.codelikeapro

import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel () {
    private val repository: PostRepository = PostRepositoryImpl ()
    val data = repository.get()
    fun like () = repository.like()
    fun repost () = repository.repost ()
}