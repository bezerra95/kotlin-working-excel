plugins {
    kotlin("jvm") version "2.1.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // Apache biblioteca excel
    implementation("org.apache.poi:poi:5.3.0")
    implementation("org.apache.poi:poi-ooxml:5.3.0")

    // Para manipulação de JSON
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}