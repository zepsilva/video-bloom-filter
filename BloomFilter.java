package Versao1;

import java.util.Random;

public class BloomFilter {
    private final int bloomSize;
    private final int[] filter;
    private final int[] hashSeeds;
    private final int numHash;
    private int count;

    public BloomFilter(int size, int numElem) {
        bloomSize = size;
        filter = new int[size];
        numHash = (int) Math.floor((size / numElem) * Math.log(2));
        Random r = new Random(System.currentTimeMillis());
        hashSeeds = genSeeds(r);
        count = 0;
    }

    public void addElem(String elem) {
        int[] inds = getInds(elem);
        for(int i = 0; i < inds.length; i++) {
            filter[inds[i]]++;
        }
        count++;
    }

    public boolean contains(String elem) {
        int[] inds = getInds(elem);
        for(int i = 0; i < inds.length; i++) {
            if(filter[inds[i]] != 0) return true;  // Pode gerar falsos positivos, caso haja colisões
        }
        return false;
    }

    public void remove(String elem) {
        int[] inds = getInds(elem);
        for(int i = 0; i < inds.length; i++) {
            filter[inds[i]]--;
        }
    }

    public int count() {
        return count;
    }

    private int[] getInds(String elem) {
        int[] inds = new int[numHash];
        int hashed = elem.hashCode();
        for(int i = 0; i < numHash; i++) {
            inds[i] = Math.abs((hashed^hashSeeds[i]) % filter.length);
        }
        return inds;
    }

    public int[] genSeeds(Random r) {
        int[] hashSeeds = new int[numHash];
        for(int i = 0; i < numHash; i++) {
            hashSeeds[i] = r.nextInt();
        }
        return hashSeeds;
    }

    public int[] getFilter() {
        return filter;
    }
}
