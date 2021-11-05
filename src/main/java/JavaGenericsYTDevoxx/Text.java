package main.java.JavaGenericsYTDevoxx;

public class Text extends Message {


    @Override
    public String toString() {
        return "Text{" +
                "text='" + super.getText() + '\'' +
                '}';
    }

    public Text(String text) {
        super(text);
    }
}
