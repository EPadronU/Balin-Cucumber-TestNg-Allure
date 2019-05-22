[![license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

# What's this repository about

This is a template for Balin, the browser-automation library written in Kotlin.
It integrates Cucumber, TestNg, and Allure with Balin.

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
