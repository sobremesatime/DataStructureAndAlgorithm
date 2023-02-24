import org.junit.Test;

import java.util.*;

public class DataTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        String b = scanner.nextLine();
        StringBuilder res_a = new StringBuilder();
        StringBuilder res_b = new StringBuilder();
        for (int i = 0; i < a.length(); i++){
            if (a.charAt(i) >= 'a' && a.charAt(i) <= 'z') {
                int location = (a.charAt(i) - 'a' + 1) % 26;
                res_a.append((char)('A' + location));
            }
            else if (a.charAt(i) >= 'A' && a.charAt(i) <= 'Z'){
                int location = (a.charAt(i) - 'A' + 1) % 26;
                res_a.append((char)('a' + location));
            }
            else if (a.charAt(i) >= '0' && a.charAt(i) <= '9'){
                int location = (a.charAt(i) - '0' + 1) % 10;
                res_a.append((char)('0' + location));
            }else
                res_a.append(a.charAt(i));
        }
        System.out.println(res_a);
        for (int i = 0; i < b.length(); i++){
            if (b.charAt(i) >= 'a' && b.charAt(i) <= 'z') {
                int location = (b.charAt(i) - 'a' - 1) >= 0 ? (b.charAt(i) - 'a' - 1) : 25;
                res_b.append((char)('A' + location));
            }
            else if (b.charAt(i) >= 'A' && b.charAt(i) <= 'Z'){
                int location = (b.charAt(i) - 'A' - 1) >= 0 ? (b.charAt(i) - 'A' - 1) : 25;
                res_b.append((char)('a' + location));
            }
            else if (b.charAt(i) >= '0' && b.charAt(i) <= '9'){
                int location = (b.charAt(i) - '0' - 1) >= 0 ? (b.charAt(i) - '0' - 1) : 9;
                res_b.append((char)('0' + location));
            }else
                res_b.append(b.charAt(i));
        }
        System.out.println(res_b);
    }
}
