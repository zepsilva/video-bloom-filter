package Versao1;

import static java.lang.System.*;

public class FilterTest {
    public static void main(String[] args) {

        BloomFilter bf = new BloomFilter(4000000, 1000000);

        String[] strings = genString(1000000);
        for (String s : strings) {
            bf.addElem(s);
        }
        out.println();

        /*
         * Descomentar para visualizar a dispersão dos indices resultantes das hash functions
         *
        int[] filter = bf.getFilter();

        for (int i : filter) {
            out.println(i);
        }*/

        out.println();

        int cont0 = 0;
        for (String s : strings) {
            if (!bf.contains(s)) { // Dá obrigatóriamente false (Bloom Filter garante não existir falsos negativos)
                cont0++;
            }
        }
        out.println("Percentagem de falsos negativos: " + (cont0 / strings.length) * 100 + "%");

        int cont1 = 0;
        for (String s : strings) {
            bf.remove(s);
            if (bf.contains(s)) { // Deve dar false (Podem ocorrer falsos positivos)
                cont1++;
            }
        }

        out.println("Percentagem de falsos positivos: "+ ((double) cont1/strings.length)*100 +"%");

        out.println();

        out.println("Número de elementos: "+ bf.count());
    }
    private static String[] genString(int num) {
        String[] strings = new String[num];
        String alfabeto = "abcdefghijklmnopqrstuvwxyz123456789";
        for(int i = 0; i < num; i++) {
            String s = "";
            for (int j = 0; j < 20; j++) {
                s += String.valueOf(alfabeto.charAt((int) Math.floor(Math.random() * 26)));
            }
            strings[i] = s;
        }
        return strings;
    }
}
