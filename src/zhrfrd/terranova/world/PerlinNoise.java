package zhrfrd.terranova.world;

import java.util.Random;

public class PerlinNoise {
    private int[] permutation;
    private static final int PERMUTATION_SIZE = 256;

    public PerlinNoise(long seed) {
        permutation = new int[PERMUTATION_SIZE * 2];
        Random random = new Random(seed);

        for (int i = 0; i < PERMUTATION_SIZE; i++) {
            permutation[i] = i;
        }

        for (int i = 0; i < PERMUTATION_SIZE; i++) {
            int j = random.nextInt(PERMUTATION_SIZE);
            int tmp = permutation[i];
            permutation[i] = permutation[j];
            permutation[j] = tmp;
        }

        for (int i = 0; i < PERMUTATION_SIZE; i++) {
            permutation[PERMUTATION_SIZE + i] = permutation[i];
        }
    }

    public double noise(double x, double y) {
        int xi = (int) Math.floor(x) & (PERMUTATION_SIZE - 1);
        int yi = (int) Math.floor(y) & (PERMUTATION_SIZE - 1);
        double xf = x - Math.floor(x);
        double yf = y - Math.floor(y);

        double u = fade(xf);
        double v = fade(yf);

        int aa = permutation[permutation[xi] + yi];
        int ab = permutation[permutation[xi] + yi + 1];
        int ba = permutation[permutation[xi + 1] + yi];
        int bb = permutation[permutation[xi + 1] + yi + 1];

        double x1, x2, y1;
        x1 = lerp(grad(aa, xf, yf), grad(ba, xf - 1, yf), u);
        x2 = lerp(grad(ab, xf, yf - 1), grad(bb, xf - 1, yf - 1), u);
        y1 = lerp(x1, x2, v);

        return (y1 + 1) / 2; // Normalize to [0, 1]
    }

    private double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }

    private double grad(int hash, double x, double y) {
        int h = hash & 3;
        double u = h < 2 ? x : y;
        double v = h < 2 ? y : x;
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }
}
