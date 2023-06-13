package ee.taltech.theme1.part5;

import java.util.Scanner;

public class ScannerExample {

    public static void main(String[] args) {
        System.out.println("Enter your name: ");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        System.out.println("You said your name is: " + line);

        System.out.println("Btw, give me some number");
        int number = sc.nextInt();

        System.out.println("Wow, such a unique number: " + number);
    }
}
