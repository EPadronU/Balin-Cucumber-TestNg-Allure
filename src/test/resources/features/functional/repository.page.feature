###############################################################################
# Copyright 2016 Edinson E. Padrón Urdaneta
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
##############################################################################

@functional
Feature: Repositories' page

  Scenario Outline: When open in a new tab, the repository's title for <repo name> should be the expected one
    Given I navigate to the home page
    When I search for <search term>
    And I open the repository's page for <repo name> in a new tab
    Then the repository's title for <repo name> should be the expected one
    Examples:
      | search term | repo name        |
      | kotlin      | JetBrains/kotlin |
      | balin       | EPadronU/balin   |
