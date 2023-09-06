package com.example.ninenews.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.ninenews.NineNewsActions
import com.example.ninenews.NineNewsNavGraph
import com.example.ninenews.R
import com.example.ninenews.Screen
import com.example.ninenews.model.Assets
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testHomeScreenLoading() {
        composeTestRule.setContent {
            HomeScreen(newsUiState = NewsUiState.Loading,
                onItemClicked = {},
                retryAction = {})
        }
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.loading))
            .assertIsDisplayed()
    }

    @Test
    fun testHomeScreenError() {
        composeTestRule.setContent {
            HomeScreen(newsUiState = NewsUiState.Error("some errors"),
                onItemClicked = {},
                retryAction = {})
        }
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.retry))
            .assertIsDisplayed()
    }

    @Test
    fun testHomeScreenEmpty() {
        composeTestRule.setContent {
            HomeScreen(newsUiState = NewsUiState.Empty,
                onItemClicked = {},
                retryAction = {})
        }
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.empty_list))
            .assertIsDisplayed()
    }

    @Test
    fun testHomeScreenSuccess() {
        val assets = ArrayList<Assets>().apply {
            add(
                Assets(
                    url = "https://www.afr.com/rear-window/joyce-s-parmesan-waft-trumps-goyder-s-humility-cologne-20230905-p5e29l",
                    byLine = "Joe Aston",
                    theAbstract = "If the Qantas chairman had any balls at all, he would voluntarily put himself up for election at the AGM on November 3.",
                    headline = "Joyce’s parmesan waft trumps Goyder’s humility cologne",
                    timeStamp = 1693914909
                )
            )

            add(
                Assets(
                    url = "https://www.afr.com/rich-list/forrest-empire-exodus-continues-with-two-more-ceos-20230905-p5e25y",
                    byLine = "Brad Thompson",
                    theAbstract = "Former Mincor chief executive Gabrielle Iwanow and Joost Heymeijer, the boss of the Forrest family’s hospitality and lifestyle arm, have departed.",
                    headline = "Forrest empire exodus spreads with two more CEOs",
                    timeStamp = 1693888651000
                )
            )
        }

        composeTestRule.setContent {
            HomeScreen(newsUiState = NewsUiState.Success(assets),
                onItemClicked = {},
                retryAction = {})
        }
        composeTestRule.onNodeWithText(assets[0].byLine ?: "")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(assets[1].byLine ?: "")
            .assertIsDisplayed()
    }

    @Test
    fun testNavHostStartDestination() {
        var navController: TestNavHostController
        composeTestRule.setContent {

            navController = TestNavHostController(LocalContext.current)
            val navigationActions = NineNewsActions(navController)

            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NineNewsNavGraph(
                navController = navController,
                startDestination = Screen.Home.route,
                navigateToDetail = navigationActions.navigateToDetail,
                navigateToHome = navigationActions.navigateToHome
            )
        }
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.news_list))
            .assertIsDisplayed()

    }

    @Test
    fun testNavigationHomeToDetail() {
        val assets = ArrayList<Assets>().apply {
            add(
                Assets(
                    url = "https://www.afr.com/rear-window/joyce-s-parmesan-waft-trumps-goyder-s-humility-cologne-20230905-p5e29l",
                    byLine = "Joe Aston",
                    theAbstract = "If the Qantas chairman had any balls at all, he would voluntarily put himself up for election at the AGM on November 3.",
                    headline = "Joyce’s parmesan waft trumps Goyder’s humility cologne",
                    timeStamp = 1693914909
                )
            )

            add(
                Assets(
                    url = "https://www.afr.com/rich-list/forrest-empire-exodus-continues-with-two-more-ceos-20230905-p5e25y",
                    byLine = "Brad Thompson",
                    theAbstract = "Former Mincor chief executive Gabrielle Iwanow and Joost Heymeijer, the boss of the Forrest family’s hospitality and lifestyle arm, have departed.",
                    headline = "Forrest empire exodus spreads with two more CEOs",
                    timeStamp = 1693888651000
                )
            )
        }
        composeTestRule.setContent {
            HomeScreen(newsUiState = NewsUiState.Success(assets),
                onItemClicked = {},
                retryAction = {})
        }
        composeTestRule.onNodeWithText(assets[0].byLine ?: "")
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(assets[1].timeStamp.toString(), useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(assets[1].timeStamp.toString(), useUnmergedTree = true)
            .performClick()
        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("newsOnWeb", useUnmergedTree = true)
                .fetchSemanticsNodes().isEmpty()
        }
    }

}