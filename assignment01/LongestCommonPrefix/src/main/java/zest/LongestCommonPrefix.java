package zest;

public class LongestCommonPrefix {

    /**
     * Returns the longest common prefix string amongst an array of strings.
     * If there is no common prefix, returns an empty string "".
     *
     * @param strs array of strings
     * @return longest common prefix
     * @throws IllegalArgumentException if strs is null
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        // FIX: remove the empty check of strs
//       if (strs.length == 0) {
//           return "";
//       }

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(prefix)) {
                // FIX: remove the empty check of prefix
//                if (prefix.isEmpty()) {
//                    return "";
//                }
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }

        return prefix;
    }
}