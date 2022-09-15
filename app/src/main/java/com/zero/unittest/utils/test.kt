package com.zero.unittest.utils

import com.zero.unittest.models.DataBook
import java.util.List

class test {
    public fun test(){
        val allBook = ArrayList(
            List.of(
                DataBook(1, "Test Book", "1234567890"),
                DataBook(1, "Test Book", "1234567890"),
                DataBook(1, "Hello", "1234567890").also {
                    it.bookID = 1
                }
            )
        )
        println("test")
    }
}