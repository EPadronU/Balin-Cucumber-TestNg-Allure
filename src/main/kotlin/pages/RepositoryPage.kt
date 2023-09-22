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
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions.*

/* ***************************************************************************/

/* ***************************************************************************/
class RepositoryPage(browser: Browser) : Page(browser) {

    companion object {
        private const val AUTHOR_LINK = "[itemprop='author']"
        private const val REPO_NAME_LINK = "[itemprop='name']"
    }

    private val authorLink by lazy {
        waitFor { elementToBeClickable((By.cssSelector(AUTHOR_LINK))) }
    }

    private val repoLink by lazy {
        waitFor { elementToBeClickable((By.cssSelector(REPO_NAME_LINK))) }
    }

    val title: String by lazy {
        "${authorLink.text}/${repoLink.text}"
    }
}
/* ***************************************************************************/
