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
        System.out.println("Welcome to our contact manager!!!!");
        Input input = new Input();
        Boolean continueLoop = true;
        do {
            options();
            int userInput = input.getInt(1,6);
            continueLoop = userInteraction(userInput);
        }while (continueLoop);
        System.out.println("Thanks for using our Contact Manager. Have a great day!");
    }

    public static boolean userInteraction(int userInput){
        if (userInput == 1){
            continueRetrievingContact();
            return true;
        }else if(userInput == 2){
            createDirectory();
            return true;
        }else if (userInput == 3){
            newSearchContact();
            return true;
        }else if(userInput == 4){
            newSearchPhoneNumber();
            return true;
        }else if(userInput == 5){
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
        System.out.println("4. Search a contact by Phone Number.\n");
        System.out.println("5. Delete an existing contact.\n");
        System.out.println("6. Exit.\n");
        System.out.println("Enter an option (1, 2, 3, 4 or 5):\n");
    }


    public static void displayContacts(){
        System.out.println("Name | Phone number\n");
        System.out.println("---------------\n");
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
            if (contact.toLowerCase().startsWith(contactToDelete.toLowerCase())){
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
        boolean isItInternational = input.yesNO("Is this number international?");
        if (!isItInternational){
            String amountOfNumbers = input.getString("How many digits are in this number?");
            if (amountOfNumbers.equals("7")){
                int telephoneNumberSecondDigit = input.getInt(100, 999,"Please enter contacts telephone number first three digits");
                int telephoneNumberThirdDigit = input.getInt(1000,9999,"Please enter contacts telephone number final four digits");
                addContact(firstName + " " +  lastName + " | " +
                        telephoneNumberSecondDigit + "-" + telephoneNumberThirdDigit);
            }else if (amountOfNumbers.equals("10")){
                int telephoneNumberFirstDigit = input.getInt(100,999,"Please enter contacts telephone number first three digits");
                int telephoneNumberSecondDigit = input.getInt(100,999,"Please enter contacts telephone number second three digits");
                int telephoneNumberThirdDigit = input.getInt(1000,9999,"Please enter contacts telephone number final four digits");
                addContact(firstName + " " +  lastName + " | " +
                        telephoneNumberFirstDigit + "-" + telephoneNumberSecondDigit + "-" + telephoneNumberThirdDigit);
            }else {
                System.out.println("Please enter a valid number of numbers \nreinitializing creator");
                createDirectory();
            }
        } else {
            System.out.println("Entering International Mode...");
            String internationalNumber = input.getString("Please enter internationl number\nCaution: Saftey filters have been removed.");
            addContact(firstName + " " +  lastName + " | " + internationalNumber);
        }
    }

    public static void addContact(String addInfo){
        Input input = new Input();
        Path filePath = Paths.get("src/contacts.txt");
        List<String> addedList = new ArrayList<>();
        try {
            addedList = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String contact : addedList){
            if (contact.contains(addInfo)){
                boolean duplicate = input.yesNO("Entry is already present. Do you want to continue [y/n]?");
                if (!duplicate) {
                    createDirectory();
                }
                input.getString();
            }
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
        displayContacts();
        for (String line : lines){
            if (line.toLowerCase().startsWith(searchedName.toLowerCase())){
                System.out.println(line);
            }
        }
    }

    public static void newSearchContact(){
        Input input = new Input();
        boolean newSearch = false;
        do {
            searchContact();
            newSearch = input.yesNO("Do you want to exit search? [Yes or No]");
        }while (!newSearch);
    }

    public static void newSearchPhoneNumber(){
        Input input = new Input();
        boolean newSearch = false;
        do {
            newSearchPhoneNumber();
            newSearch = input.yesNO("Do you want to exit search? [Yes or No]");
        }while (!newSearch);
    }

    public static void searchNumber(){
        Input input = new Input();
        String searchedNumber = input.getString("Enter the number which you'd like to search.");
        Path filePath = Paths.get("src/contacts.txt");
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayContacts();
        for (String line : lines){
            if (line.toLowerCase().endsWith(searchedNumber)){
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
        displayContacts();
        for (String line : lines){
            System.out.println(line);
        }
    }

    public static void continueRetrievingContact(){
        boolean continueLooking = false;
        Input input = new Input();
        do {
            retriveContact();
            continueLooking = input.yesNO("Do you want to continue? [Yes or No]");
        } while (!continueLooking);
    }

}
