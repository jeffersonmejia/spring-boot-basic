package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PalindromeController {

    /**
     * End point to check if a word is a palindrome.
     * 
     * @param word The word to check.
     * @return A message indicating whether the word is a palindrome or not.
     */
    @GetMapping("/palindrome/{word}")
    public String Palindrome(@PathVariable String word) {
        return isPalindrome(word) ? "The word is a palindrome" : "The word is not a palindrome";
    }

    /**
     * Checks if a word is a palindrome.
     * 
     * @param word The word to check.
     * @return true if the word is a palindrome, false otherwise.
     */
    private boolean isPalindrome(String word) {
        int length = word.length();
        for (int i = 0; i < length / 2; i++) {
            if (word.charAt(i) != word.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
