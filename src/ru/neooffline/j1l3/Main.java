package ru.neooffline.j1l3;

import java.util.Random;
import java.util.Scanner;

public class Main {
private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
	String w1 = "banana";
        for (int i = 0; i < toCharArray(w1).length;i++){
            System.out.print(toCharArray(w1)[i] + "+");
        }
        String[] secret = {"baba"};
        gameWithWords(secret);
    }
    static void gameWithWords(String[] arrayOfWords){
        Random random = new Random();
        int randomElement = 0; //random.nextInt(arrayOfWords.length);
        char[] charsRandomWord = new char[15];
        char[] charsWord = new char[15];
        System.arraycopy(arrayOfWords[randomElement],0,charsRandomWord,0,charsRandomWord.length);
        boolean win=false;
        String res;
        int l = 0;
        do {
            res= sc.next();
            System.arraycopy(res,0,charsWord,0,charsWord.length);
            for (int i = 0; i < charsWord.length; i++) {
                if (charsWord[i]!=charsRandomWord[i]){
                    charsWord[i]='#';
                } else {
                  l++;  
                }
            }
            for (char item:charsWord
                 ) {
                System.out.print(item+" ");
            }
        } while (l!=charsWord.length);
        System.out.println();
    }
    static char[] toCharArray(String word){
        char[] charArray = word.toCharArray();
        return charArray;
    }
}
