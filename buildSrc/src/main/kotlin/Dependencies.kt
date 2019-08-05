const val kotlinVersion = "1.3.31"
const val jvmTargetVersion = "1.8"
const val springBootVersion = "2.1.4.RELEASE"

object Libraries {
    private object Versions {
        const val googleGuavaVersion = "21.0"
        const val benManerCaffeineVersion = "2.7.0"
        const val kotlinLogging = "1.6.26"
    }
    const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val mysql = "mysql:mysql-connector-java"
    const val springBootStarterActuator = "org.springframework.boot:spring-boot-starter-actuator"
    const val springBootStarterWeb = "org.springframework.boot:spring-boot-starter-web"
    const val springBootStarterDataJpa = "org.springframework.boot:spring-boot-starter-data-jpa"
    const val jacksonCore = "com.fasterxml.jackson.core:jackson-core"
    const val jacksonAnnotation = "com.fasterxml.jackson.core:jackson-annotations"
    const val jacksonDatabind = "com.fasterxml.jackson.core:jackson-databind"
    const val jacksonDatatypeJdk = "com.fasterxml.jackson.datatype:jackson-datatype-jdk8"
    const val jacksonDatatypeJsr310 = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310"
    const val jacksonKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin"
    const val googleGuava = "com.google.guava:guava:${Libraries.Versions.googleGuavaVersion}"
    const val caffeine = "com.github.ben-manes.caffeine:caffeine:${Libraries.Versions.benManerCaffeineVersion}"
    const val kotlinLogging = "io.github.microutils:kotlin-logging:${Libraries.Versions.kotlinLogging}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect"
    const val kotlinJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
}

object Classpath {

    const val springBootClasspath = "org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion"
    const val kotlinGradleClasspath = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val kotlinNoArgClasspath = "org.jetbrains.kotlin:kotlin-noarg:$kotlinVersion"
    const val kotlinAllOpenClasspath = "org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion"
}

object TestLibraries {
    private object Versions {
        const val jupiter = "5.1.0"
    }

    const val jupiter = "org.junit.jupiter:junit-jupiter-api:${TestLibraries.Versions.jupiter}"
}
