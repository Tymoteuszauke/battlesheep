package com.blackship.battlesheep.game.state;

import java.util.stream.IntStream;

/**
 * @author Anna Gawda
 * @since 26.07.17
 */
public class StartingBoard implements Board {
    //TODO: refactor
    @Override
    public String boardLayout() {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.rangeClosed(1, 100).forEach(x -> {
            stringBuilder.append(String.format("%4s", String.valueOf(x)));
            if (x % 10 == 0) stringBuilder.append(System.getProperty("line.separator"));
        });
        return stringBuilder.toString();
    }
}
