package ru.neooffline.j1l3;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    static String[] secret = {"новый", "старый", "молодой", "больной", "ситцевый", "сереневый"};
    public static void main(String[] args) {
        System.out.print("Игрый на выбор:\n0. Выход\n1. Угадай число\n2. Угадай слово\nВаш выбор: ");
        int choice = sc.nextInt();
        switch (choice){
            case 1:{
                do {
                    gameWithRandomNumber();
                    System.out.print("Сыграем еще? 1 - да, 0 - нет: ");
                } while (sc.nextInt()!=0);
                break;
            }
            case 2: {
                gameWithWords(secret);
                break;
            }
            case 0:{
                break;
            }default:break;
        }
        sc.close();
    }
    //Задание 1
    static void gameWithRandomNumber(){
        int randomNumber = random.nextInt(9);
        int number, theTry=0, attempt = 3;
        System.out.print("Введите число от 0 до 9, у вас " + attempt + " попытки: ");
        do {
            number = sc.nextInt();
            theTry++;
            if (number < randomNumber && theTry!=attempt){
                System.out.print(number + " - меньше загаданного числа, еще "+(attempt-theTry)+" попытки: ");
            } else if (number > randomNumber && theTry!=attempt){
                System.out.print(number + " - больше загаданного числа, еще "+(attempt-theTry)+" попытки: ");
            } else {
                System.out.println("Поздравляю!! Вы угадали, загаданное число - " + number);
                break;
            }
            if (theTry ==3 && number!=randomNumber){
                System.out.println("Сожалею, но Вы проиграли :( :( ;( ;(");
            }
        } while (theTry < attempt);

    }
    //Задание 2
    static void gameWithWords(String[] arrayOfWords){
        int randomElement = random.nextInt(arrayOfWords.length);
        char[] charsRandomWord;
        char[] charsWord;
        charsRandomWord=toCharArray(arrayOfWords[randomElement]);
        String res;
        System.out.println("\t\t\tСыграем в игру угадай слово!\n" +
                "\tМы загадали случайное слово, а ты его должен угадать\n" +
                "Внимание!! Длина слова не должна превышать 15 символов и " +
                "\nсодержит только прописные русские буквы.\n" +
                "\t\tНачнем!");
        do {
            System.out.println("Введите своё слово: ");
            res= sc.next();
            charsWord=toCharArray(res);
            for (int i = 0; i < charsRandomWord.length; i++) {
                if (charsWord[i]!=charsRandomWord[i]){
                    charsWord[i]='#';
                }
            }
            if (res.equals(arrayOfWords[randomElement])){
                System.out.println("Поздравляем!! Вы победили!!!");
                System.out.println("Загаданное слово - "+ arrayOfWords[randomElement]);
            } else {
                System.out.println("Попробуй еще раз");
                printCharArray(charsWord);
            }
        } while (!res.equals(arrayOfWords[randomElement]));
        System.out.println();
    }
    static char[] toCharArray(String word){
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
}
