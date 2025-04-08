package org.example.service

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.example.entities.InventoryItem
import java.io.FileOutputStream

fun createExcelFile(items: List<InventoryItem>, filePath: String) {
    // Criar um novo workbook (arquivo Excel)
    val workbook: Workbook = XSSFWorkbook()

    // Criar uma aba (planilha) chamada "Inventory"
    val sheet: Sheet = workbook.createSheet("Inventory")

    // Criar cabeçalhos na primeira linha
    val headerRow = sheet.createRow(0)
    val headers = listOf("ID", "Product Name", "Quantity", "Price", "Total")
    headers.forEachIndexed { index, header ->
        headerRow.createCell(index).setCellValue(header)
    }

    // Preencher os dados
    items.forEachIndexed { i, item ->
        val row = sheet.createRow(i + 1)
        row.createCell(0).setCellValue(item.id.toDouble())
        row.createCell(1).setCellValue(item.productName)
        row.createCell(2).setCellValue(item.quantityInStock.toDouble())
        row.createCell(3).setCellValue(item.pricePerUnit)
        row.createCell(4).setCellValue(item.quantityInStock * item.pricePerUnit)
    }

    // Salvar o arquivo
    FileOutputStream(filePath).use { workbook.write(it) }
    workbook.close()
    println("📄 Arquivo Excel criado com sucesso em: $filePath")
}
