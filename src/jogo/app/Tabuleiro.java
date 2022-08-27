package jogo.app;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Tabuleiro {
    private Deque<List<Integer>> pecasNaMesa = new LinkedList<>();
    private Integer cabeca;
    private Integer rabo;
    private List<Integer> ultimaPeca = new ArrayList<>();

    public List<Integer> getUltimaPeca() {
        return ultimaPeca;
    }

    public void setUltimaPeca(List<Integer> ultimaPeca) {
        this.ultimaPeca = ultimaPeca;
    }

    public Deque<List<Integer>> getPecasNaMesa() {
        return pecasNaMesa;
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

    public void adicionaPeca(List<Integer> peca) {
        if (pecasNaMesa.isEmpty()) {
            pecasNaMesa.addFirst(peca);
            cabeca = peca.get(0);
            rabo = peca.get(1);
            return;
        }

        if (adicionaPecaNaCabeca(peca)) {
            return;
        }

        if (adicionaPecaNoRabo(peca)) {
            return;
        }
    }

    public boolean adicionaPecaNaCabeca(List<Integer> peca) {
        for (int i = 0; i < 2; i++) {
            if (peca.get(i).equals(cabeca)) {
                pecasNaMesa.addFirst(peca);
                cabeca = peca.get((i + 1) % 2);
                return true;
            }
        }
        return false;
    }

    public boolean adicionaPecaNoRabo(List<Integer> peca) {
        for (int i = 0; i < 2; i++) {
            if (peca.get(i).equals(rabo)) {
                pecasNaMesa.addLast(peca);
                rabo = peca.get((i + 1) % 2);
                return true;
            }
        }
        return false;
    }

    public void abreJogo() {
        if (this.ultimaPeca.equals(this.pecasNaMesa.peekFirst())) {
            List<Integer> peca = this.pecasNaMesa.pollFirst();
            adicionaPecaNoRabo(peca);
        }

        if (this.ultimaPeca.equals(this.pecasNaMesa.peekLast())) {
            List<Integer> peca = this.pecasNaMesa.pollLast();
            adicionaPecaNaCabeca(peca);
        }
    }
}
