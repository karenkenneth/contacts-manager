import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class contactsManager {
    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu(){
        Input input = new Input();
        Boolean continueLoop = true;
        do {
            options();
            int userInput = input.getInt(1,5);
            continueLoop = userInteraction(userInput);
        }while (continueLoop);
        System.out.println("Thanks for using this");
    }

    public static boolean userInteraction(int userInput){
        if (userInput == 1){
            retriveContact();
            return true;
        }else if(userInput == 2){
            createDirectory();
            return true;
        }else if (userInput == 3){
            searchContact();
            return true;
        }else if(userInput == 4){
            removeContact();
            return true;
        }else {
            return false;
        }
    }

    public static void options() {
        System.out.println("1. View contacts.\n");
        System.out.println("2. Add a new contact.\n");
        System.out.println("3. Search a contact by name.\n");
        System.out.println("4. Delete an existing contact.\n");
        System.out.println("5. Exit.\n");
        System.out.println("Enter an option (1, 2, 3, 4 or 5):\n");
    }

    public static void displayContacts(){
        System.out.println("Name | Phone number\n");
        System.out.println("---------------\n");
        retriveContact();
    }

    public static void removeContact(){
        Input input = new Input();
        String contactToDelete = input.getString("Enter name of contact to delete(first and last)");
        deleteContact(contactToDelete);
    }

    public static void deleteContact(String contactToDelete){
        Path filePath = Paths.get("src/contacts.txt");
        List<String> removeList = new ArrayList<>();
        try {
            removeList = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String contactToRemove = "";
        for (String contact : removeList){
            if (contact.startsWith(contactToDelete)){
                contactToRemove = contact;
            }
        }
        removeList.remove(contactToRemove);
        try {
            Files.write(Paths.get("src/contacts.txt"), removeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDirectory(){
        Input input = new Input();
        String firstName = input.getString("Please Enter Contacts first name");
        String  lastName = input.getString("Please Enter Contacts last name");
        int telephoneNumberFirstDigit = input.getInt("Please enter contacts telephone number first three digits");
        int telephoneNumberSecondDigit = input.getInt("Please enter contacts telephone number second three digits");
        int telephoneNumberThirdDigit = input.getInt("Please enter contacts telephone number final four digits");
        addContact(firstName + " " +  lastName + " | " +
                telephoneNumberFirstDigit + "-" + telephoneNumberSecondDigit + "-" + telephoneNumberThirdDigit);
    }

    public static void addContact(String addInfo){
        Path filePath = Paths.get("src/contacts.txt");
        List<String> addedList = new ArrayList<>();
        try {
            addedList = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        addedList.add(addInfo);
        try {
            Files.write(Paths.get("src/contacts.txt"), addedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchContact(){
        Input input = new Input();
        String searchedName = input.getString("Please enter the searched name");
        Path filePath = Paths.get("src/contacts.txt");
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : lines){
            if (line.startsWith(searchedName)){
                System.out.println(line);
            }
        }
    }


    public static void retriveContact() {
        Path filePath = Paths.get("src/contacts.txt");
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : lines){
            System.out.println(line);
        }
    }
}
