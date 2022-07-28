 package com.example.codelikeapro

data class Post(
  val author: String,
  val published: String,
  val content: String,
  val id: Long,
  val likeByMe: Boolean = false,
  val likes: Int = 0,
  val reposted: Int = 0,
  val ownedByMe: Int = 0,
  val video: String? = null,

  )

