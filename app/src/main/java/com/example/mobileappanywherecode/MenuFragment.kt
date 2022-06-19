package com.example.mobileappanywherecode

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.example.mobileappanywherecode.databinding.FragmentMenuBinding
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

lateinit var categories: List<DataCategory>
lateinit var items: List<DataItem>
var movedItem: DataItem? = null

class MenuFragment: Fragment(R.layout.fragment_menu) {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var screensaver : LottieAnimationView
    private var isStarted = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)
        screensaver = LottieAnimationView(context)
        if(!isStarted) {
            addCategories()
            addItems()
        }
    }

    @Suppress("DEPRECATION")
    override fun onStart() {
        super.onStart()
        if(!isStarted) {
            binding.root.setBackgroundColor(resources.getColor(R.color.primary))
            val view = View(context)
            view.setBackgroundColor(resources.getColor(R.color.primary))
            startScreensaver(view)
            val thread1 = Thread { stopScreensaver(view) }
            thread1.start()
            binding.root.setBackgroundColor(resources.getColor(R.color.white))
        }
        isStarted = true
    }

    private fun addCategories() {
        val json = context!!.resources.assets.open(
            "categories.json").bufferedReader().use { it.readText() }
        categories = Json.decodeFromString(json)

        for(i in 0 until categories.size) {
            val view = Category(context!!)
            view.setData(categories[i])
            binding.categories.addView(view)
            if(i == 0) view.isCurrentCategory = true
            view.updateState()
        }
    }

    private fun addItems() {
        val json = context!!.resources.assets.open(
            "items.json").bufferedReader().use { it.readText() }
        items = Json.decodeFromString(json)

        var currentLayout = LinearLayout(context!!)
        for(i in 0 until 30) {
//        for(i in 0 until items.size)
            if(i%2 == 0) {
                currentLayout = LinearLayout(context!!)
                currentLayout.orientation = LinearLayout.HORIZONTAL
                binding.items.addView(currentLayout)
            }
            val view = Item(context!!)
            view.setData(items[i])
            val params = ConstraintLayout.LayoutParams(metrics.bounds.width()/2 - 16, ConstraintLayout.LayoutParams.MATCH_PARENT)
            currentLayout.addView(view, params)
            view.binding.buyButton.setOnClickListener(addItemToBasket())
            view.binding.plusButton.setOnClickListener(addItemToBasket())
            view.binding.minusButton.setOnClickListener(addItemToBasket())
            view.setOnClickListener{
                movedItem = items[i]
                findNavController().navigate(R.id.itemFragment)
                isStarted = false
            }
            view.update()
        }
    }

    private fun addItemToBasket() = View.OnClickListener { view ->
        var item = view.parent
        while (item !is Item) item = (item as View).parent
        view as Button
        if(view.text == "-") {
            item.countToBuy--
            if(item.countToBuy == 0) {
                item.binding.buyButton.visibility = View.VISIBLE
                item.binding.plusButton.visibility = View.INVISIBLE
                item.binding.minusButton.visibility = View.INVISIBLE
                item.binding.counter.visibility = View.INVISIBLE
            }
        }else if( view.text == "+") {
            item.countToBuy++
        }else {
            item.countToBuy++
            item.binding.buyButton.visibility = View.INVISIBLE
            item.binding.plusButton.visibility = View.VISIBLE
            item.binding.minusButton.visibility = View.VISIBLE
            item.binding.counter.visibility = View.VISIBLE
        }

        item.binding.counter.text = item.countToBuy.toString()
    }

    @Suppress("DEPRECATION")
    private fun startScreensaver(backgroundView: View) {
        backgroundView.translationZ = 1000f
        binding.root.addView(backgroundView)
        screensaver.translationZ = 1001f
        binding.root.addView(screensaver)
        screensaver.setAnimation(R.raw.start_screen_animation)
        screensaver.progress = 0f
        screensaver.playAnimation()
    }

    @Suppress("DEPRECATION")
    private fun stopScreensaver(backgroundView: View) {
        screensaver.progress = 0f
        screensaver.setMaxProgress(0.5f)
        while(screensaver.progress < 0.5f) { }
        screensaver.progress = 1f
        backgroundView.visibility = View.INVISIBLE
        screensaver.visibility = View.INVISIBLE
    }

}