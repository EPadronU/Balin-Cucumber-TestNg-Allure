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
package components
/* ***************************************************************************/

/* ***************************************************************************/
import com.github.epadronu.balin.core.Component
import com.github.epadronu.balin.core.Page
import com.github.epadronu.balin.extensions.find
import pages.SearchResultPage
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
/* ***************************************************************************/

private const val SEARCH_INPUT_SELECTOR = "input[id='query-builder-test']"
private const val OPEN_ACTUAL_SEARCH_BAR_BUTTON = "button[class*='header-search-button']"

/* ***************************************************************************/
class SearchBar(page: Page, rootElement: WebElement) : Component(page, rootElement) {

    fun search(text: String): SearchResultPage {
        val searchInput: WebElement

        with(rootElement) {
            // GitHub opens another input once you click the "search bar"
            find(OPEN_ACTUAL_SEARCH_BAR_BUTTON, 0).click()
            searchInput = find(SEARCH_INPUT_SELECTOR, 0)
        }

        with(searchInput) {
            clear()
            sendKeys(text)
            sendKeys(Keys.RETURN)
        }

        waitFor {
            ExpectedConditions.urlContains(text)
        }

        return browser.at(::SearchResultPage)
    }
}
/* ***************************************************************************/
