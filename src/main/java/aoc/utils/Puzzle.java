package aoc.utils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/** A single Advent of Code puzzle solution. */
public interface Puzzle {

    default int year() {
        return numberFromPackage(getClass(), "y");
    }

    default int day() {
        return numberFromPackage(getClass(), "d");
    }

    default Mono<String> part1(Flux<String> input) {
        return input.count().map(count -> "lines: " + count);
    }

    default Mono<String> part2(Flux<String> input) {
        return input.count().map(count -> "lines: " + count);
    }

    private static int numberFromPackage(Class<?> type, String prefix) {
        return Arrays.stream(type.getPackageName().split("\\."))
                .filter(segment -> segment.startsWith(prefix))
                .map(segment -> segment.substring(prefix.length()))
                .filter(digits -> !digits.isEmpty() && digits.chars().allMatch(Character::isDigit))
                .findFirst()
                .map(Integer::parseInt)
                .orElseThrow(() -> new IllegalStateException(
                        "Cannot determine %s from package %s".formatted(prefix, type.getPackageName())));
    }
}
