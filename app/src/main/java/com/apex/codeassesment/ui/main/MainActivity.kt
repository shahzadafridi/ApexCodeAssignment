package com.apex.codeassesment.ui.main

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.apex.codeassesment.R
import com.apex.codeassesment.databinding.ActivityMainBinding
import com.apex.codeassesment.di.MainComponent
import com.apex.codeassesment.model.user.User
import com.apex.codeassesment.ui.details.DetailsActivity
import com.apex.codeassesment.util.UiState
import com.apex.codeassesment.util.ex.hide
import com.apex.codeassesment.util.ex.load
import com.apex.codeassesment.util.ex.navigate
import com.apex.codeassesment.util.ex.show
import com.apex.codeassesment.util.ex.toast
import javax.inject.Inject

// TODO (3 points): Add tests for viewmodel or presenter.
// TODO (1 point): Add content description to images
// TODO (3 points): Add tests
// TODO (Optional Bonus 10 points): Make a copy of this activity with different name and convert the current layout it is using in
//  Jetpack Compose.
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel by lazy { ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java] }
    private val userAdapter: UserListAdapter by lazy {
        UserListAdapter(
            list = arrayListOf(),
            onItemClick = { user ->
                navigate<DetailsActivity>(
                    bundle = Bundle().apply {
                        putParcelable("saved-user-key", user)
                    }
                )
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as MainComponent.Injector).mainComponent.inject(this)

        binding.mainUserList.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = userAdapter
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

        binding.mainUserListButton.setOnClickListener {
            viewModel.getUsers()
        }
        observe()
    }

    private fun observe() {
        viewModel.user.observe(this) { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    binding.progressBar.show()
                }

                is UiState.Success -> {
                    binding.progressBar.hide()
                    uiState.data?.let {
                        binding.mainImage.load(it.picture.thumbnail)
                        binding.mainName.text =
                            getString(R.string.details_name, it.name.first, it.name.last)
                        binding.mainEmail.text = it.email
                    }
                }

                is UiState.Error -> {
                    toast(uiState.message ?: getString(R.string.undefined_error_message))
                    binding.progressBar.hide()
                }
            }
        }
        viewModel.users.observe(this) { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    binding.progressBar.show()
                }

                is UiState.Success -> {
                    binding.progressBar.hide()
                    userAdapter.updateList(uiState.data)
                }

                is UiState.Error -> {
                    toast(uiState.message ?: getString(R.string.undefined_error_message))
                    binding.progressBar.hide()
                }
            }
        }
    }

}
