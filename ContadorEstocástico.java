package Versao1;

public class ContadorEstocástico {

    private static int count=0;

    public ContadorEstocástico(){}

    public void counter() {
        int c = 0;
        if(Math.random() < 0.5) {
            c++;
        }
        count+=c*2;
    }
    public int getCount(){
        return count;
    }
}
