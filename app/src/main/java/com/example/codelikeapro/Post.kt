 package com.example.codelikeapro

data class Post (
  val author: String,
  val published: String,
  val content: String,
  val link: String,
  val id: Long,
  val likeByMe: Boolean = false,
  val likes: Int = 990,
  val reposted: Int = 990,
  val ownedByMe:Boolean = false
)

