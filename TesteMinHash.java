package Versao1;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TesteMinHash {
    public static void main(String[] args) throws IOException {
        Set<String> set1= new HashSet<>();
        set1.add("aa");
        set1.add("bb");
        set1.add("cc");
        set1.add("dd");

        Set<String> set2= new HashSet<>();
        set2.add("aa");
        set2.add("bb");
        set2.add("cc");
        set2.add("dd");

        Set<String> set3= new HashSet<>();
        set3.add("aa");
        set3.add("bb");
        set3.add("cc");
        set3.add("xd");

        MinHash m= new MinHash();
        System.out.println("Similaridade set1 e set2: "+m.similarity(set1,set2,20));
        System.out.println("Similaridade set1 e set3: "+m.similarity(set1,set3,20));
    }
}
