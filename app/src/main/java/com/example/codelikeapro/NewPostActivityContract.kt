package com.example.codelikeapro

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class NewPostActivityContract : ActivityResultContract<Unit, String?>() {

    override fun createIntent(context: Context, input: Unit): Intent =
        Intent(context, NewPostAcivity::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?): String? =
        intent?.getStringExtra(Intent.EXTRA_TEXT)
}