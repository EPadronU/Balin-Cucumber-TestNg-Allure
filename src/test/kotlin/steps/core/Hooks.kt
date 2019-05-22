/******************************************************************************
 * Copyright 2016 Edinson E. Padrón Urdaneta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *****************************************************************************/

/* ***************************************************************************/
package steps.core
/* ***************************************************************************/

/* ***************************************************************************/
import cucumber.api.Scenario
import cucumber.api.java.After
import cucumber.api.java.Before
import io.qameta.allure.Allure
import io.qameta.allure.model.Parameter
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
/* ***************************************************************************/

/* ***************************************************************************/
class Hooks : StepDefinition() {

    companion object {
        @JvmStatic
        private val targetBrowser = System
            .getenv()
            .getOrDefault("BROWSER", "firefox")
            .toLowerCase()

        @JvmStatic
        private fun produceWebDriver(): WebDriver = when (targetBrowser) {
            "chrome" -> {
                val options = ChromeOptions().apply {
                    addArguments("window-size=1024,768");
                }

                ChromeDriver(options)
            }
            "firefox" -> FirefoxDriver().apply {
                manage().window().size = Dimension(1024, 768)
            }
            else -> throw Exception("Driver not supported")
        }
    }

    @Before
    fun `Set up the allure's test and the WebDriver`(scenario: Scenario) {
        Allure.getLifecycle().updateTestCase { testResult ->
            testResult.name = scenario.name
            testResult.parameters.clear()
            testResult.parameters.add(Parameter().setName("Browser").setValue(targetBrowser.capitalize()))
        }

        driver = produceWebDriver()
    }

    @After
    fun `Add attachments on failure and quit WebDriver`(scenario: Scenario) {
        if (scenario.isFailed) {
            Allure.addAttachment("Page's URL", driver.currentUrl)
            Allure.addAttachment("Page's title", driver.title)
            Allure.addAttachment("Page's source code", "text/html", driver.pageSource, "html")
            Allure.addAttachment("View when the failure was produced", takeScreenshots().inputStream())
        }

        driver.quit()
    }
}
/* ***************************************************************************/
