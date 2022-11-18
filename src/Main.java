import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

//    static void Input(int[][] a, int k, int n){
//        for (int i = 0; i < k; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print("a["+i+"]["+j+"] = "); a[i][j] = scan.nextInt();
//            }
//        }
//    }

    static void Create(int[][] a, int k, int n){
        int min = -10; int max = 10;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = (int) (Math.random() * (max - min) + min);
            }
        }
    }

    static void Print(int[][] a, int k, int n){
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d\t|  ", a[i][j]);
            }
            System.out.println();
        }
    }

    static int Part1_count(int[][] a, int k, int n){
        int sum = 0;
        int lessThan0;
        for (int i = 0; i < n; i++) {
            lessThan0 = 0;
            for (int j = 0; j < k; j++) {
                if(a[j][i] < 0){
                    lessThan0++;
                    break;
                }
            }
            if(lessThan0 == 0){
                for (int j = 0; j < k; j++) {
                    sum += a[j][i];
                }
            }
        }
        return sum;
    }

    static int Part2_nextDiagonalSum(int[][] a, int k, int n, int count){
        int nextDiagonal = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                if (i + j == count) {
                    nextDiagonal += Math.abs(a[i][j]);
                }
            }
        }
        return nextDiagonal;
    }

    static void Part2_compareToNext(int[][] a, int k, int n){
        int minimal = Math.abs(a[0][0]) + Math.abs(a[k - 1][n - 1]);
        int count = 1;
        int independent = k > n ? k + 1 : n + 1;
        int nextDiagonal = Part2_nextDiagonalSum(a, k, n, count);
        while(count < independent){
            if (nextDiagonal < minimal) {
                minimal = nextDiagonal;
            }
            count++;
            nextDiagonal = Part2_nextDiagonalSum(a, k, n,  count);
        }
        System.out.println("minimal: " + minimal);
    }

    public static void main(String[] args) {
        System.out.print("K = "); int k = scan.nextInt();
        System.out.print("N = "); int n = scan.nextInt();

        int[][] a = new int[k][n];
//      Input(a, k, n);
        Create(a, k, n);
        Print(a, k, n);

        int sum = Part1_count(a, k, n);
        if (sum == 0){
            System.out.println("There are no such columns");
        } else {
            System.out.println("sum = " + sum);
        }
        Part2_compareToNext(a, k, n);
    }
}