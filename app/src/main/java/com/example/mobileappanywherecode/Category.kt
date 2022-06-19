package com.example.mobileappanywherecode

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mobileappanywherecode.databinding.CategoryBinding

class Category constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr){
    var isCurrentCategory: Boolean = false
    private lateinit var data: DataCategory

    private val binding = CategoryBinding.inflate(LayoutInflater.from(context), this)

    fun setData(category: DataCategory) {
        binding.name.text = category.name
        data = category
    }

    fun getData() = DataCategory(data.id, binding.name.text.toString())

    fun updateState() {
        if(!isCurrentCategory) {
            binding.background.background = resources.getDrawable(R.drawable.not_current_category)
            binding.name.setTextColor(resources.getColor(R.color.dark))
        }
        else {
            binding.background.background = resources.getDrawable(R.drawable.current_category)
            binding.name.setTextColor(resources.getColor(R.color.white))
        }
    }

    fun changeState() {
        isCurrentCategory = !isCurrentCategory
        updateState()
    }

}