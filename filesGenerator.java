package Versao1;

import java.io.*;

import java.util.Random;

public class filesGenerator {
    public static void main(String[] args) throws IOException {

        BufferedWriter writerCanal = new BufferedWriter(new FileWriter("canais.txt"));
        BufferedWriter writerVideo = new BufferedWriter(new FileWriter("videos.txt"));

        for(int i=0; i<20000;i++){
            Canal c= randCanal();

            writerCanal.append(Integer.toString(c.getId()));
            writerCanal.append("\t");
            writerCanal.append(c.getNome());
            writerCanal.append("\t");
            writerCanal.append(Integer.toString(c.getSubscritores()));
            writerCanal.append("\t");
            for(int j=0;j<c.getCategorias().length;j++) {
                writerCanal.append(c.getCategorias()[j]);
                writerCanal.append(" ");
            }
            int rand= (int) Math.ceil((Math.random()*100));

            for(int j=0; j<rand;j++){

                int temp= (int) Math.ceil((Math.random()*5));
                String title="";
                for(int l=0;l<temp;l++){
                    title+=" "+genRandString();
                }
                int rand1=(int) Math.ceil(Math.random()*c.getCategorias().length);
                String[] cat= new String[rand1];
                int l=0;
                while(l<cat.length){
                    if(cat.length==c.getCategorias().length) {
                        System.arraycopy(c.getCategorias(), 0, cat, 0, cat.length);
                        break;
                    }
                    int rand2=(int) Math.floor(Math.random()*c.getCategorias().length);
                    boolean contains=false;
                    for(int v=0;v<cat.length;v++){
                        try {
                            if (cat[v].equalsIgnoreCase(c.getCategorias()[rand2]))
                                contains = true;
                        }catch (NullPointerException e){

                        }
                    }
                    if(!contains) {
                        cat[l] = c.getCategorias()[rand2];
                        l++;
                    }
                }

                Video v= new Video(title,cat,c.getId());
                v.setVis((int) Math.ceil(Math.random()*1000000));
                writerVideo.append(Integer.toString(v.getId()));
                writerVideo.append("\t");
                writerVideo.append(Integer.toString(v.getIdCanal()));
                writerVideo.append("\t");
                writerVideo.append(Integer.toString(v.getVisualizacoes()));
                writerVideo.append("\t");
                for(l=0;l<v.getCategorias().length;l++) {
                    writerVideo.append(v.getCategorias()[l]);
                    writerVideo.append(" ");
                }
                writerVideo.append("\t");
                writerVideo.append(v.getNome());
                writerVideo.append("\t");
                writerVideo.append("\n");

            }

            writerCanal.append("\n");
        }
        writerCanal.close();
        writerVideo.close();
        System.exit(0);
    }
    public static String randCategoria(){
        String[] categorias={"Comedia","Acao","Videojogos","Entretenimento","Animais","Desporto","Musica","Politica","Automoveis","Moda","Tecnologia"};
        int rand = (int) Math.floor( Math.random()*categorias.length);
        return categorias[rand];
    }
    public static Canal randCanal(){
        int rand= (int) Math.ceil((Math.random()*3));
        String[] cat= new String[rand];
        int l=0;
        while(l<cat.length){
            String categoria=randCategoria();
            boolean contains=false;
            for(int v=0;v<cat.length;v++){
                try {
                    if (cat[v].equalsIgnoreCase(categoria))
                        contains = true;
                }catch (NullPointerException e){

                }
            }
            if(!contains) {
                cat[l] = categoria;
                l++;
            }
        }
        int randSubs= (int) Math.ceil(Math.random()*1000000);

        return new Canal(genRandString(),cat,randSubs);
    }
    public static String genRandString(){
        String alphabet= "abcdefghijklmnopqrstuvwxyz";
        String s = "";
        Random random = new Random();
        int randomLen = 1+random.nextInt(12);
        for (int i = 0; i < randomLen; i++) {
            char c = alphabet.charAt(random.nextInt(26));
            s+=c;
        }
        return s;
    }
}
