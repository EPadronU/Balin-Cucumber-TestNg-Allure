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
import cucumber.api.CucumberOptions
import cucumber.api.testng.AbstractTestNGCucumberTests
import org.testng.ITestContext
import org.testng.annotations.BeforeClass
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
/* ***************************************************************************/

/* ***************************************************************************/
@Test
@CucumberOptions(
        dryRun = false,
        monochrome = true,
        plugin = ["pretty"],
        features = ["src/test/resources/features"],
        glue = ["steps"]
)
class CucumberRunner : AbstractTestNGCucumberTests() {

    companion object {
        private val threadCount = System.getenv().getOrDefault("THREAD_COUNT", "2").toInt()
    }

    @BeforeClass
    fun `Set thread count`(context: ITestContext) {
        context.currentXmlTest.suite.dataProviderThreadCount = threadCount
    }

    @DataProvider(parallel = true)
    override fun scenarios(): Array<Array<Any>> {
        return super.scenarios()
    }
}
/* ***************************************************************************/
