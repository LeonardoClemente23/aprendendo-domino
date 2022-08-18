package jogo.app;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Tabuleiro {
    private Deque<List<Integer>> pecasNaMesa = new LinkedList<>();
    private Integer cabeca;
    private Integer rabo;

    public Deque<List<Integer>> getPecasNaMesa() {
        return pecasNaMesa;
    }

    public void adicionaPeca(List<Integer> peca) {
        if (pecasNaMesa.isEmpty()) {
            pecasNaMesa.addFirst(peca);
            cabeca = peca.get(0);
            rabo = peca.get(1);
            return;
        }

        for (int i = 0; i < 2; i++) {
            if (peca.get(i).equals(cabeca)) {
                pecasNaMesa.addFirst(peca);
                cabeca = peca.get((i + 1) % 2);
                return;
            }
            if (peca.get(i).equals(rabo)) {
                pecasNaMesa.addLast(peca);
                rabo = peca.get((i + 1) % 2);
                return;
            }
        }
    }

    public int getCabeca() {
        return cabeca;
    }

    public void setCabeca(int cabeca) {
        this.cabeca = cabeca;
    }

    public int getRabo() {
        return rabo;
    }

    public void setRabo(int rabo) {
        this.rabo = rabo;
    }
}
