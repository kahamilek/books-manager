import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kotlin")
    id("kotlin-spring")
    id("io.spring.dependency-management")
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":internal-api-model"))
    module(":buildSrc")
    compile(Libraries.springBootStarterActuator)
    compile(Libraries.springBootStarterWeb)
    compile(Libraries.springBootStarterDataJpa)

    implementation(Libraries.kotlinReflect)
    implementation(Libraries.kotlinJdk8)
    compile(Libraries.mysql)
    // ***********************************************************
    // JACKSON
    // ***********************************************************
    compile(Libraries.jacksonCore)
    compile(Libraries.jacksonAnnotation)
    compile(Libraries.jacksonDatabind)
    compile(Libraries.jacksonDatatypeJdk)
    compile(Libraries.jacksonKotlin)
    compile(Libraries.jacksonDatatypeJsr310)

    compile(Libraries.googleGuava)
    compile(Libraries.caffeine)
    compile(Libraries.kotlinLogging)

    testImplementation(TestLibraries.jupiter)
}

tasks.withType<KotlinCompile>().all {
    targetCompatibility = jvmTargetVersion
    kotlinOptions.jvmTarget = jvmTargetVersion
    kotlinOptions.apiVersion = kotlinVersion
    kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
}

java {
    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
        }
    }
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}