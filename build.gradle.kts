/* ***************************************************************************/
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
/* ***************************************************************************/

/* ***************************************************************************/
group = "epadronu.github.com"
version = "1.0-SNAPSHOT"
/* ***************************************************************************/

/* ***************************************************************************/
plugins {
    kotlin("jvm").version("1.3.50")
    id("io.qameta.allure").version("2.8.1")
    id("com.energizedwork.webdriver-binaries").version("1.4")
}
/* ***************************************************************************/

/* ***************************************************************************/
dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("com.github.epadronu:balin:0.4.2")
    compile("org.slf4j:slf4j-api:1.7.28")

    testCompile("org.seleniumhq.selenium:selenium-chrome-driver:3.141.59")
    testCompile("org.testng:testng:7.0.0")
    testCompile("io.cucumber:cucumber-java:4.7.2")
    testCompile("io.cucumber:cucumber-testng:4.7.2")
    testCompile("io.qameta.allure:allure-cucumber4-jvm:2.12.1")

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

    version = "2.12.1"

    useTestNG {
        version = "2.12.1"
    }
}
/* ***************************************************************************/

/* ***************************************************************************/
webdriverBinaries {
    chromedriver = "114.0.5735.90"
    geckodriver = "0.33.0"
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
