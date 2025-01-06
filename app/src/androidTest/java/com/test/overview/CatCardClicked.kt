package com.test.overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.overview.ui.screens.CatsApp
import com.test.overview.ui.screens.breedsList.BreedsListViewModel
import com.test.overview.ui.screens.login.LoginViewModel
import com.test.overview.ui.theme.OverviewTheme
import org.junit.Rule
import org.junit.Test

class CatCardClicked {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val actions = TestActions(composeTestRule)

    @Test
    fun checkDetailsScreen() {
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

        actions.inputText("Username", LoginCredentials.USERNAME)
        actions.inputText("Password", LoginCredentials.PASSWORD)
        actions.clickLogin()
        actions.click("Abyssinian")
        actions.assertTextDisplayed("The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.")
        composeTestRule.onNode(hasClickAction()).performClick()
        actions.click("Aegean")
        composeTestRule.onNodeWithText("The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.").assertDoesNotExist()
    }
}