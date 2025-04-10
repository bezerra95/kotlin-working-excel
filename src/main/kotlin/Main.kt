package org.example

import org.example.entities.InventoryItem

import org.example.service.createExcelFile
import functions.readExcelFile
import org.example.service.removeItemByIndex
import org.example.service.updateCellValue
import java.io.File

fun main() {
    val filePath = "src/main/kotlin/files/Inventory.xlsx"

    // Verifica se o arquivo existe para deletar e criar outro exemplo
    val file = File("src/main/kotlin/files/Inventory.xlsx")
    if (file.exists()) file.delete()


    // Cria o Excel Apartir dos dados de uma entidade
    createExcelFile(InventoryItem.returnInventory(), filePath)
    println()

    // Remover Linha
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
