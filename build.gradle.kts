/* ***************************************************************************/
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
/* ***************************************************************************/

/* ***************************************************************************/
group = "epadronu.github.com"
version = "1.0-SNAPSHOT"
/* ***************************************************************************/

/* ***************************************************************************/
plugins {
    kotlin("jvm").version("1.3.21")
    id("io.qameta.allure").version("2.7.0")
    id("com.energizedwork.webdriver-binaries").version("1.4")
}
/* ***************************************************************************/

/* ***************************************************************************/
dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("com.github.epadronu:balin:0.4.1")
    compile("org.slf4j:slf4j-api:1.7.26")

    testCompile("org.seleniumhq.selenium:selenium-chrome-driver:3.141.59")
    testCompile("org.testng:testng:6.14.3")
    testCompile("io.cucumber:cucumber-java:4.3.1")
    testCompile("io.cucumber:cucumber-testng:4.3.1")
    testCompile("io.qameta.allure:allure-cucumber4-jvm:2.11.0")

    testRuntime("ch.qos.logback:logback-classic:1.2.3")
}

repositories {
    jcenter()
    mavenCentral()
}
/* ***************************************************************************/

/* ***************************************************************************/
allure {
    autoconfigure = true

    version = "2.11.0"

    useTestNG {
        version = "2.11.0"
    }
}
/* ***************************************************************************/

/* ***************************************************************************/
webdriverBinaries {
    chromedriver = "2.46"
    geckodriver = "0.24.0"
}
/* ***************************************************************************/

/* ***************************************************************************/
tasks.withType(Test::class.java).all {
    ignoreFailures = true

    useTestNG()
}
/* ***************************************************************************/

/* ***************************************************************************/
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
/* ***************************************************************************/
