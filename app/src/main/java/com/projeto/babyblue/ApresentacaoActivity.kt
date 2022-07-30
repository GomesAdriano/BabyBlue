package com.projeto.babyblue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.projeto.babyblue.adapters.IntroSliderAdapter
import com.projeto.babyblue.model.IntroSlide

class ApresentacaoActivity : AppCompatActivity() {

    private lateinit var indicatorsContainer : LinearLayout
    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Auto-avaliação",
                R.drawable.img_intro_1
            ),
            IntroSlide(
                "Explicando a depressão pós-parto",
                R.drawable.img_intro_2
            ),
            IntroSlide(
                "Apoio à puérpera",
                R.drawable.img_intro_3
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apresentacao)

        val introSliderViewPager = findViewById<ViewPager2>(R.id.introSliderViewPager)
        val buttonNext = findViewById<Button>(R.id.buttonNext)
        indicatorsContainer = findViewById(R.id.indicatorsContainer)

        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)

        introSliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })

        buttonNext.setOnClickListener{
            if(introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount){
               introSliderViewPager.currentItem += 1
            }else{
                Intent(applicationContext, TesteActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }

    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)

        for(i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int){
        val childCount = indicatorsContainer.childCount

        for(i in 0 until childCount){

            val imageView = indicatorsContainer[i] as ImageView

            if(i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }

}