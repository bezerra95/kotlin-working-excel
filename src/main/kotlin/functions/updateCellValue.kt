package org.example.service

import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Atualiza o valor de uma célula específica em uma planilha Excel.
 *
 * @param filePath Caminho do arquivo Excel.
 * @param sheetIndex Índice da planilha (base 0) onde a célula está localizada.
 * @param rowIndex Índice da linha (base 0) da célula a ser atualizada.
 * @param columnIndex Índice da coluna (base 0) da célula a ser atualizada.
 * @param newValue Novo valor a ser inserido na célula.
 * @return true se a célula foi atualizada com sucesso; false caso contrário.
 */
fun updateCellValue(
    filePath: String,
    sheetIndex: Int,
    rowIndex: Int,
    columnIndex: Int,
    newValue: String
): Boolean {
    FileInputStream(filePath).use { fis ->
        val workbook = WorkbookFactory.create(fis)
        val sheet = workbook.getSheetAt(sheetIndex)

        // Verifica se a linha existe; se não, cria uma nova
        val row = sheet.getRow(rowIndex) ?: sheet.createRow(rowIndex)
        // Verifica se a célula existe; se não, cria uma nova
        val cell = row.getCell(columnIndex) ?: row.createCell(columnIndex)
        // Atualiza o valor da célula
        cell.setCellValue(newValue)

        FileOutputStream(filePath).use { fos ->
            workbook.write(fos)
        }
        workbook.close()
        return true
    }
}
