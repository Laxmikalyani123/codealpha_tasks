import java.util.*;

class Chatbot {

    public String getResponse(String input) {

        input = input.toLowerCase();

        if (input.contains("hello") || input.contains("hi")) {
            return "Hello! How can I help you today?";
        }

        else if (input.contains("your name")) {
            return "I am CodeAlpha AI Chatbot.";
        }

        else if (input.contains("course") || input.contains("internship")) {
            return "This internship focuses on Java development and problem-solving skills.";
        }

        else if (input.contains("task")) {
            return "There are 3 main tasks: Grade Tracker, Stock Platform, and AI Chatbot.";
        }

        else if (input.contains("bye")) {
            return "Goodbye! Have a great day!";
        }

        else if (input.contains("how are you")) {
            return "I'm just code, but I'm functioning perfectly!";
        }

        else {
            return "Sorry, I didn't understand that. Can you rephrase?";
        }
    }
}

public class AIChatbot {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Chatbot bot = new Chatbot();

        System.out.println("===== AI CHATBOT =====");
        System.out.println("Type 'bye' to exit.");

        while (true) {

            System.out.print("You: ");
            String userInput = sc.nextLine();

            String response = bot.getResponse(userInput);
            System.out.println("Bot: " + response);

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }
        }

        sc.close();
    }
}