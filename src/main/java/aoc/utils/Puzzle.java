package aoc.utils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** A single Advent of Code puzzle solution. */
public interface Puzzle {

    int year();

    int day();

    Mono<String> part1(Flux<String> input);

    Mono<String> part2(Flux<String> input);
}
