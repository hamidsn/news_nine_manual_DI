package com.example.ninenews.fake

import com.example.ninenews.model.Assets
import com.example.ninenews.model.NewsAFR

object FakeDataSource {
    val successNews = NewsAFR(assetType = "ASSET_LIST",
        displayName = "AFR iPad Editor's Choice",
        assets = ArrayList<Assets>().apply {
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
        })

    val emptyNews = NewsAFR(
        assetType = "ASSET_LIST",
        displayName = "",
        assets = ArrayList<Assets>()
    )

}