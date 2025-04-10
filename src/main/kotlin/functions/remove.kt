package org.example.service

import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Remove uma linha específica de uma planilha Excel com base no índice fornecido.
 *
 * @param filePath Caminho do arquivo Excel.
 * @param index Índice da linha a ser removida (0-based, onde 0 é a primeira linha).
 * @return true se a linha foi removida com sucesso; false se o índice for inválido.
 */


fun removeItemByIndex(filePath: String, index: Int): Boolean {
    // Abre o arquivo Excel para leitura
    FileInputStream(filePath).use { fis ->
        // Cria um workbook a partir do arquivo
        val workbook = WorkbookFactory.create(fis)
        // Obtém a primeira planilha do workbook
        val sheet = workbook.getSheetAt(0)

        // Verifica se o índice está dentro dos limites válidos
        if (index in 0..sheet.lastRowNum) {
            // Obtém a linha a ser removida
            val rowToRemove = sheet.getRow(index)
            // Remove a linha da planilha
            sheet.removeRow(rowToRemove)

            // Se a linha removida não for a última, desloca as linhas subsequentes para cima
            if (index < sheet.lastRowNum) {
                sheet.shiftRows(index + 1, sheet.lastRowNum, -1)
            }

            // Abre o arquivo Excel para escrita e salva as alterações
            FileOutputStream(filePath).use { fos ->
                workbook.write(fos)
            }
            // Fecha o workbook
            workbook.close()
            return true
        } else {
            // Fecha o workbook se o índice for inválido
            workbook.close()
            return false
        }
    }
}
