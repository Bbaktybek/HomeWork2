package com.example.homework2

import android.annotation.SuppressLint
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework2.MainActivity.Companion.MA_SA
import com.example.homework2.databinding.ActivityMainBinding
import com.example.homework2.databinding.ActivitySelectedBinding
import com.example.homework2.model.Photo

class SelectedActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectedBinding

    private lateinit var adapter: ImageAdapter

    private var list: ArrayList<Photo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        initGetExtra()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initGetExtra() {
        val getExtra = intent.getSerializableExtra(MA_SA) as? ArrayList<Photo>
        if (getExtra != null){
            list.addAll(getExtra)
            adapter.notifyDataSetChanged()
        }
    }

    private fun initAdapter() {
        adapter = ImageAdapter(list, clickListener = {}, deleteItem = {})
        binding.recycler.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBackPressed() {
        super.onBackPressed()
        list.clear()
        adapter.notifyDataSetChanged()
        finish()
    }
}