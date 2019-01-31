package ru.neooffline.j1l3;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random random = new Random();
    static StringWriter sw = new StringWriter();
    static PrintWriter pw = new PrintWriter(sw);
    static String CHARSET_LINUX = "utf-8";
    static String CHARSET_WINDOWS = "cp866";
    private static String charset;
    static String[] secret = {"новый", "старый", "молодой", "больной", "ситцевый",
            "сереневый", "оранжевый", "лопата", "лапти", "железо", "никель", "кобальт"};
    public static void main(String[] args) {
        charset = System.getProperty("os.name").contains("Windows")?CHARSET_WINDOWS:CHARSET_LINUX;
        Scanner sc = new Scanner(System.in,charset);
        int choice, continueGame =1, status = 0;
        while (status != 1){
            System.out.print("Игрый на выбор:\n0. Выход\n1. Угадай число\n2. Угадай слово\nВаш выбор: ");
            try {
               choice = Integer.parseInt(sc.next());
            } catch (NumberFormatException e){
                e.printStackTrace(pw);
                System.out.println("Ошибка при вводе данных:\n" + sw.toString());
                choice = -1;
            }
            switch (choice){
            case 1:{
                do {
                    if (continueGame == 1){
                        gameWithRandomNumber();
                    }
                    System.out.print("Сыграем еще? 1 - да, 0 - нет: ");
                    try {
                        continueGame = Integer.parseInt(sc.next());
                    } catch (NumberFormatException e){
                        e.printStackTrace(pw);
                        System.out.println("Ошибка при вводе данных:\n" + sw.toString());
                        System.out.println("Вы ввели не корректные данные, возврат в главное меню");
                        continueGame = 0;
                    }
                    if (continueGame !=1 || continueGame != 0){
                        System.out.println("Вы ввели не корректные данные, возврат в главное меню");
                        continueGame = 0;
                    }
                } while (continueGame != 0);
                break;
            }
            case 2: {
                gameWithWords(secret);
                break;
            }
            case 0:{
                status = 1;
                break;
            }
            default:
                System.out.println("--------------------\n" +
                        "Вы можете ввести только предложенные значения!!!\n" +
                        "--------------------");
            }
        }
        sc.close();
        pw.close();
    }
    //Задание 1
    static void gameWithRandomNumber(){
        Scanner sc = new Scanner(System.in,charset);
        int randomNumber = random.nextInt(9);
        int number, attempt=0, maxAttempt = 3;
        String attemptString;
        System.out.print("Введите число от 0 до 9, у вас " + maxAttempt + " попытки: ");
        do {
            try {
                number = Integer.parseInt(sc.next());
            } catch (NumberFormatException e){
                e.printStackTrace(pw);
                System.out.println("Ошибка в веденных данных:\n" + sw.toString());
                number = -1;
            }
            attempt++;
            attemptString = (maxAttempt-attempt) == 1 ? " попытка: ":" попытки: ";
            if (number < 0 || number > 9 && attempt != maxAttempt){
                System.out.print("Число должно быть от 0 до 9, еще " + (maxAttempt-attempt) + attemptString);
            } else if (number < randomNumber && attempt != maxAttempt){
                System.out.print(number + " - меньше загаданного числа, еще " + (maxAttempt-attempt) + attemptString);
            } else if (number > randomNumber && attempt != maxAttempt){
                System.out.print(number + " - больше загаданного числа, еще " + (maxAttempt-attempt) + attemptString);
            } else if (attempt == 3 && number != randomNumber){
                System.out.println("Сожалею, но Вы проиграли :( :( ;( ;(");
            }
            else {
                System.out.println("Поздравляю!! Вы угадали, загаданное число - " + number);
                break;
            }
        } while (attempt < maxAttempt);
    }
    //Задание 2
    static void gameWithWords(String[] arrayOfWords){
        Scanner sc = new Scanner(System.in,charset);
        int randomElement = random.nextInt(arrayOfWords.length-1);
        String result;
        char[] charsRandomWord, charsWord;
        charsRandomWord= wordToCharArray(arrayOfWords[randomElement]);
        System.out.println("\t\t\tСыграем в игру угадай слово!\n" +
                "\tМы загадали случайное слово, а ты его должен угадать\n" +
                "Внимание!! Длина слова не должна превышать 15 символов и " +
                "\nсодержит только строчные русские буквы.\n"+
                "список слов, прдставлен ниже:");
        printWordArray(arrayOfWords);
        do {
            System.out.println("Введите своё слово: ");
            result= sc.next();
            System.out.println(result);
            charsWord= wordToCharArray(result);
            for (int i = 0; i < charsRandomWord.length; i++) {
                if (charsWord[i]!=charsRandomWord[i]){
                    charsWord[i]='#';
                }
            }
            if (result.equals(arrayOfWords[randomElement])){
                System.out.println("Поздравляем!! Вы победили!!!");
                System.out.println("Загаданное слово - "+ arrayOfWords[randomElement]);
            } else {
                System.out.println("Попробуй еще раз");
                printCharArray(charsWord);
            }
        } while (!result.equals(arrayOfWords[randomElement]));
        System.out.println();
    }
    static char[] wordToCharArray(String word){
        char[] charArray = new char[15];
        if (word.length() < 15){
            for (int i = 0; i < word.length(); i++) {
                charArray[i] = word.charAt(i);
            }
            for (int i = word.length(); i < 15; i++) {
                charArray[i] = '#';
            }
        } else {
            for (int i = 0; i < 15; i++) {
                charArray[i] = '#';
            }
        }
        return charArray;
    }
    static void printCharArray(char[] charArray){
        for (char item:charArray) {
            System.out.print(item);
        }
        System.out.println();
    }
    static void printWordArray(String[] arrayOfWords){
        System.out.println("-----------------------");
        for (int i = 0; i < arrayOfWords.length; i++) {
            if (i != 0 && i%5 == 0 && i != arrayOfWords.length -1){
                System.out.print(arrayOfWords[i] + ",\n");
            } else if (i != arrayOfWords.length -1){
            System.out.print(arrayOfWords[i] + ", ");
            } else {
                System.out.println(arrayOfWords[i]+".");
            }
        }
        System.out.println("-----------------------");
    }
}
