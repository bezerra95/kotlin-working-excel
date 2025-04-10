package functions

import org.apache.poi.ss.usermodel.WorkbookFactory
import org.example.entities.InventoryItem
import java.io.File

/**
 * Lê um arquivo Excel (.xlsx ou .xls) contendo dados de inventário e retorna uma lista de itens.
 *
 * Cada linha da planilha representa um item com as seguintes colunas:
 * 0 - ID do produto (número)
 * 1 - Nome do produto (texto)
 * 2 - Quantidade em estoque (número)
 * 3 - Preço por unidade (número)
 *
 * @param filePath Caminho completo do arquivo Excel a ser lido
 * @return Lista de objetos InventoryItem preenchidos com os dados da planilha
 */
fun readExcelFile(filePath: String): List<InventoryItem> {

    // Cria um objeto File com o caminho recebido
    val file = File(filePath)

    // Abre o arquivo Excel com o POI. O WorkbookFactory detecta automaticamente se é .xls ou .xlsx
    val workbook = WorkbookFactory.create(file)

    // Pega a primeira aba da planilha (índice 0)
    val sheet = workbook.getSheetAt(0)

    // Lista que vai guardar todos os itens lidos da planilha
    val items = mutableListOf<InventoryItem>()

    // Percorre todas as linhas da planilha, começando da linha 1
    // Por que da linha 1? Porque normalmente a linha 0 é o cabeçalho (títulos das colunas)
    for (rowIndex in 1..sheet.lastRowNum) {
        // Obtém a linha atual da planilha
        val row = sheet.getRow(rowIndex)

        // Verifica se a linha existe (pode haver linhas em branco)
        if (row != null) {
            // Extrai os dados das colunas da linha atual
            val id = row.getCell(0).numericCellValue.toInt()             // Coluna A: ID do produto
            val productName = row.getCell(1).stringCellValue             // Coluna B: Nome do produto
            val quantity = row.getCell(2).numericCellValue.toInt()       // Coluna C: Quantidade
            val price = row.getCell(3).numericCellValue                  // Coluna D: Preço unitário

            // Cria um objeto InventoryItem com os dados lidos e adiciona à lista
            items.add(InventoryItem(id, productName, quantity, price))
        }
    }

    // Fecha o workbook para liberar os recursos do arquivo (evita travamentos no sistema)
    workbook.close()

    // Mensagem de feedback no console
    println("📖 Leitura concluída: ${items.size} itens encontrados.")

    // Retorna a lista de itens preenchida
    return items
}

