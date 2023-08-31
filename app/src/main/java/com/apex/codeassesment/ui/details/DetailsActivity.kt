package com.apex.codeassesment.ui.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.apex.codeassesment.R
import com.apex.codeassesment.databinding.ActivityDetailsBinding
import com.apex.codeassesment.model.user.User
import com.apex.codeassesment.ui.location.LocationActivity
import com.apex.codeassesment.util.ex.load
import com.apex.codeassesment.util.ex.navigate
import com.apex.codeassesment.util.ex.parcelable

// TODO (1 point): Add content description to images
// TODO (2 points): Add tests
class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userObj: User? = parcelable("saved-user-key",true)

        userObj?.let { user ->
            binding.detailsImage.load(user.picture.large)
            binding.detailsName.text = getString(R.string.details_name, user.name.first, user.name.last)
            binding.detailsEmail.text = getString(R.string.details_email, user.gender)
            binding.detailsAge.setText(user.dob.age)
            val coordinates = user.location.coordinates
            binding.detailsLocation.text = getString(
                R.string.details_location,
                coordinates.latitude,
                coordinates.longitude
            )
            binding.detailsLocationButton.setOnClickListener { x: View? ->
                navigate<LocationActivity>(
                    bundle = Bundle().apply {
                        putString("user-latitude-key", coordinates.latitude)
                        putString("user-longitude-key", coordinates.longitude)
                    }
                )
            }
        }
    }
}
