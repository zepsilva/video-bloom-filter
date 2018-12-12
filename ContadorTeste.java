package Versao1;

import static java.lang.System.*;

public class ContadorTeste {
    public static void main(String[] args) {

        ContadorEstocástico c= new ContadorEstocástico();
        for (int i = 0; i < 1000000; i++) {
            c.counter();
        }
        out.println("Número de elementos: " +c.getCount());
    }
}
