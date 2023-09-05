import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class wordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'T' to input text or 'F' to input a file: ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("T")) {
            System.out.println("Enter the your text for Word Count: ");
            String text = scanner.nextLine();
            countWords(text);
        } else if (choice.equalsIgnoreCase("F")) {
            System.out.println("Enter the file path: ");
            String filePath = scanner.nextLine();
            try {
                String text = readFile(filePath);
                countWords(text);
            } catch (IOException e) {
                System.out.println("Error");
            }
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    private static String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private static void countWords(String text) {
        String[] words = text.split("[\\s\\p{Punct}]+");

        int wordCount = words.length;
        System.out.println("1.Total word count: " + wordCount);

        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        for (String word : words) {
            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
        }

        int uniqueWordCount = wordFrequencyMap.size();
        System.out.println("2.Unique word count: " + uniqueWordCount);

        System.out.println("3.Word frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        {
            System.out.println("4.Exit");
        }
    }
}
