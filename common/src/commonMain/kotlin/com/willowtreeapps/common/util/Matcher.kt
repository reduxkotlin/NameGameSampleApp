package com.willowtreeapps.common.util

import com.willowtreeapps.common.Logger
import com.willowtreeapps.fuzzywuzzy.diffutils.FuzzySearch
import com.willowtreeapps.fuzzywuzzy.diffutils.ratio.PartialRatio

const val MATCH_SCORE_THRESHOLD = 60
const val NO_MATCH = -1

fun match(query: String, choices: List<String>): Int {
    val results = FuzzySearch.extractSorted(query, choices, PartialRatio())
    Logger.d("All ranks: $results")
    return if (results[0].score == results[1].score) {
        NO_MATCH
    } else {
        Logger.d("Match for query: $query\nfrom choices: $choices\nchoose: ${results[0]}")
        if (results[0].score > MATCH_SCORE_THRESHOLD)
            results[0].index
        else
            NO_MATCH
    }
}