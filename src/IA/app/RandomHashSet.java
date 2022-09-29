package IA.app;

import java.util.ArrayList;
import java.util.HashSet;

public class RandomHashSet<T> {
    HashSet<T> set = new HashSet<>();
    ArrayList<T> data = new ArrayList<>();

    public boolean contains(T object) {
        return set.contains(object);
    }

}
