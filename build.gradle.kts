import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.spring") version "1.5.21"
    kotlin("plugin.jpa") version "1.5.21"
    kotlin("plugin.allopen") version "1.5.21"
    kotlin("plugin.noarg") version "1.5.21"
    id("com.netflix.dgs.codegen") version "5.0.6"
}

group = "com.avalos"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_16

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Spring Boot's JPA support
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Graphql stuff
    implementation("com.graphql-java:graphql-java-extended-scalars:16.0.1")
    implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:latest.release"))
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
    implementation("com.netflix.graphql.dgs:graphql-dgs-pagination")
    implementation("com.netflix.graphql.dgs:graphql-dgs-subscriptions-websockets-autoconfigure:latest.release")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")

    // Testing deps
    testImplementation("com.ninja-squad:springmockk:3.0.1") {
        exclude(module = "mockito-core")
    }
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(group = "com.h2database", name = "h2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "16"
    }
}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
    generateClient = true
    packageName = "com.avalos.chatty.generated"
    language = "kotlin"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
