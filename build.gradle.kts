plugins {
  kotlin("jvm") version "2.1.20"
  id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
  application // Add application plugin
}

group = "me.amol"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}
val koTest = "5.9.0"
dependencies {
  testImplementation(kotlin("test"))
  testImplementation("io.kotest:kotest-runner-junit5:$koTest")
  testImplementation("io.kotest:kotest-assertions-core:$koTest")
}

tasks.test {
  useJUnitPlatform()
}
kotlin {
  jvmToolchain(21)
}

application {
  mainClass.set("me.amol.MainKt")
  // Configure standard input for run task
  applicationDefaultJvmArgs = listOf("-Dorg.gradle.console=plain")
}

// Add a custom configuration for the run task to properly handle System.in
tasks.named<JavaExec>("run") {
  standardInput = System.`in`
}

tasks.jar {
  manifest {
    attributes["Main-Class"] = "me.amol.MainKt"
  }
  // Configure as fat jar with all dependencies
  from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
  duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
