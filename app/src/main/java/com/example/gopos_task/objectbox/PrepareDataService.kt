package com.example.gopos_task.objectbox

import android.util.Log
import com.example.gopos_task.model.Data
import com.example.gopos_task.model.ob_mobel.Item
import com.example.gopos_task.repository.AuthRepository
import com.example.gopos_task.repository.ItemsRepository
import com.example.gopos_task.utils.Constants
import com.example.gopos_task.utils.DispatcherProvider
import com.example.gopos_task.utils.Resource
import io.objectbox.Box
import io.objectbox.BoxStore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class PrepareDataService @Inject constructor(
    private val authRepository: AuthRepository,
    private val itemsRepository: ItemsRepository,
    private val dispatchers: DispatcherProvider,
    private val itemsBox: ItemsBox
) {
    private lateinit var token: String
    fun prepareData() {
        GlobalScope.launch(dispatchers.io) {
            prepareAuthData()
            prepareItemsData()
        }
    }

    private suspend fun prepareAuthData() {
        /*
        access data should be stored somewhere "safe" i put it in Constans only to have it in
        one place
         */
        when(val data = authRepository.getToken(
            mapOf(
                "password" to Constants.API_PASSWORD,
                "grant_type" to Constants.API_GRANT_TYPE,
                "client_secret" to Constants.API_CLIENT_SECRET,
                "client_id" to Constants.API_CLIENT_ID,
                "username" to Constants.API_USERNAME
            )
        )) {
            is Resource.Error -> Log.d("Error", data.message!!)
            is Resource.Success -> {
                token = data.data!!.access_token
            }
        }
    }

    private suspend fun prepareItemsData() {
        when(val data = itemsRepository.getItems(token)) {
            is Resource.Error -> Log.d("Error", data.message!!)
            is Resource.Success -> {
                val box = itemsBox.getItemsBox()
                data.data?.data?.forEach { item -> box.put(mapToObModel(item))}
            }
        }
    }

    private fun mapToObModel(data: Data): Item {
        /*
        This was interesting for me as the items in response were not plain
        Response Json differs when it comes to images url.
        It might have it or not
         */
        if(data.image_link != null){
            return Item(
                data.id.toLong(),
                data.category_id,
                data.image_link.default_link,
                data.image_link.small,
                data.item_group_id,
                data.joint_id,
                data.name,
                data.price.amount,
                data.price.currency,
                data.status,
                data.tax_id,
                data.updated_at)
        } else {
            return Item(
                data.id.toLong(),
                data.category_id,
                Constants.NOT_FOUND_IMAGE_URL,
                Constants.NOT_FOUND_IMAGE_URL,
                data.item_group_id,
                data.joint_id,
                data.name,
                data.price.amount,
                data.price.currency,
                data.status,
                data.tax_id,
                data.updated_at)
        }
    }
}