package app;

import java.util.Comparator;

public class SecondaryComparator implements Comparator<Musica> {

    public int compare(Musica m1, Musica m2) {
        
        int resposta = m1.getNome().compareTo(m2.getNome());

        if (resposta != 0) return resposta;

        resposta = m1.getAutor().compareTo(m2.getAutor());

        if (resposta != 0) return resposta;

        PrimaryComparator pc = new PrimaryComparator();
        return pc.compare(m1, m2);
    }
    
}
