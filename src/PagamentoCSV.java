import java.io.*;
import java.util.*;

public class PagamentoCSV {

    static class Cobranca {
        int id;
        String cliente;
        double valor;
        String status;

        public Cobranca(int id, String cliente, double valor, String status) {
            this.id = id;
            this.cliente = cliente;
            this.valor = valor;
            this.status = status;
        }

        @Override
        public String toString() {
            return id + "," + cliente + "," + valor + "," + status;
        }
    }

    public static List<Cobranca> lerCSV(String caminhoArquivo) {
        List<Cobranca> cobrancas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                int id = Integer.parseInt(partes[0]);
                String cliente = partes[1];
                double valor = Double.parseDouble(partes[2]);
                String status = partes[3];
                cobrancas.add(new Cobranca(id, cliente, valor, status));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cobrancas;
    }

    public static void escreverCSV(String caminhoArquivo, List<Cobranca> cobrancas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Cobranca c : cobrancas) {
                bw.write(c.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String caminho = "cobrancas.csv";

        // Leitura inicial do arquivo
        List<Cobranca> cobrancas = lerCSV(caminho);

        // Atualiza a primeira cobrança para PAGO (simulação)
        if (!cobrancas.isEmpty()) {
            cobrancas.get(0).status = "PAGO";
        }

        // Reescreve o arquivo com as alterações
        escreverCSV(caminho, cobrancas);

        System.out.println("Arquivo atualizado com sucesso!");
    }
}
