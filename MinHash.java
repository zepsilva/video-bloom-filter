package Versao1;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;

public class MinHash {

    public double similarity(Set<String> keywords, Set<String> title, int numHash){

        long[][] values = new long[2][numHash];

        Arrays.fill(values[0], Long.MAX_VALUE);
        Arrays.fill(values[1], Long.MAX_VALUE);

        Random r = new Random(System.currentTimeMillis());

        long[]  seeds= getHash(r,numHash);

        int j = 0;

        for (int i = 0; i < numHash; i++){

            for(String s : title)
                values[1][i] = Math.min(values[1][i], s.hashCode()^seeds[i]);

            for(String s : keywords)
                values[0][i] = Math.min(values[0][i], s.hashCode()^seeds[i]);

            if(values[0][i] == values[1][i]){
                j++;
            }
        }
        return (double)j / numHash;
    }

    private long[] getHash(Random r, int numHash){
        long[] hashSeeds = new long[numHash];
        for(int i = 0; i < numHash; i++) {
            hashSeeds[i] = r.nextLong();
        }
        return hashSeeds;
    }
}

