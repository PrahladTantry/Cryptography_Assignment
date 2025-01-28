public class ExtendedEuclidean {
    public static int[] extendedGCD(int a, int b) 
    {
        if (a == 0) 
            return new int[]{b, 0, 1};
        else 
        {
            int[] result = extendedGCD(b % a, a);
            int gcd = result[0];
            int x = result[2] - (b / a) * result[1];
            int y = result[1];
            return new int[]{gcd, x, y};
        }
    }

    public static void main(String[] args) 
    {
        int a = 56;
        int b = 15;
        int[] result = extendedGCD(a, b);
        System.out.println("GCD: " + result[0]);
        System.out.println("x: " + result[1]);
        System.out.println("y: " + result[2]);
    }
}