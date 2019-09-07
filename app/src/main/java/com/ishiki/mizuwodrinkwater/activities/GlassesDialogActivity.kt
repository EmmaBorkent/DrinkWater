package com.ishiki.mizuwodrinkwater.activities

import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Glasses
import com.ishiki.mizuwodrinkwater.services.EXTRA_CHECK
import com.ishiki.mizuwodrinkwater.services.EXTRA_GLASS
import com.ishiki.mizuwodrinkwater.services.EXTRA_POSITION
import com.ishiki.mizuwodrinkwater.services.EXTRA_VOLUME
import kotlinx.android.synthetic.main.activity_glasses_dialog.*

class GlassesDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_glasses_dialog)

        // Intents always gets called in onCreate
        val glassImage = intent.getStringExtra(EXTRA_GLASS)
        Log.d("intent image", "the image is $glassImage")
        val glassVolume: Int? = intent.getIntExtra(EXTRA_VOLUME, 250)
        Log.d("intent volume", "The volume is $glassVolume")

        val resourceId = resources.getIdentifier(glassImage, "drawable", packageName)
        popup_image.setImageResource(resourceId)
        popup_volume_input.setText(glassVolume.toString())

        // You have to use toString() first, because otherwise you are converting a character
        // toInt() and the character number for 1 is 49.
        var number = glassImage[6].toString().toInt()
        Log.d("number", "the number is $number")
        popup_arrow_right.setOnClickListener {
            if (number < 10) {
                number += 1
            } else {
                number = 1
            }
            Log.d("number right", "going right the number is $number")
            popup_image.setImageResource(Glasses.imageNameToResourceId(number, this))
        }
        popup_arrow_left.setOnClickListener {
            if (number > 1) {
                number -= 1
            } else {
                number = 10
            }
            Log.d("number left", "going left the number is $number")
            popup_image.setImageResource(Glasses.imageNameToResourceId(number, this))
        }

        popup_save_button.setOnClickListener {
            val check = intent.getStringExtra(EXTRA_CHECK)
            Log.d("check", "$check was clicked")
            val image = "water0$number"
            val volume = popup_volume_input.text.toString().toInt()

            if (check == "fab") {
                Glasses.createGlass(image, volume)
                this.finish()
            } else {
                val position: Int = intent.getIntExtra(EXTRA_POSITION, 0)
                Log.d("position", "the item position is $position")
                Glasses.updateGlass(image, volume, position)
                this.finish()
            }
        }

        popup_delete_button.setOnClickListener {
            val position: Int = intent.getIntExtra(EXTRA_POSITION, 0)
            Log.d("position", "the item position is $position")
            Glasses.deleteGlass(position)
            this.finish()
        }
    }
}
