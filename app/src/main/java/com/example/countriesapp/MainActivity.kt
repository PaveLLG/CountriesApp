package com.example.countriesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.countriesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seachButton.setOnClickListener{
            val countryName = binding.countryNameEditText.text.toString()

            lifecycleScope.launch{
                try {
                    val counrtries = restCountriesApi.getCountryByName(countryName)
                    val country = counrtries[0]

                    binding.countryNameTextView.text = country.name
                    binding.capitalTextView.text = country.capital
                    binding.populationTextVeiw.text = formatNumber(country.population)
                    binding.areaTextView.text = formatNumber(country.area)
                    binding.languagesTextView.text = lanToString(country.languages)

                    loadSvg(binding.imageView, country.flag)

                    binding.resaltLayout.visibility = View.VISIBLE
                    binding.searchLayout.visibility = View.INVISIBLE
                }catch (e:Exception){
                    binding.textSeacrh.text = "Страна не найдена"
                    binding.imageSeacrh.setImageResource(R.drawable.error_svgrepo)
                    binding.resaltLayout.visibility = View.INVISIBLE
                    binding.searchLayout.visibility = View.VISIBLE
                }

            }



        }


    }


}




