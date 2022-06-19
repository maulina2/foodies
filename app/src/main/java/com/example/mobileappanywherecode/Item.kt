package com.example.mobileappanywherecode

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mobileappanywherecode.databinding.ItemBinding

class Item constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): LinearLayout(context, attrs, defStyleAttr) {
    private lateinit var data: DataItem
    var countToBuy = 0

    val binding = ItemBinding.inflate(LayoutInflater.from(context), this)

    fun setData(item: DataItem) {
        data = item

        // "1.png" is incorrect name for drawable
        binding.itemImage.setImageResource(
            resources.getIdentifier("itemimage.png", "drawable", context.opPackageName))
        binding.name.text = data.name
        binding.measure.text = "${data.measure} ${data.measure_unit}"
        binding.buyButton.text = "${data.price_current}"
        binding.plusButton.visibility = View.INVISIBLE
        binding.minusButton.visibility = View.INVISIBLE
        binding.counter.visibility = View.INVISIBLE

    }

    fun update() {

    }
}