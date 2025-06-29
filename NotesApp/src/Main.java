import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Notes Manager ---");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Delete all notes");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch (choice) {
                case 1 -> addNote(sc);
                case 2 -> viewNotes();
                case 3 -> deleteNotes();
                case 4 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        sc.close();
    }

    private static void addNote(Scanner sc) {
        System.out.println("Enter your note (single line): ");
        String note = sc.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {  // true = append mode
            writer.write(note + System.lineSeparator());
            System.out.println("Note saved.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void viewNotes() {
        System.out.println("\n--- Your Notes ---");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int count = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println(count++ + ". " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes found. (notes.txt doesn't exist yet)");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    private static void deleteNotes() {
        File file = new File(FILE_NAME);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("All notes deleted successfully.");
            } else {
                System.out.println("Failed to delete notes.");
            }
        } else {
            System.out.println("No notes found to delete.");
        }
    }

}
