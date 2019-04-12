package com.willowtreeapps.common.repo

import com.willowtreeapps.common.Item
import com.willowtreeapps.common.PlatformDispatcher
import com.willowtreeapps.common.ItemId

/**
 * interface for providing list of items for the game.  Items are generic representation.
 * This may be used for any data source to generate questions.
 */
interface ItemRepository {
    suspend fun fetchItems(): GatewayResponse<ItemsHolder, GenericError>
}

data class ItemsHolder(val questionTitle: String,
                       val items: List<Item>)

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

