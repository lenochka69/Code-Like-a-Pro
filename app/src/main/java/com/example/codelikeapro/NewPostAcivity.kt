package com.example.codelikeapro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.codelikeapro.databinding.ActivityMainBinding
import com.example.codelikeapro.databinding.NewPostBinding

class NewPostAcivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = NewPostBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.content.requestFocus()


        val contents = intent.getStringExtra(Intent.EXTRA_TEXT)

    binding.content.setText(contents)

    binding.save.setOnClickListener {
        if (binding.content.text.isNullOrBlank()) {
            Toast.makeText(
                this,
                getString(R.string.empty_post_error),
                Toast.LENGTH_SHORT
            ).show()
            setResult(RESULT_CANCELED)
        } else {
            val result = Intent().putExtra(Intent.EXTRA_TEXT, binding.content.text.toString())
            setResult(RESULT_OK, result)
        }
        finish()
    }
}
}
