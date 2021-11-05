package main.java.JavaGenericsYTDevoxx;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        log(new Email("Hello Devoxx!"));

        // Covariant arrays or covariance
        Email[] emails = {new Email("We love beer!"), new Email("We love coffee")};
        logAll(emails);

        Message[] messageArray = emails;
        logAll(messageArray);

//        messageArray[0] = new Text("Text message");
//        messageArray[1] = new Text("Text message");
//        logAll(messageArray);

        List<Email> emailList = Arrays.asList(new Email("Hello by email"), new Email("Email list elem 2"));
        List<Text> textList = Arrays.asList(new Text("Hello by text"), new Text("Text list elem 2"));
        List<Message> messageList = Arrays.asList(new Text("Message List Text elem"), new Email("Message List Email " +
                "elem"));

        // Works
        logAll(messageList);

        // doesn't work without Wildcard
        logAll(emailList);

        Consumer<Message> consumer = (msg) -> System.out.println(msg);
        logAll(emailList, consumer);

        //Consumer of objects
        // Functional interfaces and Lambdas use <? super T> a lot
        // Contra Variant
        Consumer<Object> print = System.out::println;

        // Doesn't work unless we make Consumer<? super Message>
        logAll(textList, print);

    }

    public static void log(Message message) {
        System.out.println(message);
    }

    public static void logAll(Message[] messages) {
        for (Message m : messages) {
            System.out.println(m);
        }
    }

    public static void logAll(List<? extends Message> messages) {
        for (Message m : messages) {
            System.out.println(m);
        }
    }

    public static void logAll(List<? extends Message> messages, Consumer<? super Message> consumer) {
        messages.forEach(consumer);
    }

}

