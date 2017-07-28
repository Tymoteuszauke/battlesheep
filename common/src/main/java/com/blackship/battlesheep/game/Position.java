package com.blackship.battlesheep.game;

/**
 * @author Anna Gawda
 * @since 26.07.2017
 */
public class Position {

    private final Integer position;
    private FieldState fieldState;

    public Position(Integer position, FieldState fieldState) {
        this.position = position;
        this.fieldState = fieldState;
    }

    public Position(Integer position) {
        fieldState = FieldState.EMPTY;
        this.position = position;
    }

    public FieldState getFieldState() {
        return fieldState;
    }

    public Integer getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position1 = (Position) o;

        if (position != null ? !position.equals(position1.position) : position1.position != null) return false;
        return fieldState == position1.fieldState;
    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (fieldState != null ? fieldState.hashCode() : 0);
        return result;
    }
}
