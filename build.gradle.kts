plugins {
    java
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.diffplug.spotless") version "7.0.2"
}

allprojects {
    group = "com.furina"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

spotless {
    java {
        palantirJavaFormat() // Định dạng theo Palantir
    }
    kotlinGradle {
        target("*.gradle.kts") // default target for kotlinGradle
        ktlint() // or ktfmt() or prettier()
    }
    yaml {
        target("**/*.yaml", "**/*.yml")
        jackson()
        targetExclude("deployment/docker-compose/infra.yml")
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "com.diffplug.spotless")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    spotless {
        java {
            palantirJavaFormat() // Định dạng theo Palantir
        }
        kotlinGradle {
            target("**/*.gradle.kts") // default target for kotlinGradle
            ktlint() // or ktfmt() or prettier()
        }
        yaml {
            target("**/*.yaml", "**/*.yml")
            jackson()
            targetExclude("deployment/docker-compose/infra.yml")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
