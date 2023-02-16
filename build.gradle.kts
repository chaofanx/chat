plugins {
    id("java-library")
    application
}

group = "xyz.chaofan"
version = "1.0-SNAPSHOT"

repositories {
    maven(url = "https://maven.aliyun.com/repository/public")
    mavenCentral()
}

dependencies {
    implementation(platform("org.noear:solon-parent:2.1.2"))
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
    runtimeOnly("com.h2database:h2:2.1.214")
    implementation("com.theokanning.openai-gpt3-java:service:0.10.0")

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
    from(configurations.compileClasspath.get()) {
        into("lib")
    }
}

application {
    mainClass.set("xyz.chaofan.AppMain")
}
