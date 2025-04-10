package org.example.entities

data class InventoryItem(
    val id: Int,
    val productName: String,
    val quantityInStock: Int,
    val pricePerUnit: Double
) {
    // O campanion object foi adicionado pra simular uma base de dados
    companion object {
        fun returnInventory(): List<InventoryItem> {
            return listOf(
                InventoryItem(1, "Teclado", 10, 100.0),
                InventoryItem(2, "Mouse", 20, 50.0),
                InventoryItem(3, "Monitor", 5, 900.0),
                InventoryItem(4, "Gabinete", 7, 300.0),
                InventoryItem(5, "Fonte de Alimentação", 15, 250.0),
                InventoryItem(6, "Placa-Mãe", 10, 500.0),
                InventoryItem(7, "Processador", 8, 1200.0),
                InventoryItem(8, "Memória RAM", 12, 400.0),
                InventoryItem(9, "HD Externo", 9, 350.0),
                InventoryItem(10, "SSD", 14, 600.0),
                InventoryItem(11, "Placa de Vídeo", 6, 1500.0),
                InventoryItem(12, "Webcam", 11, 200.0),
                InventoryItem(13, "Impressora", 4, 700.0),
                InventoryItem(14, "Scanner", 3, 650.0),
                InventoryItem(15, "Notebook", 5, 3500.0),
                InventoryItem(16, "Tablet", 7, 1800.0),
                InventoryItem(17, "Smartphone", 10, 2500.0),
                InventoryItem(18, "Cadeira de Escritório", 8, 850.0),
                InventoryItem(19, "Mesa de Escritório", 6, 1200.0),
                InventoryItem(20, "Luminária", 15, 150.0)
            )
        }
    }
}

