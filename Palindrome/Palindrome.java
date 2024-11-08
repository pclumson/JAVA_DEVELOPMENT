// A Java Palindrom class

class Palindrome {

    // a boolean method to chech a string for palindrome
    public boolean isPalindrome(String str){

        int left = 0;
        int right = str.length() - 1 ;

        while (left < right) {

            if (str.charAt(left) != str.charAt(right)){

                return false;

            }

            left++;
            right--;

        }
        return true; // It is Palindrome
    }

    public static void main(String[] args) {

        Palindrome p = new Palindrome();

        String s1 = "radar";
        String s2 = "village";

        System.out.println(s1 + " "+ "is palindrome ? " + p.isPalindrome(s1));
        System.out.println(s2 + " " +"is palindrome ? " + p.isPalindrome(s2));
    }



}
