package com.test.overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.junit4.createComposeRule
import com.test.overview.ui.theme.OverviewTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.overview.ui.screens.CatsApp
import com.test.overview.ui.screens.breedsList.BreedsListViewModel
import com.test.overview.ui.screens.login.LoginViewModel

class LoginFailed {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val actions = TestActions(composeTestRule)

    @Test
    fun checkErrors() {
        composeTestRule.setContent {
            OverviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    val breedsViewModel: BreedsListViewModel =
                        viewModel(factory = BreedsListViewModel.Factory)
                    val loginViewModel = LoginViewModel()
                    CatsApp(loginViewModel, breedsViewModel.breedUiState)
                }
            }
        }

        actions.inputText("Username", LoginCredentials.WRONGUSERNAME)
        actions.inputText("Password", LoginCredentials.WRONGPASSWORD)
        actions.clickLogin()
        actions.assertTextDisplayed("Username is incorrect")
        actions.assertTextDisplayed("Password is incorrect")
        }
    }