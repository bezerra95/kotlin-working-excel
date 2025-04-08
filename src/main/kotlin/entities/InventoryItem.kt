package org.example.entities

data class InventoryItem(
    val id: Int,
    val productName: String,
    val quantityInStock: Int,
    val pricePerUnit: Double
)
