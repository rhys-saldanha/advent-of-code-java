# Advent of Code (Java)

A [Spring Boot 4.2.0](https://spring.io/projects/spring-boot) + [Project Reactor](https://projectreactor.io/) starter for solving [Advent of Code](https://adventofcode.com/) puzzles in Java 25. There is no web stack: it is a command-line application.

## Layout

```
src/main/java/aoc/
  AdventOfCodeJavaApplication.java     Spring Boot entry point
  utils/                               shared puzzle infrastructure
    Puzzle.java                        contract implemented by every day
    PuzzleInput.java                   classpath input -> Flux<String>
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

List registered puzzles:

```bash
./gradlew bootRun
```

Run a specific day (both parts), or one part:

```bash
./gradlew bootRun --args='2026 1'
./gradlew bootRun --args='2026 1 2'
./gradlew bootRun --args='2022 1'
```

Run the tests:

```bash
./gradlew test
```

## Add a new day

1. Create `src/main/java/aoc/years/year<YEAR>/day<NN>/DayNN.java` implementing `aoc.utils.Puzzle` and annotate it `@Component` so `PuzzleRunner` discovers it.
2. Put the puzzle's sample input in `src/test/resources/years/<YEAR>/day<NN>/example.txt` and write `DayNNTest.java` using `PuzzleInput` + Reactor's `StepVerifier`.
3. Put your personal input in `src/main/resources/years/<YEAR>/day<NN>/input.txt` (add `*.txt` handling to `.gitignore` if you do not want to commit it).
4. Implement `part1` / `part2` as `Mono<String>` functions over the input `Flux<String>`.
