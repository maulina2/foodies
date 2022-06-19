package com.example.mobileappanywherecode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mobileappanywherecode.databinding.FragmentItemBinding

class ItemFragment : Fragment(R.layout.fragment_item) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var binding = FragmentItemBinding.bind(view)
//        binding.image.setImageResource(
//                resources.getIdentifier("itemimage.png", "drawable", context!!.opPackageName))
        binding.name.text = movedItem!!.name
        binding.fat.text = movedItem!!.fats_per_100_grams.toString()
        binding.description.text = movedItem!!.description
        binding.carbohydrates.text = movedItem!!.carbohydrates_per_100_grams.toString()
        binding.energy.text = movedItem!!.energy_per_100_grams.toString()
        binding.protein.text = movedItem!!.proteins_per_100_grams.toString()
        binding.weight.text = "${movedItem!!.measure} ${movedItem!!.measure_unit}"


        binding.back.setOnClickListener{
            findNavController().navigate(R.id.menuFragment)
        }
    }


}