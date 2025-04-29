package app;

import java.util.Comparator;

public class SecondaryComparator implements Comparator<Musica> {

    public int compare(Musica m1, Musica m2) {
        
        return m1.getNome().compareTo(m2.getNome());
    }
    
}
