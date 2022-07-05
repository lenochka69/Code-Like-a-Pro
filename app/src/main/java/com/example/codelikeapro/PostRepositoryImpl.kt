package com.example.codelikeapro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.icu.text.CompactDecimalFormat
import kotlin.math.ln
import kotlin.math.pow


class PostRepositoryImpl: PostRepository {
    var post = Post (
        author = "Неотология. Университет интернет - профессий.",
        published = "26 июня 2022 года",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам от новичков до уверенных профессионалов.",
        link = "Но самое важное остается с нами: мыверим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен ->  http:/netolo.gy/fyb",
        id = 0,
        likeByMe = false,
        likes = 9999,
        repost = false
            )
    private val data = MutableLiveData(post)


    override fun get(): LiveData<Post> = data

    override fun like() {
        post = post.copy(
            likeByMe = !post.likeByMe,
            likes = if (post.likeByMe) post.likes -1 else post.likes +1

            )
        data.value = post
    }

    override fun repost() {

        post = post.copy(
            repost = !post.repost,
            reposted = if (post.repost) post.reposted +1 else post.reposted +1
        )

        data.value = post
    }

}