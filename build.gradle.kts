buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath(Classpath.springBootClasspath)
        classpath(Classpath.kotlinAllOpenClasspath)
        classpath(Classpath.kotlinGradleClasspath)
        classpath(Classpath.kotlinNoArgClasspath)
    }
}

subprojects{
    repositories {
        jcenter()
        google()
    }
}