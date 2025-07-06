import java.math.BigInteger;

/**
 * Leetcode 28. Find the Index of the First Occurrence in a String
 * Link: https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
 */
//------------------------------------ Solution 1 -----------------------------------
public class StrStr {
    /**
     * At each index of haystack string try to match if a string starting from that index will match with
     * needle string or not. If there is match move both pointers. In between if there is a mismatch reset the pointer
     * on haystack to next element where it previously started.
     *
     * TC: O(mn) m - needle length n - haystack length
     * SC: O(1)
     */
    public int strStr(String haystack, String needle) {
        int j = 0;
        int n = haystack.length();
        int m = needle.length();
        for (int i = 0; i <= n - m; i++) {
            int k = i;
            while (haystack.charAt(k) == needle.charAt(j)) {
                j++;
                k++;

                if (j == m) {
                    return i;
                }
            }

            j = 0;
        }
        return -1;
    }
}

//------------------------------------ Solution 2 -----------------------------------
class StrStr2 {
    /**
     * Robin Karp rolling hash solution
     *
     * TC: O(m + n)
     * SC: O(1)
     */
    public int strStr(String haystack, String needle) {
        int m = needle.length();
        int n = haystack.length();
        if (m > n) {
            return -1;
        }
        BigInteger hashNiddle = BigInteger.ZERO;
        BigInteger hashHay = BigInteger.ZERO;
        BigInteger k = BigInteger.valueOf(26);

        for (char c: needle.toCharArray()) {
            hashNiddle = hashNiddle.multiply(k).add(BigInteger.valueOf(c - 'a' + 1));
        }

        for (int i = 0; i < n; i++) {
            if (i >= m) {
                hashHay = hashHay.mod(k.pow(m - 1));
            }

            hashHay = hashHay.multiply(k).add(BigInteger.valueOf(haystack.charAt(i) - 'a' + 1));

            if (hashNiddle.equals(hashHay)) {
                return i - m + 1;
            }
        }
        return -1;
    }
}