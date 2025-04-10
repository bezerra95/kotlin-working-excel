package org.example

import org.example.entities.InventoryItem

import org.example.service.createExcelFile
import org.example.service.readExcelFile
import org.example.service.removeItemByIndex
import org.example.service.updateCellValue
import java.io.File

fun main() {
    val filePath = "src/main/kotlin/files/Inventory.xlsx"
    val apoiemais = "src/main/kotlin/files/projetos-aptos-a-captacao 17.03.25.xlsx"

    // Verifica se o arquivo existe para deletar e criar outro exemplo
    val file = File("src/main/kotlin/files/Inventory.xlsx")
    if (file.exists()) file.delete()

   // Populando entidade para teste
    val items = listOf(
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

    // Funções:

    // Cria o Excel
    createExcelFile(items, filePath)
    println()

    //Remover Linha
    // Teclado será removido
    removeItemByIndex(filePath, 1)
    println("Deletado:  InventoryItem(1, Teclado, 10, 100.0)")
    println()

    // Lê o Excel
    val readItems = readExcelFile(filePath)
    println(readItems)
    println()

    //Atualizar dados
    updateCellValue(filePath, 0, 19, 2, "20")

}
