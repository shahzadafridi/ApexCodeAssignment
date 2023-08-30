package com.apex.codeassesment.ui.main

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.apex.codeassesment.R
import com.apex.codeassesment.databinding.ActivityMainBinding
import com.apex.codeassesment.di.MainComponent
import com.apex.codeassesment.model.user.User
import com.apex.codeassesment.ui.details.DetailsActivity
import com.apex.codeassesment.util.UiState
import com.apex.codeassesment.util.ex.load
import com.apex.codeassesment.util.ex.navigate
import javax.inject.Inject

// TODO (5 points): Move calls to repository to Presenter or ViewModel.
// TODO (5 points): Use combination of sealed/Dataclasses for exposing the data required by the view from viewModel .
// TODO (3 points): Add tests for viewmodel or presenter.
// TODO (1 point): Add content description to images
// TODO (3 points): Add tests
// TODO (Optional Bonus 10 points): Make a copy of this activity with different name and convert the current layout it is using in
//  Jetpack Compose.
class MainActivity : AppCompatActivity() {

    // TODO (2 points): Convert to view binding
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ArrayAdapter<User>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel by lazy { ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as MainComponent.Injector).mainComponent.inject(this)

        adapter = ArrayAdapter<User>(this, android.R.layout.simple_list_item_1)

        binding.mainUserList.setOnItemClickListener { parent, _, position, _ ->
            navigate<DetailsActivity>(
                bundle = Bundle().apply {
                    putParcelable("saved-user-key", parent.getItemAtPosition(position) as User)
                }
            )
        }

        binding.mainSeeDetailsButton.setOnClickListener {
            viewModel.getUser()?.let {
                navigate<DetailsActivity>(
                    bundle = Bundle().apply {
                        putParcelable("saved-user-key", it)
                    }
                )

            }
        }

        binding.mainRefreshButton.setOnClickListener {
            viewModel.getUser(true)
        }

        binding.mainUserList.setOnClickListener {
            viewModel.getUsers()
        }
    }

    fun observe() {
        viewModel.user.observe(this) { uiState ->
            when (uiState) {
                is UiState.Loading -> {}
                is UiState.Success -> {
                  uiState.data?.let {
                    // TODO (1 point): Use Glide to load images after getting the data from endpoints mentioned in RemoteDataSource
                    binding.mainImage.load(it.picture.thumbnail)
                    binding.mainName.text = getString(R.string.details_name,it.name.first,it.name.last)
                    binding.mainEmail.text = it.email
                  }
                }

                is UiState.Error -> {}
            }
        }
        viewModel.users.observe(this) { uiState ->
            when (uiState) {
                is UiState.Loading -> {}
                is UiState.Success -> {
                    adapter.clear()
                    adapter.addAll(uiState.data)
                }

                is UiState.Error -> {}
            }
        }
    }

    // TODO (2 points): Convert to extenstion function.
}
