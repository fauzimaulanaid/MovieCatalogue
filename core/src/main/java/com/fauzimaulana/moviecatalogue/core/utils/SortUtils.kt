package com.fauzimaulana.moviecatalogue.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object SortUtils {
    const val ASCENDING = "Ascending"
    const val DESCENDING = "Descending"
    const val RANDOM = "random"

    fun getSortedQuery(filter: String, dbName: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $dbName ")
        when (filter) {
            ASCENDING -> {
                simpleQuery.append("ORDER BY title ASC")
            }
            DESCENDING -> {
                simpleQuery.append("ORDER BY title DESC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}