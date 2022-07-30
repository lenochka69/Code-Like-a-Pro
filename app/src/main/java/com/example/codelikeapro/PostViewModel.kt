package com.example.codelikeapro

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

val emptyPost = Post(
    "",
    "",
    "",
    0,
    false,
    0,
    0,
    0,
    null,


    )

class PostViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PostRepository = PostRepositoryImpl(application)

    val data = repository.getAll()
    val edited = MutableLiveData(emptyPost)

    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.repostById(id)
    fun removeById(id: Long) = repository.removeById(id)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = emptyPost
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun editContent(content: String) {
        edited.value?.let {
            val trimmed = content.trim()
            if (trimmed == it.content) {
                return
            }
            edited.value = it.copy(content = trimmed)
        }
    }
}