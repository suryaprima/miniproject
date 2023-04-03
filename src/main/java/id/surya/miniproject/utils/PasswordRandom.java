package id.surya.miniproject.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Qualifier("randomPassword")
public class PasswordRandom implements RandomString{
    @Override
    public String random() {
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

        StringBuilder randomPassword = new StringBuilder();
        int lengthPassword = 15;

        Random random = new Random();

        for (int i = 0; i < lengthPassword; i++) {
            int index = random.nextInt(alphaNumeric.length());

            char randChar = alphaNumeric.charAt(index);
            randomPassword.append(randChar);
        }

        return randomPassword.toString();
    }
}
