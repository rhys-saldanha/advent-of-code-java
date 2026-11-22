package aoc.utils;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/** Lists the available puzzles, or runs the one selected by command-line arguments. */
@Component
public class PuzzleRunner implements ApplicationRunner {

    private final List<Puzzle> puzzles;

    public PuzzleRunner(List<Puzzle> puzzles) {
        this.puzzles = puzzles.stream()
                .sorted(Comparator.comparingInt(Puzzle::year).thenComparingInt(Puzzle::day))
                .toList();
    }

    @Override
    public void run(ApplicationArguments args) {
        List<String> values = args.getNonOptionArgs();
        if (values.isEmpty()) {
            puzzles.forEach(puzzle ->
                    System.out.printf("Available: %d day %02d%n", puzzle.year(), puzzle.day()));
            return;
        }
        if (values.size() < 2) {
            throw new IllegalArgumentException("Usage: ./gradlew bootRun --args='<year> <day> [1|2]'");
        }

        int year = Integer.parseInt(values.get(0));
        int day = Integer.parseInt(values.get(1));

        Puzzle puzzle = puzzles.stream()
                .filter(candidate -> candidate.year() == year && candidate.day() == day)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No puzzle registered for %d day %02d".formatted(year, day)));

        if (values.size() > 2) {
            PuzzleMain.run(puzzle, values.get(2));
        } else {
            PuzzleMain.run(puzzle);
        }
    }
}
