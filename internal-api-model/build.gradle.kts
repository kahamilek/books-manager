import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kotlin")
    id("kotlin-spring")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(Libraries.kotlinReflect)
    implementation(Libraries.kotlinJdk8)
    // ***********************************************************
    // JACKSON
    // ***********************************************************
    implementation(Libraries.jacksonCore)
    implementation(Libraries.jacksonAnnotation)
    implementation(Libraries.jacksonDatabind)
    implementation(Libraries.jacksonDatatypeJdk)
    implementation(Libraries.jacksonKotlin)
    implementation(Libraries.jacksonDatatypeJsr310)
}

tasks.withType<KotlinCompile>().all {
    targetCompatibility = JavaVersion.VERSION_1_8.toString()
    kotlinOptions.jvmTarget = jvmTargetVersion
    kotlinOptions.apiVersion = kotlinVersion
    kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
}

java{
    dependencyManagement{
        imports{
            mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
        }
    }
}