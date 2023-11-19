[![license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

# What's this repository about

This is a template for Balin, the browser-automation library written in Kotlin.
It integrates Cucumber, TestNg, and Allure with Balin.

## Status: [Abandonware](https://en.wikipedia.org/wiki/Abandonware)❗

Just as [Balin](https://github.com/EPadronU/balin) has become abandonware at
this point, so this template.

Since I wrote Balin a couple of years ago, I have not had the opportunity to
work under Kotlin's umbrella, much less use Balin itself. Thus, time has passed
and the library has gotten dust, cobwebs and the like. Be aware of the fact the
dependencies are already quite outdated and it's quite probable I'm not gonna be
around updating dependencies or fixing unit tests. You're more than welcome to
peek around and even use Balin, but there's no garantes of this library
keeping up-to-date for modern developing/use. Heck, even bintray doesn't exist
anymore at this point.

## Do you want to know more about Balin?

You can find more at the [project's official repository](https://github.com/EPadronU/balin).

## Do you want to demo the template?

You only need to invoke the gradle wrapper as follows:

```bash
env THREAD_COUNT=2 BROWSER=Firefox ./gradlew clean test allureReport allureServe
```

## License

Like Kotlin and Balin, this template is released under version 2.0 of the
[Apache License](LICENSE.md).
