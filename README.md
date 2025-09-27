# Purpose
This repository contains the instructional implementation for Lab Tutorial 1 of the *Artificial Intelligence* module (BSc Computer Engineering, UAlg). Its goals are to demonstrate:
- clear separation between a search algorithm (context) and a problem-specific layout (strategy/interface `Ilayout`);
- test-driven development using JUnit;
- a best-first search algorithm to find a shortest path between two 3×3 board configurations (target instances limited to small depths, ≤12).

# Key features
- `Ilayout` interface defining the contract for any state-space problem.
- `Board` class representing the 3×3 board with `children()`, `isGoal()`, `getK()`, `toString()`, `equals()` and `hashCode()`.
- `BestFirst` class implementing best-first search with an inner `State` structure.
- `Main` executable that reads initial and goal configurations (two lines) and prints the sequence of states, separated by a blank line, ending with the path length.
- JUnit tests (e.g. `PuzzleUnitTests`) that validate constructor behaviour, `toString()` and basic properties.
- Notes and instructions for submission to Mooshak (Contest IA 2025/26, Problem A).

# Requirements
- JDK 11 or later (JDK 17 recommended).
- Build tool optional: Maven or Gradle (recommended to run tests); can compile with `javac`/`java`.
- JUnit 5 for unit tests (if using an IDE, add JUnit 5 to the build path).

# How to compile and run (command line)
Assuming a simple structure without packages.

Compile:
```bash
# from project root
mkdir -p bin
javac -d bin src/*.java
```

Run (reads two lines from stdin: initial then goal):
```bash
# interactive
java -cp bin Main

# or using redirected input
printf "023145678\n123405678\n" | java -cp bin Main
```

Input format: each line is a sequence of 9 digits (`0` denotes the blank), e.g. `023145678`.

Expected output: each board printed as three lines, boards separated by a blank line; final line is an integer giving the path length.

# How to run tests
**With Maven (recommended):**
- Add `junit-jupiter` to the `pom.xml`.
```bash
mvn test
```

**Without Maven (IDE):**
- Add JUnit 5 to the project libraries and run `PuzzleUnitTests` as a JUnit test class.

# Design & implementation notes (teaching focus)
- **Strategy pattern:** `BestFirst` depends only on `Ilayout`. To solve another state-space problem, implement `Ilayout` for that domain.
- **State representation:** `State` encapsulates an `Ilayout layout`, a `State father`, and cost `g`. Open set is a `PriorityQueue` ordered by the evaluation function.
- **Successor generation:** `Board.children()` returns all valid moves of the blank tile.
- **Repetition detection:** Before inserting successors into open set, check presence in open/closed sets using `equals()`/`hashCode()` to avoid cycles.
- Keep `equals()` and `hashCode()` focused only on board configuration (not on parent pointers or auxiliary fields).

# Example (manual test)
Input:
```
023145678
123405678
```

Example output (format depends on `toString()` specifics and expansion order):
```
 23
145
678

123
 45
678

123
4 5
678

123
405
678

3
```
Final number is the path length. The precise intermediate boards will depend on move ordering and `toString()` formatting.
