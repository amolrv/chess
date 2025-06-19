# Chess Kotlin Project

This project is a Kotlin-based chess application that determines possible moves for chess pieces. It uses Gradle for build automation, Ktlint for code linting, and Kotest for testing.

## Prerequisites
- Java 21 or later
- [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) (already included)

## Getting Started

1. **Clone the repository:**
   ```sh
   git clone git@github.com:amolrv/chess.git
   cd chess
   ```

2. **Build the project:**
   ```sh
   ./gradlew build
   ```
   This will compile the code, run tests, and check code style with ktlint.
   See [Program Input and Output](#program-input-and-output) section for details.
3. **Run the application:**
   ```sh
   ./gradlew run --args="Pawn G1"
   ```
   The program will prompt you for input in the format described below.

4. **Run tests:**
   ```sh
   ./gradlew test
   ```

5. **Check code style (ktlint):**
   ```sh
   ./gradlew ktlintCheck
   ```

6. **Format code (ktlint):**
   ```sh
   ./gradlew ktlintFormat
   ```

## Program Input and Output

### Input Format
The program accepts input in the following format:
```
<ChessPiece> <Position>
```

For example:
```
Pawn G1
```

Where:
- `ChessPiece` is one of: Pawn, King, Queen, Rook, Bishop, Knight
- `Position` is a standard chess notation position (e.g., A1, B3, H8)

### Output Format
The program will output all possible cells to which the specified chess piece can move from its current position.

For example, for input `Pawn G1`, the output would be:
```
Possible moves for Pawn at G1: G2
```

## Project Structure
- `src/main/kotlin/` - Application source code
- `src/test/kotlin/` - Test code (Kotest)
- `build.gradle.kts` - Build configuration

## Notes
- All dependencies are managed via Gradle.
- The project uses Kotest for unit testing and Ktlint for code style.

---

For any issues, please open an issue in the repository.
