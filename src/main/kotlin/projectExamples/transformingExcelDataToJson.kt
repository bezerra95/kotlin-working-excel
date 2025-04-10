import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.example.entities.EligibleProject
import java.io.File
import java.io.FileInputStream


// Função para gerar um arquivo Json atráves de dados via Planilha excel

fun main() {
    // Caminho do arquivo Excel de entrada
    val file = File("src/main/kotlin/files/projetos-aptos-a-captacao.xlsx")

    // Caminho do arquivo JSON de saída
    val outputFile = File("src/main/kotlin/files/dados_extraidos.json")

    // Lista para armazenar todos os projetos lidos da planilha
    val projects = mutableListOf<EligibleProject>()

    // Leitura do arquivo Excel
    FileInputStream(file).use { fis ->
        val workbook: Workbook = XSSFWorkbook(fis)
        val sheet: Sheet = workbook.getSheetAt(0) // Pegamos a primeira aba da planilha
        val startRowIndex = 2 // Começa a ler da linha 3 (índice 2), pulando cabeçalhos

        for (rowIndex in startRowIndex..sheet.lastRowNum) {
            val row = sheet.getRow(rowIndex)

            // Verifica se todas as células da linha estão vazias
            val isRowEmpty = (0..13).all { index ->
                getCellValue(row, index).isBlank()
            }
            if (isRowEmpty) continue // pula linha vazia

            try {
                // Mapeia os dados da linha para o objeto EligibleProject
                val project = EligibleProject(
                    number = getCellValue(row, 0),
                    processNumber = getCellValue(row, 1),
                    proponent = getCellValue(row, 2),
                    projectName = getCellValue(row, 3),
                    sli = getCellValue(row, 4),
                    numberCount = getCellValue(row, 5),
                    sportManifestation = getCellValue(row, 6),
                    sportModality = getCellValue(row, 7),
                    cnpj = getCellValue(row, 8),
                    city = getCellValue(row, 9),
                    state = getCellValue(row, 10),
                    authorizedValue = getCellValue(row, 11),
                    publicationDate = getCellValue(row, 12),
                    fundraisingDeadline = getCellValue(row, 13)
                )

                // Adiciona o projeto na lista
                projects.add(project)

            } catch (e: Exception) {
                // Exibe erro no terminal se houver falha ao processar alguma linha
                println("❌ Erro na linha $rowIndex: ${e.message}")
            }
        }

        // Fecha o arquivo Excel após leitura
        workbook.close()
    }

    // Cria um objeto que transforma a lista em JSON
    val objectMapper = jacksonObjectMapper()

    // Converte a lista de projetos para JSON (em formato bonito)
    val jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(projects)

    // Salva o JSON no arquivo de saída
    outputFile.writeText(jsonOutput)

    // Mensagem final
    println("✅ JSON gerado com sucesso em: ${outputFile.absolutePath}")
}

// Função para extrair o valor de uma célula como String, mesmo que seja número ou data
fun getCellValue(row: Row, index: Int): String {
    val cell = row.getCell(index, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)

    return when (cell.cellType) {
        CellType.STRING -> cell.stringCellValue.trim()
        CellType.NUMERIC -> {
            if (DateUtil.isCellDateFormatted(cell)) {
                // Se for data, retorna no formato padrão ISO
                cell.localDateTimeCellValue.toLocalDate().toString()
            } else {
                // Se for número, converte para string
                cell.numericCellValue.toString()
            }
        }
        CellType.BOOLEAN -> cell.booleanCellValue.toString()
        else -> ""
    }
}
