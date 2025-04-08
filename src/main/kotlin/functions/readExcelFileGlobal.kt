package org.example.functions


import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.io.FileInputStream

/**
 * Lê e imprime o conteúdo de um arquivo Excel.
 *
 * @param filePath Caminho para o arquivo Excel.
 */
fun readExcelFileGlobal(filePath: String) {
    val file = File(filePath)

    if (!file.exists()) {
        println("O arquivo não foi encontrado no caminho especificado.")
        return
    }

    FileInputStream(file).use { fis ->
        val workbook = WorkbookFactory.create(fis)
        val sheet = workbook.getSheetAt(0) // Obtém a primeira planilha

        // Itera sobre as linhas e células
        for (row in sheet) {
            for (cell in row) {
                print("${cell.toString()} \t")
            }
            println()
        }

        workbook.close()
    }
}