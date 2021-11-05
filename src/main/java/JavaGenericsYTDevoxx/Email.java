package main.java.JavaGenericsYTDevoxx;

public class Email extends Message {

    public Email(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return "Email{" +
                "text='" + super.getText() + '\'' +
                '}';
    }
}
