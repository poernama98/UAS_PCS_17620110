package com.rizkypurnama.finalproject.playerdetail

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.rizkypurnama.finalproject.R
import kotlinx.android.synthetic.main.activity_detail_player.*
import org.jetbrains.anko.*

class DetailPlayerActivity : AppCompatActivity() {
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var imagePlayer: ImageView
    private lateinit var heigth: TextView
    private lateinit var weigth: TextView
    private lateinit var position: TextView
    private lateinit var description: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        title = "Detail Player"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        collapsingToolbarLayout = find(R.id.toolbar_layout)
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(baseContext, android.R.color.transparent))

        imagePlayer = find(R.id.imagePlayer)
        heigth = find(R.id.heigth)
        weigth = find(R.id.weigth)
        position = find(R.id.position)
        description = find(R.id.description)

        val intent = intent
        intent?.let {
            Glide.with(baseContext).load(it.getStringExtra("ImagePlayer")).into(imagePlayer)
            heigth.text = it.getStringExtra("Heigth") + " M"
            weigth.text = it.getStringExtra("Weigth") + " Kg"
            position.text = it.getStringExtra("Position")
            description.text = it.getStringExtra("Description")
        }
    }
}
