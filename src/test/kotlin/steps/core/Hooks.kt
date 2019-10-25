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
import utils.WebDriverBuilderFactory
import io.cucumber.core.api.Scenario
import io.cucumber.java.After
import io.cucumber.java.Before
import io.qameta.allure.Allure
import io.qameta.allure.model.Parameter
import org.openqa.selenium.Dimension
/* ***************************************************************************/

/* ***************************************************************************/
class Hooks : StepDefinition() {

    companion object {
        @JvmStatic
        private val targetWebDriver = System
            .getenv()
            .getOrDefault("WEBDRIVER", "firefox")
            .toLowerCase()
    }

    @Before
    fun `Set up the allure's test and the WebDriver`(scenario: Scenario) {
        Allure.getLifecycle().updateTestCase { testResult ->
            testResult.name = scenario.name
            testResult.parameters.clear()
            testResult.parameters.add(Parameter().setName("Browser").setValue(targetWebDriver.capitalize()))
        }

        driver = WebDriverBuilderFactory
            .createFromName(targetWebDriver)
            .windowSize(Dimension(1024, 768))
            .build()
    }

    @After
    fun `Add attachments on failure and quit WebDriver`(scenario: Scenario) {
        if (scenario.isFailed) {
            Allure.addAttachment("Page's URL", driver.currentUrl as String)
            Allure.addAttachment("Page's title", driver.title as String)
            Allure.addAttachment("View when the failure was produced", takeScreenshots().inputStream())
        }

        driver.quit()
    }
}
/* ***************************************************************************/
