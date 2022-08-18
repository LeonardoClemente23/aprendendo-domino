package jogo.app;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Estatisticas {
    // Contabiliza quantidade de vitorias de cada jogador e o tipo de vitoria,
    // por mão vazia ou por pontos.
    private static Map<Integer, Map<Boolean, Integer>> placar = new HashMap<>();
    private static Map<Boolean, Integer> tiposVitorias = new HashMap<>();

    public Estatisticas() {
        tiposVitorias.put(true, 0);
        tiposVitorias.put(false, 0);
        for (int jogadorId = 1; jogadorId <= 4; jogadorId++) {
            Map<Boolean, Integer> tiposVitoriasJogador = new HashMap<>();
            tiposVitoriasJogador.put(true, 0);
            tiposVitoriasJogador.put(false, 0);
            placar.put(jogadorId, tiposVitoriasJogador);
        }
    }

    public Map<Integer, Map<Boolean, Integer>> getPlacar() {
        return placar;
    }

    public Map<Boolean, Integer> getTiposVitorias() {
        return tiposVitorias;
    }

    public Map<Boolean, Integer> getTiposVitoriasJogador(int idJogador) {
        return placar.get(idJogador);
    }

    public void contagemPlacar(Boolean vitoriaMaoVazia, int idJogador) {
        tiposVitorias.computeIfPresent(vitoriaMaoVazia, (k, v) -> v + 1);

        Map<Boolean, Integer> tiposVitoriasJogador = getTiposVitoriasJogador(idJogador);
        tiposVitoriasJogador.computeIfPresent(vitoriaMaoVazia, (k, v) -> v + 1);
    }

    private Integer calculaTotalJogos() {
        return tiposVitorias.entrySet()
                .stream()
                .mapToInt((entry) -> entry.getValue())
                .sum();
    }

    private String porcentagem(double totalEvento, Integer espacoAmostral) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(100 * totalEvento / espacoAmostral) + "%";
    }

    private void mostraPlacarIndividual(Entry<Integer, Map<Boolean, Integer>> estatisticasJogador) {
        Integer totalVitorias = (estatisticasJogador.getValue().get(true) + estatisticasJogador.getValue().get(false));

        System.out.println("Joagador " + estatisticasJogador.getKey() + ": ");
        System.out
                .println("Total vitórias: "
                        + totalVitorias + "(" + porcentagem(totalVitorias, calculaTotalJogos()) + ")");
    }

    private void mostraPlacarTipoVitoria(Entry<Boolean, Integer> tiposVitoria, Integer n) {

        if (tiposVitoria.getKey()) {
            System.out.println("Vitorias por mão vazia: " + tiposVitoria.getValue() + "("
                    + porcentagem(tiposVitoria.getValue(), n) + ")");
        } else {
            System.out.println("Vitorias por pontos: " + tiposVitoria.getValue() + "("
                    + porcentagem(tiposVitoria.getValue(), n) + ")");
        }
    }

    // Mostrar porcentagem ao lado do total
    public void mostraPlacarFull() {
        System.out.println("Placar:");
        for (Map.Entry<Integer, Map<Boolean, Integer>> estJogador : placar.entrySet()) {
            mostraPlacarIndividual(estJogador);
            Integer n = estJogador.getValue().entrySet()
                    .stream()
                    .mapToInt((entry) -> entry.getValue())
                    .sum();

            for (Map.Entry<Boolean, Integer> tiposVitoria : estJogador.getValue().entrySet()) {
                mostraPlacarTipoVitoria(tiposVitoria, n);
            }
            System.out.println();
        }

        System.out.println("Total de vitórias por tipo:");
        for (Map.Entry<Boolean, Integer> tiposVitoria : tiposVitorias.entrySet()) {
            mostraPlacarTipoVitoria(tiposVitoria, calculaTotalJogos());
        }
    }

}
