package aoc.years.year2022.day01;

import aoc.utils.Puzzle;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component("year2022Day01")
public class Day01 implements Puzzle {

    @Override
    public int year() {
        return 2022;
    }

    @Override
    public int day() {
        return 1;
    }

    @Override
    public Mono<String> part2(Flux<String> input) {
        // TODO: implement part 2
        return Mono.empty();
    }
}
