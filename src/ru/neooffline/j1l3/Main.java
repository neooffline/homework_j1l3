package ru.neooffline.j1l3;

import java.util.Random;
import java.util.Scanner;

public class Main {
static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        String[] secret = {"новый", "старый", "молодой", "больной", "ситцевый", "сереневый"};
        gameWithWords(secret);
    }
    static void gameWithWords(String[] arrayOfWords){
        Random random = new Random();
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
