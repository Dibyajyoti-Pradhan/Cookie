# Cookie Log Parser

## Introduction

This project implements a Cookie Log Parser in Java. It's designed to parse log files containing cookie data and identify the most active cookies for a given date. The parser reads CSV files containing cookie logs, extracts relevant information, and provides insights into the most active cookies.

## Problem Statement

The Cookie Log Parser project addresses the following problem:

Given a cookie log file in the following format:

```
cookie,timestamp
AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00
SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00
5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00
AtY0laUfhglK3lC7,2018-12-09T06:19:00+00:00
SAZuXPGUrfbcn5UA,2018-12-08T22:03:00+00:00
4sMM2LxV07bPJzwf,2018-12-08T21:30:00+00:00
fbcn5UAVanZf6UtG,2018-12-08T09:30:00+00:00
4sMM2LxV07bPJzwf,2018-12-07T23:30:00+00:00
```

Write a command line program in Java to process the log file and return the most active cookie(s) for a specific day.

### Requirements

- The program should be executed with two parameters:
  - `-f <filename>` for specifying the log file to process.
  - `-d <date>` to specify the target date for identifying the most active cookie(s).
- For example, to get the most active cookie(s) for 9th Dec 2018:

  ```bash
  java Main -f cookie_log.csv -d 2018-12-09
  ```

  Expected output:

  ```
  AtY0laUfhglK3lC7
  ```

- The program defines the most active cookie as one seen the most times in the log on a given day.
- If multiple cookies meet that criterion, return all of them on separate lines.

### Assumptions

- The `-d` parameter takes dates in the UTC time zone.
- The log file can fit in memory, so we can read it in a single pass.
- Cookies are sorted by timestamp (most recent first).
- Only basic libraries should be used for parsing and processing, with additional libraries only allowed for testing, logging, and CLI parsing.

### Expectations

The solution should be production-grade, demonstrating:

- Good testing practices.
- Clean, maintainable, and extendable code.
- Knowledge of build systems, testing frameworks, and meaningful abstractions.

## Folder Structure

Here is the directory structure of the Cookie Log Parser project, outlining where the relevant files are located:

```
CookieLogParserProject/
│
├── src/ # Contains all source code files
│ ├── main/
│ │ ├── java/
│ │ │ └── com/
│ │ │ └── dibyojyoti/
│ │ │ └── cookie/
│ │ │ ├── model/ # Domain models
│ │ │ │ ├── Cookie.java
│ │ │ │ ├── CookieLog.java
│ │ │ │ └── ...
│ │ │ │
│ │ │ ├── parser/ # Log parsers
│ │ │ │ ├── LogParser.java
│ │ │ │ ├── CookieLogParser.java
│ │ │ │ └── ...
│ │ │ │
│ │ │ ├── processor/ # Log processors
│ │ │ │ ├── LogProcessor.java
│ │ │ │ ├── CookieLogProcessor.java
│ │ │ │ └── ...
│ │ │ │
│ │ │ └── Main.java # Main application class
```

## Setup

To set up and run the Cookie Log Parser, follow these steps:

1. **Install VSCode for Java:**  
   Ensure that you have VSCode installed with the necessary Java extensions. For installation instructions, visit [VSCode for Java](https://code.visualstudio.com/docs/languages/java#_install-visual-studio-code-for-java).

2. **Setup Maven:**

   ```bash
   brew install maven
   ```

3. **Open the Project:**  
   Open the `cookie` folder in VSCode.

4. **Open a Terminal in VSCode:**  
   You can open a terminal in VSCode by going to `Terminal` -> `New Terminal`.

5. **Navigate to the Directory (Check where is the root for pom.xml):**  
   Use the terminal to navigate to the `cookie` directory:

   ```bash
   cd cookie/
   ```

6. **Compile and Run the Java Code:**

   ```bash
   mvn exec:java -Dexec.args="-f src/main/resources/cookie_log.csv -d 2018-12-08"
   ```

   Replace `-f src/main/resources/cookie_log.csv -d 2018-12-08` with any other arguments as needed. A test file has been provided under the `src/main/resources` folder to play with.

7. **Run Tests:**
   ```bash
   mvn test
   ```
   This will run tests using different CSV files located in the `src/test/resources` directory.

## Tools Used

1. Maven
2. JUnit
