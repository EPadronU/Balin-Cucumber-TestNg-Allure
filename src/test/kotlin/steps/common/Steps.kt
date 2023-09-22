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
import com.github.epadronu.balin.extensions.find
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.qameta.allure.Attachment
import io.qameta.allure.Step
import org.testng.Assert.assertEquals
import org.testng.Assert.assertTrue
import pages.HomePage
import pages.RepositoryPage
import pages.SearchResultPage
import steps.core.StepDefinition
import utils.withWindow

/* ***************************************************************************/

/* ***************************************************************************/
class Steps : StepDefinition() {

    @Step("I navigate to the home page")
    @Attachment("I navigate to the home page", type = "image/png")
    @Given("I navigate to the home page")
    fun `I navigate to the home page`() = drive {
        to(::HomePage)
    }

    @Step("I search for {text}")
    @Attachment("I search for {text}", type = "image/png")
    @When("I search for (.*?)")
    fun `I search for {text}`(text: String) = drive {
        val page = at(::HomePage)
        page.find("button[class*='header-search-button']", 0).click()
        page.searchBar.search(text)
    }

    @Step("I open the repository's page for {text} in a new tab")
    @Attachment("I open the repository's page for {text} in a new tab", type = "image/png")
    @When("I open the repository's page for (.*?) in a new tab")
    fun `I open the repository's page for {text} in a new tab`(text: String) = drive {
        at(::SearchResultPage).openRepositoryInNewTab(text)
    }

    @Step("I should find {text} among the results")
    @Attachment("I should find {text} among the results", type = "image/png")
    @Then("I should find (.*?) among the results")
    fun `I should find {text} among the results`(text: String) = drive {
        assertTrue(at(::SearchResultPage).isResultPresent(text))
    }

    @Step("the repository's title for {text} should be the expected one")
    @Attachment("the repository's title for {text} should be the expected one", type = "image/png")
    @Then("the repository's title for (.*?) should be the expected one")
    fun `the repository's title for {text} should be the expected one`(text: String) = drive {
        at(::RepositoryPage).withWindow {
            assertEquals(title, text)
        }
    }
}
/* ***************************************************************************/
