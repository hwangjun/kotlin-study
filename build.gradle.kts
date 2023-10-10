import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    kotlin("plugin.jpa") version "1.8.21"
    kotlin("plugin.allopen") version "1.8.21"
    // 코틀린 공식문서에서는 ksp사용을 권장하지만 아직 스프링에서 지원 안하는걸로 알고 있음
    // kapt는 자바 16 이상부터 지원 안함 x 그래서 추가 확인 필요하나 스프링 공식 가이드에서는 kapt를 사용
    // 스프링 깃허브 이슈 및 코틀린 이슈 참고 함
    kotlin("kapt") version "1.8.21"
}

group = "com.d1t"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

// 코틀린에서는 기본적으로 모든 클래스가 final 이기 때문에 open 을 붙여줘야 상속이 가능함 (JPA 에서는 상속이 필요하기 때문에)
allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

dependencies {
    // 코틀린 추가 구성
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // querydsl 추가 구성 Spring 3.0 부터 javax에서 jakarta로 변경되어 querydsl 아래와 같이 설정 필요
    implementation("com.querydsl:querydsl-core:5.0.0")
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")

    // h2
    runtimeOnly("com.h2database:h2")

    // 스프링 부트 설정 프로세서
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    // kotest 라이브러리 (코틀린 테스트 라이브러리)
    implementation("io.kotest:kotest-property-jvm:5.6.1")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.6.1")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.6.1")
    implementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")

    // JWT 인증
    implementation("com.auth0:java-jwt:3.19.2")

    // kotlin logging
    implementation("io.github.microutils:kotlin-logging:3.0.5")

    // mockk 라이브러리 (코틀린 모킹 라이브러리)
    testImplementation("io.mockk:mockk:1.13.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}