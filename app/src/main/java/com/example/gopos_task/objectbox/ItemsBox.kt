package com.example.gopos_task.objectbox

import com.example.gopos_task.model.ob_mobel.Item
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.objectbox.Box
import io.objectbox.BoxStore
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class ItemsBox @Inject constructor(
    private val box: BoxStore
){
    fun getItemsBox(): Box<Item> {
        return box.boxFor(Item::class.java)
    }
}