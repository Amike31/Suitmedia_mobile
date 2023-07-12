package com.example.suitmedia_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.suitmedia_mobile.databinding.ActivityMainBinding

class FirstScreen : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btn1.setOnClickListener {
            Toast.makeText(this, isPalindrome(binding.tIePalindrome.text.toString()), Toast.LENGTH_SHORT).show()
        }
        binding.btn2.setOnClickListener {
            val intent = Intent(this, SecondScreen::class.java)
            intent.putExtra("name", binding.tIeName.text.toString())
            startActivity(intent)
        }
    }

    fun isPalindrome(word: String): String {
        if (word.isEmpty()) return "empty text"
        val word = word.toLowerCase()
        var result = true
        for (i in 0..word.length / 2) {
            if (word[i] != word[word.length - i - 1]) {
                result = false
                break
            }
        }
        return if (result) "isPalindrome" else "notPalindrome"
    }
}