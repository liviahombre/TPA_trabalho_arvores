package app;

import java.util.Comparator;

public class PrimaryComparator implements Comparator<Musica> {
    
    public int compare(Musica m1, Musica m2) {

        return Long.compare(m1.getIndex(), m2.getIndex());

    }

}