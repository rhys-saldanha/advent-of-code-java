package aoc.years.year2022.day01;

import aoc.utils.Puzzle;
import aoc.utils.PuzzleMain;
import org.springframework.stereotype.Component;

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

    public static void main(String[] args) {
        PuzzleMain.run(new Day01(), args);
    }
}
