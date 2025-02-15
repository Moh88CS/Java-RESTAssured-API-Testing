# Java RESTAssured API Testing Framework

This project is a robust API testing framework built using **Java** and **RESTAssured**. It is designed to automate the testing of RESTful APIs, ensuring reliability, scalability, and maintainability. The framework leverages industry-standard tools and practices to provide a comprehensive solution for API testing.

## Features

- **RESTAssured Integration**: Utilizes RESTAssured, a powerful Java DSL for testing RESTful APIs, to simplify request creation, response validation, and error handling.
- **Modular Design**: The framework is structured in a modular way, making it easy to extend and maintain.
- **Data-Driven Testing**: Supports data-driven testing using external data sources like JSON, CSV, or Excel files.
- **Reporting**: Generates detailed test execution reports for better visibility into test results.
- **Environment Configuration**: Easily switch between different environments (e.g., dev, staging, production) using configuration files.
- **Logging**: Integrated logging for better debugging and traceability.
- **CI/CD Integration**: Ready to be integrated into CI/CD pipelines for automated testing.

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 8 or higher.
- **Gradle**: For dependency management and build automation.
- **IDE**: IntelliJ IDEA, Eclipse, or any other Java-compatible IDE.
- **Git**: To clone the repository.

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Moh88CS/Java-RESTAssured-API-Testing.git
   cd Java-RESTAssured-API-Testing
   gradle clean test
