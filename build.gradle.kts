import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

buildscript {
    configurations.classpath {
        resolutionStrategy.activateDependencyLocking()
    }
    dependencies {
        classpath("com.atradius.andromeda.sdk:openapi-generator-template:0.1.0-SNAPSHOT")
        classpath("commons-codec:commons-codec:1.11")
        classpath("commons-logging:commons-logging:1.2")
        classpath("org.apache.httpcomponents:httpcore:4.4.13")
    }
}

plugins {
    jacoco
    `java-library`
    `maven-publish`
    id("com.diffplug.spotless") version ("6.+")
    id("org.asciidoctor.jvm.convert") version("3.+")
    id("org.asciidoctor.jvm.pdf") version("3.+")
    id("org.openapi.generator") version("6.+")
    id("org.owasp.dependencycheck") version ("7.+")
    id("org.sonarqube") version("3.+")
    id("pl.allegro.tech.build.axion-release") version("1.+")
}

project.version = scmVersion.version

apply(from = "gradle/documentation.gradle")

java{
    sourceCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
}

repositories {
    mavenLocal()
}

dependencyLocking {
    lockAllConfigurations()
}

dependencies {
    api("com.atradius.andromeda.sdk:openapi-generator-common:0.1.0-SNAPSHOT")

    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.+"))

    implementation("com.google.code.findbugs:jsr305:3.+")
    implementation("io.swagger.core.v3:swagger-annotations:2.+")
    implementation("org.openapitools:jackson-databind-nullable:0.2.+")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.springframework:spring-core")
    implementation("org.springframework:spring-web")
    implementation("org.springframework:spring-webmvc")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.+")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.+")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
    }
    repositories {
        maven{
            name = "artifactory"
            url = if (project.version.toString().endsWith("-SNAPSHOT")) {
                uri("https://lpart001.atradiusnet.com:443/artifactory/libs-snapshots/")
            } else {
                uri("https://lpart001.atradiusnet.com:443/artifactory/libs-releases/")
            }
            credentials(PasswordCredentials::class)
        }
    }
}

openApiGenerate {
    generatorName.set("atradius-spring")
    inputSpec.set("$rootDir/src/main/specs/openapi.yaml")
    outputDir.set("$buildDir/generatedSources")
    apiPackage.set("com.atradius.risk-visualization-data.api")
    modelPackage.set("com.atradius.risk-visualization-data.api.model")
    configOptions.set(mapOf(
        "dateLibrary" to "java8-localdatetime",
        "implicitHeaders" to "true",
        "openApiNullable" to "false",
        "useBeanValidation" to "true",
        "useSpringBoot3" to "true",
        "useTags" to "true"
    ))
    ignoreFileOverride.set("$rootDir/src/main/specs/.openapi-generator-ignore")
}

tasks.register("apiaryGenerate", GenerateTask::class) {
    group = "OpenAPI Tools"
    description = "Generate Apiary API"
    generatorName.set("atradius-spring")
    inputSpec.set("$rootDir/src/main/specs/apimodel-country.yaml")
    outputDir.set("$buildDir/generatedSources")
    apiPackage.set("com.atradius.apiary.risk-visualization-data.api")
    modelPackage.set("com.atradius.apiary.risk-visualization-data.api.model")
    configOptions.set(mapOf(
        "dateLibrary" to "java8-localdatetime",
        "implicitHeaders" to "true",
        "openApiNullable" to "false",
        "useBeanValidation" to "true",
        "useSpringBoot3" to "true",
        "useTags" to "true"
    ))
}

sourceSets.main {
    java.srcDir("$buildDir/generatedSources/src/main/java")
    resources.srcDir("$buildDir/generatedSources/src/main/resources")
}

tasks.named("compileJava") {
    dependsOn("openApiGenerate")
}

tasks.named("processResources") {
    dependsOn("openApiGenerate")
}

tasks.named("sourcesJar") {
    dependsOn("openApiGenerate")
}

spotless {
    java {
        target("src/main/java/**/*.java")

        googleJavaFormat()
    }
}

dependencyCheck {
    suppressionFile = "${project.rootDir}/gradle/suppressions.xml"
}

tasks.withType<org.sonarqube.gradle.SonarTask>() {
    dependsOn("jacocoTestReport")
}

tasks.jacocoTestReport {
    reports {
        html.required.set(true)
        xml.required.set(true)
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", project.name)
    }
}