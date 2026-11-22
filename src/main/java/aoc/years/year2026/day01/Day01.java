package aoc.years.year2026.day01;

import aoc.utils.Puzzle;
import aoc.utils.PuzzleMain;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component("year2026Day01")
public class Day01 implements Puzzle {

    @Override
    public int year() {
        return 2026;
    }

    @Override
    public int day() {
        return 1;
    }

    @Override
    public Mono<String> part1(Flux<String> input) {
        return input.count().map(count -> "lines: " + count);
    }

    @Override
    public Mono<String> part2(Flux<String> input) {
        return input.filter(line -> !line.isBlank()).count().map(count -> "non-blank lines: " + count);
    }

    public static void main(String[] args) {
        PuzzleMain.run(new Day01(), args);
    }
}
