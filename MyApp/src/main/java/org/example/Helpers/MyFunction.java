package org.example.Helpers;

import org.example.Views.ClientView;
import org.example.Views.MainPage;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MyFunction {
    public static LocalDate getDate(String message) {
        Scanner scanner = new Scanner(System.in);
        Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        System.out.println(message);
        String inputDate = scanner.nextLine();
        if (datePattern.matcher(inputDate).matches()) {
            try {
                return LocalDate.parse(inputDate);
            } catch (Exception e) {
                System.out.println(e);
                return getDate(message);
            }
        } else {
            System.out.println("Format de date invalide. Veuillez entrer la date au format yyyy-mm-dd.");
            return getDate(message);
        }
    }
    public static void appuyezPourQuitter(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Appuyez sur une touche pour quitter");
        scanner.nextLine();
    }
}
