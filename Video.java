package Versao1;

import java.util.HashMap;

public class Video {
    private String nome;
    private String[] categorias;
    private static int id=0;
    private int idCanal;
    private int visualizacoes;

    public Video(String nome,String[] categorias,int canal){
        this.nome=nome;
        this.categorias=categorias;
        id++;
        idCanal=canal;
    }
    public void setVis(int n){
        visualizacoes=n;
    }
    public void setId(int n){
        id=n;
    }
    public String getNome() {
        return nome;
    }

    public String[] getCategorias() {
        return categorias;
    }

    public int getId() {
        return id;
    }

    public int getIdCanal() { return idCanal; }

    public int getVisualizacoes() {
        return visualizacoes;
    }
    public String toString(HashMap<Integer,Canal> canais){
        StringBuilder s= new StringBuilder();
        s.append("\t");
        s.append("Visualizacoes: ");
        s.append(visualizacoes);
        s.append("\t");
        s.append("Nome: ");
        s.append(nome);
        s.append("\t");
        s.append("Canal: ");
        s.append(canais.get(idCanal).getNome());
        s.append("\t");
        s.append("Categorias: ");
        for(int i=0; i<categorias.length;i++){
            s.append(categorias[i]);
            s.append(" ");
        }
        return s.toString();
    }
}
