package com.thoughtworks.trains.model;

/**
 * Model class representing a direction.
 */
public class Direction implements Comparable<Direction> {

    private Town town;
    private int distance;

    public Direction(Town town, int distance) {
        this.town = town;
        this.distance = distance;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Direction other) {
        return this.distance - other.distance;
    }
}
