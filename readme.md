### Automated testing with TestNG & Selenium
- Automation framework: TestNG
- UI testing: Selenium
- API testing: restAssured
- Reporting: Allure

**I. Instructions:**
- To run a test headlessly: `mvn test -Dheadless=true`
- To run a test with environment: `mvn test -Denv=dev/staging`
- To run a test suite: `mvn test -Dtest={yourTestClassName}`
- To run multiple suites: `mvn test -Dtest={yourTestClassName},{your2ndTestClassName}`
- To run a test case in a suite: `mvn test -Dtest={yourTestClassName}#{yourTestFunction}`
- To run with specific tags: `mvn test -Dgroups=smoke,regression`
- To run in parallel: `mvn test -Dparallel=classes -DthreadCount={numberofThreads} -Dsurefire.suiteXmlFiles=""`
- To generate html report: `allure generate allure-results --clean -o allure-report`
- To open html report: `allure open allure-report`
