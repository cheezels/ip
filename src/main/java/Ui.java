import java.util.Scanner;

public class Ui {

    private Scanner sc = new Scanner(System.in);

    public void showIntroMessage() {
        System.out.println("""
                Hello! I'm Lee Kuan Yew, founding father of Singapore.
                How can I help my fellow countryman today?""");
    }

    public String getReply() {
        return sc.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

}
