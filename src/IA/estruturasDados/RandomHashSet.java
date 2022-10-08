package IA.estruturasDados;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import IA.genoma.Gene;

public class RandomHashSet<T extends Gene> {
    HashSet<T> set = new HashSet<>();
    ArrayList<T> data = new ArrayList<>();

    public ArrayList<T> getData() {
        return data;
    }

    public boolean contains(T object) {
        return set.contains(object);
    }

    public T randomElement() throws Exception {
        if (set.size() > 0) {
            Random random = new Random();
            int bound = data.size();
            return data.get(random.nextInt(bound));
        }
        throw new Exception("Empty Set.");
    }

    public void add(T object) {
        if (!set.contains(object)) {
            set.add(object);
            data.add(object);
        }

    }

    public void addSorted(T object) {
        for (int i = 0; i < this.size(); i++) {
            int innovation = (data.get(i)).getInnovationNumber();
            if (object.getInnovationNumber() < innovation) {
                data.add(i, object);
                set.add(object);
                return;
            }
        }
        data.add(object);
        set.add(object);
    }

    public void clear() {
        set.clear();
        data.clear();
    }

    public T getElement(int index) throws Exception {
        if (index < 0 || index >= size()) {
            throw new Exception("Invalid index.");
        }
        return data.get(index);
    }

    // public T getElementByTemplate(T template){}

    public void remove(int index) throws Exception {
        if (index < 0 || index >= size()) {
            throw new Exception("Invalid index.");
        }
        set.remove(data.get(index));
        data.remove(index);
    }

    public void remove(T object) {
        set.remove(object);
        data.remove(object);
    }

    public int size() {
        return data.size();
    }
}
