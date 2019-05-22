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
package utils
/* ***************************************************************************/

/* ***************************************************************************/
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedCondition
import kotlin.reflect.KProperty
/* ***************************************************************************/

/* ***************************************************************************/
fun isPresenceOfElementLocated(by: By) = ExpectedCondition { webDriver ->
    webDriver?.findElement(by)?.isDisplayed ?: false
}

class ThreadLocalDelegate<T>(private val delegate: ThreadLocal<T> = ThreadLocal<T>()) {

    constructor(initialValueSupplier: () -> T) : this(ThreadLocal.withInitial(initialValueSupplier))

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = delegate.get()

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T): Unit = delegate.set(value)
}
/* ***************************************************************************/
