package Versao1;

import java.util.LinkedList;

public class Canal {
    private String nome;
    private int subscritores;
    private String[] categorias;
    private static int id=0;
    private LinkedList<Video> videos;

    public Canal(String nome,String[] categorias,int subscritores){
        this.nome=nome;
        this.categorias=categorias;
        this.subscritores=subscritores;
        videos=new LinkedList<>();
        id++;
    }
    public void addVideo(Video v){
        videos.add(v);
    }
    public void setId(int i){
        id=i;
    }
    public String getNome(){
        return nome;
    }
    public int getSubscritores(){
        return subscritores;
    }
    public String[] getCategorias(){
        return categorias;
    }
    public int getId(){
        return id;
    }
    public Video[] getVideos(){
        return videos.toArray(Video[]::new);
    }
    public String toString(){
        StringBuilder s= new StringBuilder();
        s.append("Nome: ");
        s.append(nome);
        s.append("\t");
        s.append("Categorias: ");
        for(int i=0; i<categorias.length;i++){
            s.append(categorias[i]);
            s.append(" ");
        }
        return s.toString();
    }
}
