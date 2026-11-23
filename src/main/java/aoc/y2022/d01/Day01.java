package aoc.y2022.d01;

import aoc.utils.Puzzle;
import aoc.utils.PuzzleRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.function.Function;

@Component("y2022D01")
public class Day01 implements Puzzle {

    @Override
    public Mono<String> part1(Flux<String> input) {
        return input
                .windowUntil(line -> !StringUtils.hasText(line))
                .flatMap(elf -> elf
                        .filter(StringUtils::hasText)
                        .map(Integer::valueOf)
                        .reduce(Integer::sum))
                .reduce(Integer::max)
                .map(String::valueOf);
    }

    @Override
    public Mono<String> part2(Flux<String> input) {
        return input
                .windowUntil(line -> !StringUtils.hasText(line))
                .flatMap(elf -> elf
                        .filter(StringUtils::hasText)
                        .map(Integer::valueOf)
                        .reduce(Integer::sum))
                .sort(Comparator.reverseOrder())
                .take(3)
                .reduce(Integer::sum)
                .map(String::valueOf);
    }

    static void main(String[] args) {
        PuzzleRunner.run(Day01.class);
    }
}
