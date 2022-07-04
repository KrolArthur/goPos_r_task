package com.example.gopos_task.model.ob_mobel

import com.example.gopos_task.model.ImageLink
import com.example.gopos_task.model.Price
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Item(
    @Id
    var id: Long = 0,
    val category_id: Int,
    var default_link: String,
    val small: String,
    val item_group_id: Int,
    val joint_id: String,
    val name: String,
    val amount: Double,
    val currency: String,
    val status: String,
    val tax_id: Int,
    val updated_at: String
){
    override fun toString(): String =
                "Name: $name\n" +
                "Price: $amount $currency\n" +
                "Vat: $tax_id (provided only id\n" +
                "Category: $category_id (provided only id"
}
