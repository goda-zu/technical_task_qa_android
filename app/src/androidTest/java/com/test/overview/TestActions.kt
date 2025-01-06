package com.test.overview

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

class TestActions(private val composeTestRule: ComposeTestRule) {
    fun click(text: String) {
        composeTestRule.onNodeWithText(text).performClick()
    }

    fun clickLogin() {
        composeTestRule.onNodeWithTag("LoginButtonTestTag").performClick()
    }

    fun assertTextDisplayed(text: String) {
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    fun inputText(fieldLabel: String, input: String) {
        composeTestRule.onNodeWithText(fieldLabel).performTextInput(input)
    }
}