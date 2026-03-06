package zest;

public class AddBinary {

    /**
     * Adds two binary strings and returns the result as a binary string.
     *
     * @param a first binary string
     * @param b second binary string
     * @return binary sum of a and b
     * @throws IllegalArgumentException if a or b is null
     */
    public static String addBinary(String a, String b) {
        if (a == null || b == null ||a.isEmpty() || b.isEmpty()) {
            throw new IllegalArgumentException("Input strings cannot be null or empty");
        }

        if(a.length() > 10000 || b.length() > 10000) {
            throw new IllegalArgumentException("Input strings cannot be longer than 10^4 characters");
        }
        
        for(char c : a.toCharArray()) {
            if (c != '0' && c != '1') {
                throw new IllegalArgumentException("Input string a contains non-binary character");
            }
        }

        for(char c : b.toCharArray()) {
            if (c != '0' && c != '1') {
                throw new IllegalArgumentException("Input string b contains non-binary character");
            }
        }

        if(a.startsWith("0") && !a.equals("0")) {
            throw new IllegalArgumentException("Input string a has leading zeros");
        }

        if(b.startsWith("0") && !b.equals("0")) {
            throw new IllegalArgumentException("Input string b has leading zeros");
        }

        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0) {

            int sum = carry;

            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }

            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }

            result.append(sum % 2);
            carry = sum / 2;
        }

        if (carry != 0) {
            result.append(carry);
        }

        return result.reverse().toString();
    }

}