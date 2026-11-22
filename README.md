# Advent of Code (Java)

A [Spring Boot 4.2.0](https://spring.io/projects/spring-boot) + [Project Reactor](https://projectreactor.io/) starter for solving [Advent of Code](https://adventofcode.com/) puzzles in Java 25. There is no web stack: it is a command-line application.

## Layout

```
src/main/java/aoc/
  AdventOfCodeJavaApplication.java     Spring Boot entry point
  utils/                               shared puzzle infrastructure
    Puzzle.java                        contract implemented by every day
    PuzzleInput.java                   classpath input -> Flux<String>
    PuzzleMain.java                    shared entry point called by every day's main
    PuzzleRunner.java                  selects/runs puzzles from CLI args
  years/
    year2022/day01/Day01.java          empty implementation stub
    year2025/day01/Day01.java          example solution
    year2026/day01/Day01.java          example solution
src/test/java/aoc/
  utils/                               tests for shared utilities
  years/.../dayNN/DayNNTest.java       one test class per day
src/main/resources/years/<year>/day<NN>/input.txt      your real puzzle input
src/test/resources/years/<year>/day<NN>/example.txt    sample input from the puzzle
```

`main` holds solutions and real input; `test` holds sample-driven tests; `utils` holds
the reusable plumbing shared across all years.

Personal input files (`src/main/resources/years/**/input.txt`) are gitignored. The
2022 day 01 scaffold contains the puzzle's sample data in the test resource and an
empty `input.txt` ready to paste your personal input into.

## Run

Every day has its own `main`, so in an IDE you can right-click `DayNN` and run
`DayNN.main()`. From the command line, run a day's main with:

```bash
./gradlew runDay -Pyear=2022 -Pday=1
./gradlew runDay -Pyear=2022 -Pday=1 --args='2'   # one part
```

The Spring Boot runner is still available and lists registered puzzles with no args:

```bash
./gradlew bootRun
./gradlew bootRun --args='2026 1'
./gradlew bootRun --args='2026 1 2'
```

Run the tests:

```bash
./gradlew test
```

## Add a new day

Run the scaffold task:

```bash
./gradlew newDay -Pyear=2022 -Pday=2
```

It creates an empty `DayNN` stub, an empty `DayNNTest`, and the two text files every day has:

- `src/test/resources/years/<YEAR>/day<NN>/example.txt` — the example from the puzzle page, committed and used by tests.
- `src/main/resources/years/<YEAR>/day<NN>/input.txt` — empty and gitignored; paste your personal input here.

It refuses to overwrite an existing day. `part1` and `part2` both default to counting lines,
so override them in the stub as you implement the puzzle.
