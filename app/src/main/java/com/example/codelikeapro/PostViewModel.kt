package com.example.codelikeapro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
val emptyPost = Post(
    "",
    "",
    "",
    "",
    0L,
    false,
    0,
    0,
false,
""

)

class PostViewModel : ViewModel () {
    private val repository: PostRepository = PostRepositoryImpl()
    val data = repository.getAll()
    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.repostById(id)
    fun removeById(id: Long) = repository.removeById(id)

    val edited = MutableLiveData(emptyPost)
    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = emptyPost
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun changeContent(content: String) {
        edited.value?.let {
            val trimmed = content.trim()
            if (trimmed == it.content) {
                return
            }
            edited.value = it.copy(content = trimmed)
        }
    }
}