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
package steps.common
/* ***************************************************************************/

/* ***************************************************************************/
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.qameta.allure.Attachment
import io.qameta.allure.Step
import org.testng.Assert
import pages.HomePage
import pages.SearchResultPage
import steps.core.StepDefinition
/* ***************************************************************************/

/* ***************************************************************************/
class Steps : StepDefinition() {

    @Step
    @Attachment(value = "I navigate to the home page", type = "image/png")
    @Given("I navigate to the home page")
    fun `I navigate to the home page`() = drive {
        to(::HomePage)
    }

    @Step("I search for {text}")
    @Attachment(value = "I search for {text}", type = "image/png")
    @When("I search for (.*?)")
    fun `I search for {text}`(text: String) = drive {
        at(::HomePage).search(text)
    }

    @Step("I should find {text} among the results")
    @Attachment(value = "I should find {text} among the results", type = "image/png")
    @Then("I should find (.*?) among the results")
    fun `I should find {text} among the results`(text: String) = drive {
        Assert.assertTrue(at(::SearchResultPage).isResultPresent(text))
    }
}
/* ***************************************************************************/
