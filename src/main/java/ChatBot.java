public class ChatBot {

    public ChatBot() {
    }

    public String getIntroMessage() {
        return """
                Hello! I'm Lee Kuan Yew, founding father of Singapore.
                How can I help my fellow countryman today?
                """;
    }

    public String getResponse(String input) {
        return input;
    }

    public String getEndMessage() {
        return """
                Goodbye fellow countryman! Majulah Singapura!
                """;
    }
}
