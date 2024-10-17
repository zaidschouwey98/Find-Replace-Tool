# Find-Replace-Tool

The **Find-Replace-Tool** is a command-line tool built with Java and the [picocli](https://picocli.info/) framework. It allows users to perform various operations on text, such as finding and replacing words, counting words or characters, splitting text into smaller parts, and transforming text (uppercase, lowercase, etc.).

This project uses Maven as its build tool.
## Features

1. **Input Handling**  
   Read input from a file or directly from the terminal.

2. **Text Operations**
    - Find a specific word and count its occurrences.
    - Replace a word in the text.
    - Count the total words or characters in the text.

3. **Text Splitting**  
   Split the text based on the number of lines and create multiple output files.

4. **Text Transformation**  
   Convert the text to uppercase, lowercase, or reverse the content.
## Installation

### 1. Clone the repository
```bash
git clone https://github.com/zaidschouwey98/Find-Replace-Tool.git
cd find-replace-tool
```
### 2. Run the project
1. Open the repository on Intellij IDEA or your favorite IDE.
2. Run the project with the *Run the app* launch configuration or use the *Package the app* configuration to create a .jar.
### Without IDE
You can run the project by running the following commands :
1. Make the JAR file
```bash
./mvnw dependency:go-offline clean compile package
```
2. Run the JAR file
```bash
java -jar ./target/Find-Replace-Tool-1.0-SNAPSHOT.jar -f input.txt -o output.txt -m reverse 
```
**You can display every available arguments for the application using**
``` java -jar ./target/Find-Replace-Tool-1.0-SNAPSHOT.jar -h```

### Run exemples
The last command uses the input.txt text and reverse it.

Here's some other basic command that you can use :

#### Reverse text
```bash
java -jar ./target/Find-Replace-Tool-1.0-SNAPSHOT.jar -f input.txt -o output.txt -m reverse 
```
#### To upper case
```bash
java -jar ./target/Find-Replace-Tool-1.0-SNAPSHOT.jar -f input.txt -o output.txt -m uppercase
```
*To do the opposite just write lowercase instead of uppercase.*
#### Replace the word *pomme* by *poire*
```bash
java -jar ./target/Find-Replace-Tool-1.0-SNAPSHOT.jar -f input.txt -o output.txt -m replace -w pomme -r poire
```
#### Split text every lines
```php
 java -jar ./target/Find-Replace-Tool-1.0-SNAPSHOT.jar -f input.txt -o output.txt -m split -lts 1
```

