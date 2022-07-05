package com.example.codelikeapro

import androidx.lifecycle.LiveData

interface PostRepository {
    fun get (): LiveData <Post>
    fun like ()
    fun repost ()
}