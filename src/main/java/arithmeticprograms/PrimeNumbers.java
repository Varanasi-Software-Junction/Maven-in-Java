/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arithmeticprograms;

import static java.lang.Math.*;

public class PrimeNumbers {

    public static void findAllPrimes1(int n) {
        int count = 0;
        out:
        for (int i = 2; i <= n; i++) {
            int limit = (int) (sqrt(i));
            for (int j = 2; j <= limit; j++) {
                if (i % j == 0) {
                    continue out;
                }
            }
            System.err.printf(",%s", i);
            count++;
        }
        System.out.printf("\nCount=%s", count);

    }

    public static void main(String[] args) {
        findAllPrimes1(500);
    }
}
