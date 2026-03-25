package zest;

public class CompareVersionNumbers {

    /**
     * Compares two version strings.
     *
     * @param version1 first version string
     * @param version2 second version string
     * @return -1 if version1 < version2,
     *          1 if version1 > version2,
     *          0 if equal
     * @throws IllegalArgumentException if either input is null
     */
    public static int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            throw new IllegalArgumentException("Version strings cannot be null");
        }
        //no empty strings
        if (version1.isEmpty() || version2.isEmpty()) {
            throw new IllegalArgumentException("Version strings cannot be empty");
        }
        //no to long strings
        if (version1.length() > 500 || version2.length() > 500) {
            throw new IllegalArgumentException("Version strings cannot exceed 500 characters");
        }

        String[] v1Parts = version1.split("\\.");
        String[] v2Parts = version2.split("\\.");

        int maxLength = Math.max(v1Parts.length, v2Parts.length);

        for (int i = 0; i < maxLength; i++) {
            int num1 = 0;
            int num2 = 0;

            if (i < v1Parts.length) {
                try {
                    num1 = Integer.parseInt(v1Parts[i]); // converts to number
                    if (num1 < 0) { //positive number
                        throw new IllegalArgumentException("Version numbers must be non-negative");
                    }
                } catch (NumberFormatException e) { // all over max int
                    throw new IllegalArgumentException("Version parts must be valid integers");
                }
            }

            if (i < v2Parts.length) {
                try {
                    num2 = Integer.parseInt(v2Parts[i]); // convert int
                    if (num2 < 0) { //positive number
                        throw new IllegalArgumentException("Version numbers must be non-negative");
                    }
                } catch (NumberFormatException e) {// all over max int
                    throw new IllegalArgumentException("Version parts must be valid integers");
                }
            }
            // switched logic (-1 and 1)
            if (num1 > num2) {
                return 1;
            } else if (num1 < num2) {
                return -1;
            }
        }

        return 0;
    }

}
