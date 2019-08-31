package com.ishiki.mizuwodrinkwater.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_GLASS
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_VOLUME
import kotlinx.android.synthetic.main.activity_glasses_popup.*

class GlassesPopupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_glasses_popup)
        setGlass()
    }

    private fun setGlass() {
        val glassImage = intent.getStringExtra(EXTRA_GLASS)
        val resourceId = resources.getIdentifier(glassImage, "drawable", packageName)
        popup_image.setImageResource(resourceId)

        val glassVolume = intent.getIntExtra(EXTRA_VOLUME, 250)
        popup_volume_input.setText(glassVolume.toString())
    }
}
