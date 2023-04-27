package ru.netology;

import java.util.Random;

public class Main {
    private static final int textsLength = 100_000;

    public static void main(String[] args) throws InterruptedException {
        // Генерация 100_000 коротких слов
        Random random = new Random();
        String[] texts = new String[textsLength];
        for (int i = 0; i < textsLength; i++) {
            texts[i] = TextGenerator.generateText("abc", 3 + random.nextInt(3));
        }

        // Поток проверки никнейма на то, является ли он палиндромом
        Thread threadPalindrome = createThreadPalindrome(texts);
        threadPalindrome.start();

        // Поток проверки никнейма на то, создан ли он из повторений одной буквы
        Thread threadFromSingleLetter = createThreadFromSingleLetter(texts);
        threadFromSingleLetter.start();

        // Поток проверки никнейма на то, идут ли в нём буквы по алфавиту
        Thread threadAlphabetically = createThreadAlphabetically(texts);
        threadAlphabetically.start();

        // Ожидаем завершение всех потоков
        threadPalindrome.join();
        threadFromSingleLetter.join();
        threadAlphabetically.join();

        // Выводим результат
        System.out.println("Красивых слов с длиной 3: " + BeautifulNickname.getCountBeautifulWordsLong3().get() +
                " шт.\nКрасивых слов с длиной 4: " + BeautifulNickname.getCountBeautifulWordsLong4().get() +
                " шт.\nКрасивых слов с длиной 5: " + BeautifulNickname.getCountBeautifulWordsLong5().get() + "шт.");
    }

    private static Thread createThreadPalindrome(String[] texts) {
        Runnable taskPalindrome = () -> {
            for (int i = 0; i < textsLength; i++) {
                if (BeautifulNickname.isPalindrome(texts[i])) {
                    BeautifulNickname.incrementCounter(texts[i].length());
                }
            }
        };
        return new Thread(taskPalindrome);
    }

    private static Thread createThreadFromSingleLetter(String[] texts) {
        Runnable taskFromSingleLetter = () -> {
            for (int j = 0; j < textsLength; j++) {
                if (BeautifulNickname.isFromSingleLetter(texts[j])) {
                    BeautifulNickname.incrementCounter(texts[j].length());
                }
            }
        };
        return new Thread(taskFromSingleLetter);
    }

    private static Thread createThreadAlphabetically(String[] texts) {
        Runnable taskAlphabetically = () -> {
            for (int k = 0; k < textsLength; k++) {
                if (BeautifulNickname.isAlphabetically(texts[k])) {
                    BeautifulNickname.incrementCounter(texts[k].length());
                }
            }
        };
        return new Thread(taskAlphabetically);
    }

}