package kcci.surfaceviewtest;

public class Random
{
    //                  2       4 -> 2,3,4
    public static int get(int min, int max) {
        if(min>max) return -1;
        int difference = max - min + 1; // 4-2 +1=3

        int value = (int)(Math.random()*difference); // 0~2
        int result = min + value; // 2 + (0~2) -> 2, 3, 4

        return result;
    }
}
