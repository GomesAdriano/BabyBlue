package com.projeto.babyblue.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projeto.babyblue.model.IntroSlide
import com.projeto.babyblue.R

class IntroSliderAdapter(private val introSlides: List<IntroSlide>)
    : RecyclerView.Adapter<IntroSliderAdapter.IntroSlideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        return IntroSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {
        holder.bind(introSlides[position])
    }

    override fun getItemCount(): Int {
        return introSlides.size
    }

    inner class IntroSlideViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val imageIcon = view.findViewById<ImageView>(R.id.imageSlideIcon)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)

        fun bind(introSlide: IntroSlide){
            imageIcon.setImageResource(introSlide.icone)
            textDescription.text = introSlide.descricao
        }
    }
}