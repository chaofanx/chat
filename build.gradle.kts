plugins {
    id("java-library")
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
}
