package jogo.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SalvaDados {
    private List<String> linhasRelatorio = new ArrayList<>();

    public SalvaDados() {
        cabecalhoGeral();
        indice();
    }

    public List<String> getLinhasRelatorio() {
        return linhasRelatorio;
    }

    public void cabecalhoGeral() {
        linhasRelatorio.add("Estatística dos jogos:");
        linhasRelatorio.add("");
        linhasRelatorio.add("Total de jogos: 4.000.000");
        linhasRelatorio.add("");
    }

    public void indice() {
        linhasRelatorio.add("Índice situações:");
        linhasRelatorio.add("   1 - Todos os jogos começam pelo mesmo jogador (1)");
        linhasRelatorio.add("   2 - O jogador que começa é aleatório");
        linhasRelatorio
                .add("   3 - Em cada jogo, o primeiro a jogar é o que está à esquerda do vencedor do jogo anterior.");
        linhasRelatorio.add(
                "   4 - Em cada jogo, o primeiro a jogar é o que está à esquerda de quem começou o jogo anterior.");
        linhasRelatorio.add("");
    }

    public void cabecalhoSituacao(int situacao) {
        linhasRelatorio.add(situacao + "ª situação:");
    }

    public void gravaEstatisticas(Estatisticas estatisticas) {
        this.linhasRelatorio.addAll(estatisticas.getDados());

    }

    public void geraRelatorio() {
        try {
            Path path = Path.of("relatorios/relatório-proporção-de-vitórias.txt");
            Files.write(path, this.linhasRelatorio);

            System.out.println("Relatorio criado\n\n");

        } catch (IOException exception) {
            System.out.println("Não foi possível criar o relatório:");
            System.out.println(exception.getMessage());
            System.out.println(exception.getStackTrace());
        }
        this.linhasRelatorio.clear();
    }

}
