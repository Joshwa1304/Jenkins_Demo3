import java.util.Scanner;

public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        System.out.println("Hello, DevOps!");

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your age");
        int age = sc.nextInt();

        if (age >= 5) {
            System.out.println("You are eligible");
        }

        else {
            System.out.println("You are not eligible");
        }
    }
}