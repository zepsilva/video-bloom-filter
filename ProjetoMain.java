package Versao1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ProjetoMain {

    private static HashMap<Integer,Canal> canais= new HashMap<>();
    private static HashMap<Integer,Video> videos= new HashMap<>();

    private static Scanner sc= new Scanner(System.in);

    private static int numVideos=0;

    public static void main(String[] args) throws IOException {
        File file = new File("canais.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        ContadorEstocástico contador= new ContadorEstocástico();

        String st;

        while ((st = br.readLine()) != null) {
            String[] temp=st.split("\t");
            Canal c=new Canal(temp[1],temp[3].split(" "),Integer.parseInt(temp[2]));
            c.setId(Integer.parseInt(temp[0]));
            canais.put(c.getId(),c);
        }
        br.close();

        file = new File("videos.txt");

        br = new BufferedReader(new FileReader(file));

        st="";
        while ((st = br.readLine()) != null) {
            String[] temp= st.split("\t");
            Video v= new Video(temp[4],temp[3].split(" "),Integer.parseInt(temp[1]));
            v.setId(Integer.parseInt(temp[0]));
            v.setVis(Integer.parseInt(temp[2]));
            videos.put(v.getId(),v);
            Canal c= canais.get(v.getIdCanal());
            c.addVideo(v);
            canais.replace(v.getIdCanal(),c);
            contador.counter();
        }

        br.close();

        numVideos= contador.getCount();

        BloomFilter bf= new BloomFilter(4*videos.size(),videos.size());

        for(int i=1;i<videos.size()+1;i++){
            Video v= videos.get(i);
            String s=""+v.getId()+v.getNome().hashCode();
            bf.addElem(s);
        }

        menu();


        boolean cont=true;
        while (cont){
            System.out.println("Continuar?(Y/n)");
            String s=sc.nextLine();
            if(s.equalsIgnoreCase("n")) cont=false;
            else menu();
        }
        sc.close();
    }
    public static LinkedList<Video> getSimilarVideos(String keyString){
        LinkedList<Video> similar= new LinkedList<>();
        MinHash m= new MinHash();
        String[] set= keyString.split(" ");
        Set<String> stringSet= new HashSet<>();
        for(String s: set){
            stringSet.add(s);
        }
        for(int i=1;i<videos.size()+1;i++){
            Video v= videos.get(i);
            String[] temp= v.getNome().split(" ");
            Set<String> title= new HashSet<>();

            for(String s: temp){
                title.add(s);
            }
            if(m.similarity(stringSet,title,20)>0)
                similar.add(v);
        }
        return similar;
    }
    public static void menu1Op1(){
        System.out.println("Insira um titulo: ");

        String title=sc.nextLine();

        LinkedList<Video> similar=getSimilarVideos(title);
        printVidsByVis(similar);

        int op= 0;
        while (op!=1 && op!=2) {
            System.out.println();
            System.out.println("Filtrar por: ");
            System.out.print("\t");
            System.out.println("1-Categorias.");
            System.out.print("\t");
            System.out.println("2-Sair.");
            System.out.println();

            op = Integer.parseInt(sc.nextLine());;
        }

        switch (op) {
            case 1:
                printVidsByVis(categorySimilarity(similar));
                break;
            case 2:
                break;
        }


    }
    public static void menu1Op2(){
        for(int i=1;i<videos.size()+1;i++){
            System.out.println(videos.get(i).toString(canais));
        }
    }
    public static void menu1Op3(){
        for(int i=1;i<canais.size()+1;i++){
            System.out.println(canais.get(i).toString());
        }
    }
    public static LinkedList<Video> categorySimilarity(LinkedList<Video> sim){
        Set<String> stringSet= new HashSet<>();
        String[] categorias={"Comedia","Acao","Videojogos","Entretenimento","Animais","Desporto","Musica","Politica","Automoveis","Moda","Tecnologia"};

        int op=0,i=0;
        while (op!=12 && i<4) {
            System.out.print("\t");
            System.out.println("1-Comedia.");
            System.out.print("\t");
            System.out.println("2-Acao.");
            System.out.print("\t");
            System.out.println("3-Videojogos.");
            System.out.print("\t");
            System.out.println("4-Entretenimento.");
            System.out.print("\t");
            System.out.println("5-Animais.");
            System.out.print("\t");
            System.out.println("6-Desporto.");
            System.out.print("\t");
            System.out.println("7-Musica.");
            System.out.print("\t");
            System.out.println("8-Politica.");
            System.out.print("\t");
            System.out.println("9-Automoveis.");
            System.out.print("\t");
            System.out.println("10-Moda.");
            System.out.print("\t");
            System.out.println("11-Tecnologia.");
            System.out.print("\t");
            System.out.println("12-Sair.");

            op=Integer.parseInt(sc.nextLine());
            if(op>1 && op<12){
                stringSet.add(categorias[op-1]);
                i++;
            }
        }

        LinkedList<Video> similar= new LinkedList<>();
        MinHash m= new MinHash();

        for(i=0;i<sim.size();i++){
            Video v= sim.get(i);
            String[] temp= v.getCategorias();
            Set<String> cat= new HashSet<>();

            for(String s: temp){
                cat.add(s);
            }
            if(m.similarity(stringSet,cat,20)==1)
                similar.add(v);
        }
        return similar;
    }
    public static void printVidsByVis(LinkedList<Video> vids){
        vids.sort((Video v1,Video v2)-> v2.getVisualizacoes()-v1.getVisualizacoes());
        for(int i=0;i< vids.size();i++){
            System.out.println(vids.get(i).toString(canais));
        }
    }
    public static void menu(){

        int op=0;
        while (op<1 || op>3) {
            System.out.println("Numero Videos: " + numVideos);
            System.out.println("1-Pesquisar por nome.");
            System.out.println("2-Listar Videos.");
            System.out.println("3-Listar Canais.");
            System.out.println("4-Sair.");
            System.out.println();
            op= Integer.parseInt(sc.nextLine());
        }

        switch (op) {
            case 1:
                menu1Op1();
                break;
            case 2:
                menu1Op2();
                break;
            case 3:
                menu1Op3();
                break;
            default:
                break;
        }

    }
}

