package org.example.projectExemples

import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.io.FileInputStream

fun readSpecificColumns(filePath: String) {
    val file = File(filePath)

    // Verifica se o arquivo realmente existe no caminho fornecido
    if (!file.exists()) {
        println("O arquivo não foi encontrado no caminho especificado.")
        return
    }

    FileInputStream(file).use { fis ->
        // Cria um workbook (planilha) a partir do arquivo Excel
        val workbook = WorkbookFactory.create(fis)
        val sheet = workbook.getSheetAt(0) // Pega a primeira aba da planilha

        // Lê a linha do cabeçalho (linha 1, ou seja, segunda linha da planilha)
        val headerRow = sheet.getRow(1)
        val columnNames = mutableListOf<String>()

        // Só adiciona os nomes das colunas que NÃO estão vazias
        for (cell in headerRow) {
            val value = cell.stringCellValue.trim()
            if (value.isNotEmpty()) {
                columnNames.add(value)
            }
        }

        // Percorre todas as linhas da planilha a partir da linha 1 (segunda linha)
        for (rowIndex in 1..sheet.lastRowNum) {
            val row = sheet.getRow(rowIndex)
            if (row != null) {
                var isEmptyRow = true
                val rowData = mutableListOf<String>()

                // Para cada coluna válida (com nome), pega o valor da célula
                for ((index, columnName) in columnNames.withIndex()) {
                    val cell = row.getCell(index)
                    val cellValue = cell?.toString()?.trim() ?: ""

                    if (cellValue.isNotEmpty()) {
                        isEmptyRow = false
                    }

                    rowData.add("$columnName: $cellValue")
                }

                // Só imprime se houver algum dado na linha
                if (!isEmptyRow) {
                    rowData.forEach { println(it) }
                    println() // Linha em branco para separar os registros
                }
            }
        }

        workbook.close()
    }
}
