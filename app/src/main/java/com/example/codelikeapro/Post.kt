 package com.example.codelikeapro

data class Post (
  val author: String,
  val published: String,
  val content: String,
  val link: String,
  val id: Int,
  val likeByMe: Boolean = false,
  val likes: Int = 990,
  val repost: Boolean = false,
  val reposted: Int = 990

  )

