package main.java.MoshGenerics;

public class Utils {

    public static <T extends Comparable<T>> T max(T first, T second) {
        return first.compareTo(second) < 0 ? second : first;
    }

    public static <K, V> void print(K key, V value) {
        System.out.println(key + "=" + value);
    }

    public static void printUser(User user) {
        System.out.println(user);
    }
    // class CAP#1 {}
    // class CAP#1 extends User {}
    // class Instructor extends User{}
    public static void printUsers(GenericList<? extends User> list) {
//        Instructor i = list.get(0); //CAP#1 is not Instructor
        User u = list.get(0);
        Object o = list.get(0);
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void addUsers(GenericList<? super User> list) {
        list.add(new User(20));
        list.add(new Instructor(20));
        list.add(new Instructor(20));
//        list.add((Comparable) new Object());

        Object o = list.get(0);
//        User u = list.get(0);   //doesn't work compiler doesn't know what type of object this is
    }
}
