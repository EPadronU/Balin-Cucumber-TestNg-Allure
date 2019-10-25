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
package pages
/* ***************************************************************************/

/* ***************************************************************************/
import com.github.epadronu.balin.core.Browser
import com.github.epadronu.balin.core.Page
import com.github.epadronu.balin.extensions.`$`
import components.SearchBar
import utils.presenceOfElementLocated
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeMoreThan
/* ***************************************************************************/

/* ***************************************************************************/
class SearchResultPage(browser: Browser) : Page(browser) {

    companion object {
        private const val REPO_LIST_ITEM_LINKS_SELECTOR = "//*[contains(@class, 'repo-list-item')]//h3//a"

        private const val SEARCH_INPUT_SELECTOR = "input[placeholder='Search GitHub']"
    }

    override val at = at {
        waitFor {
            presenceOfElementLocated(By.className("codesearch-results"))
        }
    }

    val searchBar by lazy {
        `$`(SEARCH_INPUT_SELECTOR, 0).component(::SearchBar)
    }

    private val repoListItemLinks
        get() = waitFor {
            numberOfElementsToBeMoreThan(By.xpath(REPO_LIST_ITEM_LINKS_SELECTOR), 0)
        }

    fun isResultPresent(text: String) = repoListItemLinks.any { it.text == text }

    fun openRepositoryInNewTab(text: String): RepositoryPage {
        val link = repoListItemLinks.first { it.text == text }

        /* Sadly, this is broken
        Actions(driver)
            .keyDown(Keys.LEFT_CONTROL)
            .click(link)
            .keyUp(Keys.LEFT_CONTROL)
            .perform()
        */

        /* We need to introduce this tiny hack */
        js(link) {
            "arguments[0].target = '_blank';"
        }

        return link.click(::RepositoryPage)
    }
}
/* ***************************************************************************/
