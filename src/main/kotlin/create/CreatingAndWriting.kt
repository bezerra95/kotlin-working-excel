package create

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream

// Representação de um Item de Estoque
data class InventoryItem(
    val id: Int,
    val productName: String,
    val quantityInStock: Int,
    val pricePerUnit: Double
)

// Função para gerar uma lista de itens de estoque
fun generateInventoryItems(): List<InventoryItem> {
    return listOf(
        InventoryItem(1, "Item A", 10, 19.99),
        InventoryItem(2, "Item B", 5, 34.50),
        InventoryItem(3, "Item C", 12, 9.99),
        InventoryItem(4, "Item D", 7, 25.00),
        InventoryItem(5, "Item E", 20, 5.75)
    )
}

// Função para salvar os dados de estoque em uma planilha Excel
fun saveToExcel(items: List<InventoryItem>) {
    // Criando o Workbook (documento Excel)
    val workbook: Workbook = XSSFWorkbook()
    val sheet: Sheet = workbook.createSheet("Inventory")

    // Criando cabeçalhos
    val headerRow = sheet.createRow(0)
    val headers = arrayOf("ID", "Product Name", "Quantity in Stock", "Price per Unit", "Total Value")
    headers.forEachIndexed { index, header ->
        val cell = headerRow.createCell(index)
        cell.setCellValue(header)
    }

    // Preenchendo os dados
    items.forEachIndexed { rowIndex, item ->
        val row = sheet.createRow(rowIndex + 1)  // Começa da linha 1, pois a linha 0 é o cabeçalho
        row.createCell(0).setCellValue(item.id.toDouble())
        row.createCell(1).setCellValue(item.productName)
        row.createCell(2).setCellValue(item.quantityInStock.toDouble())
        row.createCell(3).setCellValue(item.pricePerUnit)

        // Calculando o valor total (quantidade * preço unitário)
        val totalValue = item.quantityInStock * item.pricePerUnit
        row.createCell(4).setCellValue(totalValue)
    }

    // Verificando se a pasta "files" existe, caso contrário, cria
    val folder = File("/C:\\Users\\Julio\\IdeaProjects\\kotlin-working-excel\\src\\main\\kotlin\\files")
    if (!folder.exists()) {
        folder.mkdir()
    }

    // Salvando o arquivo Excel na pasta "files"
    FileOutputStream(File(folder, "Inventory.xlsx")).use { fileOut ->
        workbook.write(fileOut)
    }

    workbook.close()  // Fechando o workbook
    println("Excel file successfully created in the 'files' folder!")
}

fun main() {
    // Gerar lista de itens de estoque
    val items = generateInventoryItems()

    // Salvar os dados em uma planilha Excel
    saveToExcel(items)
}
