package com.willowtreeapps.common.repo

import com.willowtreeapps.common.Item
import com.willowtreeapps.common.PlatformDispatcher
import com.willowtreeapps.common.ItemId
import com.willowtreeapps.common.takeRandom

/**
 * interface for providing list of items for the game.  Items are generic representation.
 * This may be used for any data source to generate questions.
 */
interface ItemRepository {
    suspend fun fetchItems(): GatewayResponse<ItemsHolder, GenericError>
}

data class ItemsHolder(val questionTitle: String,
                       val items: List<Item>,
                       val gameResultResponses: GameResultResponses = GameResultResponses())

class ProfileItemRepository(val repo: ProfilesRepository = KtorProfilesRepository()) : ItemRepository {
    override suspend fun fetchItems(): GatewayResponse<ItemsHolder, GenericError> {
        val results = repo.profiles()
        return if (results.isSuccessful) {
            val itemHolder = ItemsHolder(questionTitle = "Who is this?",
                    items = results.response?.map { Item(id = ItemId(it.id), firstName = it.firstName, lastName = it.lastName, imageUrl = "https:${it.headshot.url}") }!!)
            GatewayResponse.createSuccess(itemHolder, 200, "")
        } else {
            GatewayResponse.createError(GenericError("Error"), 500, "")
        }
    }

}

class DogItemRepository(val repo: KtorDogsRepository = KtorDogsRepository(PlatformDispatcher)) : ItemRepository {
    override suspend fun fetchItems(): GatewayResponse<ItemsHolder, GenericError> {
        val results = repo.dogs()
        return if (results.isSuccessful) {
            val itemsHolder = ItemsHolder(questionTitle = "Name the breed",
                    items = results.response?.map {
                        Item(id = ItemId(it.breed + "_" + it.subBreed),
                                firstName = it.breed, lastName = it.subBreed ?: "",
                                imageUrl = it.imageUrl)
                    }!!)
            GatewayResponse.createSuccess(itemsHolder, 200, "")
        } else {
            GatewayResponse.createError(GenericError("Error"), 500, "")
        }
    }

}

class CatItemRepository(val repo: KtorCatsRepository = KtorCatsRepository(PlatformDispatcher)) : ItemRepository {
    override suspend fun fetchItems(): GatewayResponse<ItemsHolder, GenericError> {
        val results = repo.allBreeds()
        val itemsHolder = ItemsHolder(questionTitle = "Name the breed",
                items = results.response?.map {
                    Item(id = ItemId(it.id),
                            firstName = it.breed, lastName = "",
                            imageUrl = it.imageUrl)
                }!!)
        return if (results.isSuccessful) {
            GatewayResponse.createSuccess(itemsHolder, 200, "")
        } else {
            GatewayResponse.createError(GenericError("Error"), 500, "")
        }
    }
}

data class GameResultResponses(
        val perfect: String = perfectScoreResponses.takeRandom(),
        val good: String = goodScoreResponses.takeRandom(),
        val ok: String = okScoreResponses.takeRandom(),
        val bad: String = badScoreResponses.takeRandom(),
        val zero: String = zeroScoreResponses.takeRandom()
)

val perfectScoreResponses = listOf(
        "Perfect!!",
        "Wow, perfection!",
        "Great Job - 100% !!!",
        "Ok, you've worked here a while.")

val goodScoreResponses = listOf(
        "Nice!!",
        "Pretty Good!",
        "Good, but you still meet a few folks!!!",
        "Good Job!")

val okScoreResponses = listOf(
        "Not bad, but you can do better!!",
        "Still meeting people?",
        "Not that great \nYou could stand some practice.",
        "A few more times are I think you'll learn more names.")

val badScoreResponses = listOf(
        "Ok, do you even work here??",
        "Practice, Practice, Practice. \nYou'll get it!",
        "Wow, that is not good.  \nNew to the company?",
        "No worries, try again!")

val zeroScoreResponses = listOf(
        "Did you even try??",
        "Not even one correct??  \nWow...want to try again?",
        "Even my cat gets a few right....")
