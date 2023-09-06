package com.example.ninenews.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.ninenews.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setup() {
        composeTestRule.setContent {
            DetailsScreen(url = "https://www.google.com")
        }
    }

    @Test
    fun testWebTitle() {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.news_details))
            .assertIsDisplayed()
    }

    @Test
    fun testWebView() {
        composeTestRule.onNodeWithTag("newsOnWeb")
            .assertIsDisplayed()
    }
}