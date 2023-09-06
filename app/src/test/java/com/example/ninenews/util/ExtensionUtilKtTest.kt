package com.example.ninenews.util

import com.example.ninenews.model.Assets
import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionUtilKtTest {

    @Test
    fun test_validate_expected_array() {
        val assets = ArrayList<Assets>().apply {
            add(
                Assets(
                    url = "https://www.afr.com/rear-window/joyce-s-parmesan-waft-trumps-goyder-s-humility-cologne-20230905-p5e29l",
                    byLine = "Joe Aston",
                    theAbstract = "If the Qantas chairman had any balls at all, he would voluntarily put himself up for election at the AGM on November 3.",
                    headline = "Joyce’s parmesan waft trumps Goyder’s humility cologne",
                    timeStamp = 1693888651000

                )
            )

            add(
                Assets(
                    url = "https://www.afr.com/rich-list/forrest-empire-exodus-continues-with-two-more-ceos-20230905-p5e25y",
                    byLine = "Brad Thompson",
                    theAbstract = "Former Mincor chief executive Gabrielle Iwanow and Joost Heymeijer, the boss of the Forrest family’s hospitality and lifestyle arm, have departed.",
                    headline = "Forrest empire exodus spreads with two more CEOs",
                    timeStamp = 16938886510001
                )
            )
        }
        assertEquals(2, assets.validateAndSort().size)


    }

    @Test
    fun test_validate_wrong_timestamp_in_array() {
        val assets = ArrayList<Assets>().apply {
            add(
                Assets(
                    url = "https://www.afr.com/rear-window/joyce-s-parmesan-waft-trumps-goyder-s-humility-cologne-20230905-p5e29l",
                    byLine = "Joe Aston",
                    theAbstract = "If the Qantas chairman had any balls at all, he would voluntarily put himself up for election at the AGM on November 3.",
                    headline = "Joyce’s parmesan waft trumps Goyder’s humility cologne",
                    timeStamp = 0

                )
            )

            add(
                Assets(
                    url = "https://www.afr.com/rich-list/forrest-empire-exodus-continues-with-two-more-ceos-20230905-p5e25y",
                    byLine = "Brad Thompson",
                    theAbstract = "Former Mincor chief executive Gabrielle Iwanow and Joost Heymeijer, the boss of the Forrest family’s hospitality and lifestyle arm, have departed.",
                    headline = "Forrest empire exodus spreads with two more CEOs",
                    timeStamp = 16938886510001
                )
            )
        }
        assertEquals(1, assets.validateAndSort().size)


    }

    @Test
    fun test_sort_timestamp_in_array() {
        val assets = ArrayList<Assets>().apply {
            add(
                Assets(
                    url = "https://www.afr.com/rear-window/joyce-s-parmesan-waft-trumps-goyder-s-humility-cologne-20230905-p5e29l",
                    byLine = "Joe Aston",
                    theAbstract = "If the Qantas chairman had any balls at all, he would voluntarily put himself up for election at the AGM on November 3.",
                    headline = "Joyce’s parmesan waft trumps Goyder’s humility cologne",
                    timeStamp = 56
                )
            )

            add(
                Assets(
                    url = "https://www.afr.com/rich-list/forrest-empire-exodus-continues-with-two-more-ceos-20230905-p5e25y",
                    byLine = "Brad Thompson",
                    theAbstract = "Former Mincor chief executive Gabrielle Iwanow and Joost Heymeijer, the boss of the Forrest family’s hospitality and lifestyle arm, have departed.",
                    headline = "Forrest empire exodus spreads with two more CEOs",
                    timeStamp = 55
                )
            )
        }
        assertEquals("Brad Thompson", assets.validateAndSort()[0].byLine)
    }
}