package main.java.MoshGenerics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ListCustomInt listCustomInt = new ListCustomInt();
        listCustomInt.add(1);
        String s1 = listCustomInt.toString();
        System.out.println(listCustomInt.get(0));
        System.out.println(s1);

        ListCustomObj listCustomObj = new ListCustomObj();
        listCustomObj.add(1);
        listCustomObj.add("pepe");
        listCustomObj.add(new User(10));
        listCustomObj.print();

        int n1 = (int) listCustomObj.get(0);
        System.out.println(n1);

//        int n2 = (int) listCustomObj.get(1);

        var genericList = new GenericList<Integer>();
        genericList.add(2);
        int n3 = genericList.get(0);

        GenericList<Float> gl2 = new GenericList<Float>();
        gl2.add(1.2f);
        float f1 = gl2.get(0);

        GenericList<Double> gl3 = new GenericList<>();
        GenericList<User> gl4 = new GenericList<>();

        User u1 = new User(10);
        User u2 = new User(20);
        System.out.println(u1.compareTo(u2));

        User userMax = Utils.max(u1, u2);
        System.out.println(userMax);

        Utils.print(1, "S");
        Utils.print('c', 1);
        Utils.print(new Object(), u2);

        User u3 = new Instructor(15);

        var users = new GenericList<User>();
        Utils.printUsers(users);
        // next
        var u4 = new GenericList<Instructor>();

        u4.add(new Instructor(5));
        u4.add(new Instructor(15));
        u4.add(new Instructor(25));
        u4.add(new Instructor(35));

//        var usersCopy = new GenericList<User>();
//        for (int i = 0; i < u4.getSize(); i++) {
//            usersCopy.add(u4.get(i));
//        }
        Utils.printUsers(u4);
        Utils.printUsers(new GenericList<Instructor>());

//        Utils.printUsers(new GenericList<String>());
//        Utils.printUsers(new GenericList<LocalDate>());

        List<A> listA = new ArrayList<A>();
        List<B> listB = new ArrayList<B>();

//        listA = listB;
//        listB = listA;
    }
}
