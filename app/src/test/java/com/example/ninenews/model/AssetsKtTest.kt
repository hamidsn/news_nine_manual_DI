package com.example.ninenews.model

import org.junit.Assert.assertEquals
import org.junit.Test

class AssetsKtTest {

    @Test
    fun test_sort_image_width() {
        val relatedImages = ArrayList<RelatedImages>().apply {
            add(
                RelatedImages(width = 88)
            )
            add(
                RelatedImages(width = 87)
            )
            add(
                RelatedImages(width = 89)
            )
        }
        val assets = ArrayList<Assets>().apply {
            add(
                Assets(
                    url = "https://www.afr.com/rear-window/joyce-s-parmesan-waft-trumps-goyder-s-humility-cologne-20230905-p5e29l",
                    byLine = "Joe Aston",
                    theAbstract = "If the Qantas chairman had any balls at all, he would voluntarily put himself up for election at the AGM on November 3.",
                    headline = "Joyce’s parmesan waft trumps Goyder’s humility cologne",
                    timeStamp = 1693888651000,
                    relatedImages = relatedImages
                )
            )
        }
        assertEquals(87, assets[0].sortByImageWidth().relatedImages.last().width)
    }
}