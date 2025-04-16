package org.example.projectExamples

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {
    val file = File("src/main/kotlin/files/projetos-aptos-a-captacao.xlsx")

    FileInputStream(file).use { fis ->
        val workbook: Workbook = XSSFWorkbook(fis)
        val sheet = workbook.getSheetAt(0)

        // Mantém as 10 primeiras linhas (índices de 0 a 9), remove o resto
        for (i in sheet.lastRowNum downTo 10) {
            val row = sheet.getRow(i)
            if (row != null) {
                sheet.removeRow(row)
            }
        }

        // Regrava o arquivo com as alterações
        FileOutputStream(file).use { fos ->
            workbook.write(fos)
        }

        workbook.close()
    }

    println("🧹 Planilha limpa com sucesso (mantidas as 10 primeiras linhas).")
}
