package main.java.MoshGenerics;

public class User implements Comparable<User> {

    protected int points;

    public User(int points) {
        this.points = points;
    }

    @Override
    public int compareTo(User other) {
        return points - other.points;
    }
}
