# Ecommerce Playground Advanced Automation

- Selenium WebDriver
- Java 17
- Cucumber (BDD)
- TestNG
- Page Object Model
- Data-driven via Scenario Outlines
- Allure Reporting
- Docker & GitHub Actions CI

## How to Run

**Locally:**
```sh
mvn clean test
```

**Via Docker:**
```sh
docker build -t selenium-cucumber-test .
docker run --rm selenium-cucumber-test
```

**CI/CD:**
Tests run on every push via GitHub Actions.