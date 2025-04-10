package org.example.entities

data class EligibleProject(
    val number: String,
    val processNumber: String,
    val proponent: String,
    val projectName: String,
    val sli: String,
    val numberCount: String,
    val sportManifestation: String,
    val sportModality: String,
    val cnpj: String,
    val city: String,
    val state: String,
    val authorizedValue: String,
    val publicationDate: String,
    val fundraisingDeadline: String
)

// processNumber → Processo
// proponent → Proponente
// projectName → Projeto
// sli → SLI (Sem tradução específica, se for sigla mantenha assim mesmo)
// numberCount → Contagem números
// sportManifestation → Manifestação desportiva
// sportModality → Modalidade
// cnpj → CNPJ
// city → Cidade
// state → UF (Unidade Federativa)
// authorizedAmount → Valor autorizado para captação
// publicationDate → Data da publicação
// fundraisingDeadline → Período de captação até
