package com.example.ninenews.util

import com.example.ninenews.model.Assets

/**
 * Display latest article first in the list, use article's 'timeStamp'
 */
fun ArrayList<Assets>.validateAndSort(): ArrayList<Assets> {
    return try {
        ArrayList((this.filter { it.timeStamp?.toInt() != 0 }
            .distinctBy { it.timeStamp } as ArrayList<Assets>)
            .sortedWith(compareBy { it.timeStamp }
            )
        )

    } catch (e: NullPointerException) {
        arrayListOf()
    }
}