import java.util.Scanner;

public class Input {

    private Scanner sc;

    public Input(){
        this.sc = new Scanner(System.in);
    }

    public String getString(){
        return this.sc.nextLine();
    }

    public String getString(String prompt){
        System.out.println(prompt);
        return this.sc.nextLine();
    }

    public Boolean yesNO(){
        return this.sc.nextLine().equals("yes");
    }

    public Boolean yesNO(String prompt){
        System.out.println(prompt);
        return this.sc.nextLine().equals("yes");
    }

    public Integer getInt(){
        try {
            return Integer.valueOf(this.sc.nextLine());
        }catch (NumberFormatException e){
            System.out.println("Please enter a Interger in number format");
            return getInt();
        }
    }


    public Integer getInt(String prompt){
        try {
            System.out.println(prompt);
            return Integer.valueOf(this.sc.nextLine());
        }catch (NumberFormatException e){
            System.out.println("Please enter a Interger in number format");
            return getInt(prompt);
        }
    }

    public Integer getInt(int min, int max){
        int number;
        try {
            number = Integer.valueOf(this.sc.nextLine());
        }catch (NumberFormatException e){
            System.out.println("Please enter a Interger in number format");
            return getInt(min,max);
        }
        if (number <= max && number >= min){
            return number;
        }else {
            System.out.println("Number not in range, please enter a new number.");
            return getInt(min,max);
        }
    }

    public Integer getInt(int min, int max, String prompt){
        System.out.println(prompt);
        int number;
        try {
            number = Integer.valueOf(this.sc.nextLine());
        }catch (NumberFormatException e){
            System.out.println("Please enter a Interger in number format");
            return getInt(min,max);
        }
        if (number <= max && number >= min){
            return number;
        }else {
            System.out.println("Number not in range, please enter a new number.");
            return getInt(min,max);
        }
    }

    public Double getDouble(){
        try {
            return Double.valueOf(this.sc.nextLine());
        }catch (NumberFormatException e){
            System.out.println("Please enter a Double in number format");
            return getDouble();
        }
    }

    public Double getDouble(String prompt){
        System.out.println(prompt);
        try {
            return Double.valueOf(this.sc.nextLine());
        }catch (NumberFormatException e){
            System.out.println("Please enter a Double in number format");
            return getDouble();
        }
    }

    public Double getDouble(int min, int max){
        double number;
        try {
            number = Double.valueOf(this.sc.nextLine());
        }catch (NumberFormatException e){
            System.out.println("Please enter a double in number format");
            return getDouble(min,max);
        }
        if (number <= max && number >= min){
            return number;
        }else {
            System.out.println("Number not in range, please enter a new number.");
            return getDouble(min,max);
        }
    }

    public Double getDouble(int min, int max, String prompt){
        System.out.println(prompt);
        double number;
        try {
            number = Double.valueOf(this.sc.nextLine());
        }catch (NumberFormatException e){
            System.out.println("Please enter a double in number format");
            return getDouble(min,max);
        }
        if (number <= max && number >= min){
            return number;
        }else {
            System.out.println("Number not in range, please enter a new number.");
            return getDouble(min,max);
        }
    }

    public int getBinary(){
        try {
            String binary = this.sc.next();
            return Integer.parseInt(binary, 2);
        }catch (NumberFormatException e){
            System.out.println("Please enter a valid number");
            return getBinary();
        }
    }

    public int getHex(){
        try {
            String hex = this.sc.nextLine();
            return Integer.parseInt(hex, 16);
        }catch (NumberFormatException e){
            System.out.println("Please enter a valid number");
            return getHex();
        }
    }
}
