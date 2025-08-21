import java.util.Scanner;

public class LeeKuanYew {
    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot("Lee Kuan Yew");
        System.out.println(chatBot.getIntroMessage());

        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();

            // Quit condition
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(chatBot.getEndMessage());
                break;
            }

            System.out.println(chatBot.getResponse(userInput));
        }
        sc.close();

    }
}