package ru.netology;

import java.util.concurrent.atomic.AtomicInteger;

public class BeautifulNickname {
    private final static AtomicInteger countBeautifulWordsLong3 = new AtomicInteger(0);
    private final static AtomicInteger countBeautifulWordsLong4 = new AtomicInteger(0);
    private final static AtomicInteger countBeautifulWordsLong5 = new AtomicInteger(0);

    public static AtomicInteger getCountBeautifulWordsLong3() {
        return countBeautifulWordsLong3;
    }

    public static AtomicInteger getCountBeautifulWordsLong4() {
        return countBeautifulWordsLong4;
    }

    public static AtomicInteger getCountBeautifulWordsLong5() {
        return countBeautifulWordsLong5;
    }

    public static boolean isPalindrome(String nickname) {
        for(int i = 0; i < nickname.length(); i++) {
            if (!(nickname.charAt(i) == nickname.charAt(nickname.length() - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFromSingleLetter(String nickname) {
        int firstLetter = nickname.charAt(0);
        for(int j = 1; j < nickname.length(); j++) {
            if(firstLetter != nickname.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphabetically(String nickname) {
        int orderLetter = nickname.charAt(0);
        for(int k = 1; k < nickname.length(); k++) {
            if(orderLetter > nickname.charAt(k)) {
                return false;
            }
            orderLetter = nickname.charAt(k);
        }
        return true;
    }

    public static void incrementCounter(int lengthNickname) {
        switch (lengthNickname) {
            case 3:
                countBeautifulWordsLong3.incrementAndGet();
                break;
            case 4:
                countBeautifulWordsLong4.incrementAndGet();
                break;
            case 5:
                countBeautifulWordsLong5.incrementAndGet();
                break;
            default:
                System.out.println("ОШИБКА! Неверный ник!");
                throw new RuntimeException();
        }
    }
}
