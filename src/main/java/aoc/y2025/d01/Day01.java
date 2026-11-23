package aoc.y2025.d01;

import aoc.utils.Puzzle;
import aoc.utils.PuzzleRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component("y2025D01")
public class Day01 implements Puzzle {

    public Mono<String> part1(Flux<String> input) {
        return input.count().map(count -> "lines: " + count);
    }

    @Override
    public Mono<String> part2(Flux<String> input) {
        return input.filter(line -> !line.isBlank()).count().map(count -> "non-blank lines: " + count);
    }

    public static void main(String[] args) {
        PuzzleRunner.run(Day01.class);
    }
}
