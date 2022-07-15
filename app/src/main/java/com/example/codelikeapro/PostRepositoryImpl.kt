package com.example.codelikeapro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.icu.text.CompactDecimalFormat
import kotlin.math.ln
import kotlin.math.pow


class PostRepositoryImpl: PostRepository {
    var posts = listOf (
        Post (
        author = "Неотология. Университет интернет - профессий.",
        published = "26 июня 2022 года",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам от новичков до уверенных профессионалов.",
        link = "Но самое важное остается с нами: мыверим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен ->  http:/netolo.gy/fyb",
        id = 1,
        likeByMe = false,
        likes = 9999,
            reposted = 900
            ),
        Post (
            author = "Неотология. Университет интернет - профессий.",
            published = "26 июня 2022 года",
            content = "Привет, это новая Нетология!",
            link = "Наша миссия - помочь встать на путь роста и начать цепочку перемен ->  http:/netolo.gy/fyb",
            id = 2,
            likeByMe = false,
            likes = 9999,
            reposted = 900
        ),
        Post (
            author = "Неотология. Университет интернет - профессий.",
            published = "26 июня 2022 года",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам от новичков до уверенных профессионалов.",
            link = "Но самое важное остается с нами: мыверим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен ->  http:/netolo.gy/fyb",
            id = 3,
            likeByMe = false,
            likes = 9999,
            reposted = 900
        ),
        Post (
            author = "Неотология. Университет интернет - профессий.",
            published = "26 июня 2022 года",
            content = "Привет, это новая Нетология!",
            link = "Наша миссия - помочь встать на путь роста и начать цепочку перемен ->  http:/netolo.gy/fyb",
            id = 4,
            likeByMe = false,
            likes = 9999,
            reposted = 900
        ),
        Post (
            author = "Неотология. Университет интернет - профессий.",
            published = "26 июня 2022 года",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам от новичков до уверенных профессионалов.",
            link = "Но самое важное остается с нами: мыверим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен ->  http:/netolo.gy/fyb",
            id = 5,
            likeByMe = false,
            likes = 9999,
            reposted = 900
        ),
        Post (
            author = "Неотология. Университет интернет - профессий.",
            published = "26 июня 2022 года",
            content = "Привет, это новая Нетология!",
            link = "Наша миссия - помочь встать на путь роста и начать цепочку перемен ->  http:/netolo.gy/fyb",
            id = 6,
            likeByMe = false,
            likes = 9999,
            reposted = 900
        ),
    )
    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData <List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map { if (it.id!=id) it else it.copy(likeByMe =  !it.likeByMe,
            likes = if (!it.likeByMe) it.likes + 1 else it.likes - 1)
        }

        data.value = posts
    }

    override fun repostById(id: Long) {
        posts = posts.map { if (it.id!=id) it else it.copy(
        reposted =  it.reposted +1 )
        }
        data.value = posts
    }

    override fun removeById (id: Long) {
        posts =posts.filterNot { it.id == id

        }
        data.value = posts
    }

    override fun save(post: Post) {
        posts = if (post.id == 0L) {
                listOf(
                    post.copy(
                        id = post.id + 1L
                    )
                ) + posts
        } else {
                posts.map {
                    if (it.id != post.id) it else it.copy(content = post.content)
                }
            }
            data.value = posts
        }
    }

