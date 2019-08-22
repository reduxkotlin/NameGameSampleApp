package org.reduxkotlin.namegame

import org.reduxkotlin.namegame.common.util.NO_MATCH
import org.reduxkotlin.namegame.common.util.match
import org.junit.Assert.assertEquals
import org.junit.Test

class MatcherTest {
    val presidents2Georges = listOf("Thomas Jefferson", "Abraham Lincoln", "George Washington", "George Bush")
    val presidentsUnique = listOf("Thomas Jefferson", "Abraham Lincoln", "Alexander Hamilton", "George Bush")
    val cats = listOf("Russian Blue", "Persian Cat", "Scottish Fold", "Siamese Cat")

    @Test
    fun `choose exact match`() = testMatch("George Washington",
            presidents2Georges,
            2)

    @Test
    fun `choose exact match case insensitive`() = testMatch("george washington",
            presidents2Georges,
            2)

    @Test
    fun `choose partial match`() = testMatch("George Castansa",
            presidentsUnique,
            3)


    @Test
    fun `choose similar partial match`() = testMatch("Geoge Castansa",
            presidentsUnique,
            3)

    @Test
    fun `returns -1 is no match`() = testMatch("bob",
            presidents2Georges,
            NO_MATCH)

    @Test
    fun `match when only one name`() = testMatch("Persian",
            cats,
            1)

    @Test
    fun `match when only one name and misspelled`() = testMatch("Persain",
            cats,
            1)

    @Test
    fun `match when only one name and misspelled badly`() = testMatch("Pur sin",
            cats,
            1)

    @Test
    fun `NO_MATCH when ambiguous`() = testMatch("cat",
            cats,
            NO_MATCH)

    @Test
    fun `NO_MATCH similar partial match ambiguous`() = testMatch("Geoge Castansa",
            presidents2Georges,
            NO_MATCH)


    fun testMatch(query: String,
                  choices: List<String>,
                  expectedIndex: Int) {
        val index = match(query, choices)

        assertEquals(expectedIndex, index)
    }
}