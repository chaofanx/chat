plugins {
    id("java-library")
    application
}

group = "xyz.chaofan"
version = "1.0-SNAPSHOT"

repositories {
    maven(url = "https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
    mavenCentral()
}

dependencies {
    implementation(platform("org.noear:solon-parent:2.1.3"))
    implementation("org.noear:solon-api") {
        exclude(group = "org.noear", module = "solon.boot.jlhttp")
    }
    implementation("org.noear:solon.boot.smarthttp")
    implementation("org.noear:solon.logging.logback")
    implementation("org.noear:mybatis-plus-solon-plugin")
    implementation("org.noear:solon.auth")
    implementation("org.noear:sa-token-solon-plugin")
    implementation("cn.hutool:hutool-all:5.8.12")
    implementation("com.zaxxer:HikariCP:5.0.1")
    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    implementation("mysql:mysql-connector-java:8.0.32")

}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs = listOf(
        "-parameters",
    )
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "xyz.chaofan.AppMain",
            "Class-Path" to configurations.compileClasspath.get().joinToString(" ") { it.name }
        )
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map {
        if (it.isDirectory) it else zipTree(it)
    })
    val sourcesMain = sourceSets.main.get()
    sourcesMain.allSource.forEach { println("add from sources: ${it.name}") }
    from(sourcesMain.output)
}

application {
    mainClass.set("xyz.chaofan.AppMain")
}
