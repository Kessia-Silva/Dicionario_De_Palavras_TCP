
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dicionario implements Runnable{
    private final Socket socket;
        private PrintWriter out;

        Dicionario(Socket socket) {
            this.socket = socket;
        }
       
    @Override
        public void run(){
            Scanner scanner = new Scanner(System.in);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                Map<String, String> dicionario = new HashMap<>();
                dicionario.put("A", "Primeira letra do alfabeto");
                dicionario.put("B", "Segunda letra do alfabeto");
                dicionario.put("C", "Terceira letra do alfabeto");
                dicionario.put("JAVA", "Linguagem de programação orientada a objetos");
                dicionario.put("TCP", "Protocolo de comunicação confiável baseado em conexão");
                dicionario.put("UDP", "Protocolo de comunicação não confiável baseado em datagramas");
                dicionario.put("REDE", "Conjunto de computadores interligados para troca de dados");
                dicionario.put("CLIENTE", "Programa ou dispositivo que solicita serviços a um servidor");
                dicionario.put("SERVIDOR", "Computador ou programa que fornece serviços a clientes");
                dicionario.put("ALGORITMO", "Conjunto de passos ordenados para resolver um problema");
                dicionario.put("VARIAVEL", "Espaço na memória que armazena dados");
                dicionario.put("FUNCAO", "Trecho de código que executa uma tarefa específica");
                dicionario.put("LOOP", "Estrutura de repetição em programação");
                dicionario.put("THREAD", "Unidade de execução dentro de um programa");
                dicionario.put("SOCKET", "Interface que permite comunicação entre computadores via rede");
                dicionario.put("HASHMAP", "Estrutura de dados que mapeia chaves a valores");
                dicionario.put("HTML", "Linguagem de marcação para criação de páginas web");
                dicionario.put("CSS", "Linguagem para estilizar páginas web");
                dicionario.put("JAVASCRIPT", "Linguagem de programação usada em páginas web");
                dicionario.put("DATABASE", "Conjunto de dados organizados e armazenados");

                out.println("[SERVIDOR] Bem vindos ao Dicionário de Palavras: \n" );

                do { 
                    out.println("[SERVIDOR] Digite uma palavra para saber seu significado ou 'exit' para sair: ");
                    String palavra = in.readLine();

                    if(palavra == null){
                        break;
                    }

                    if(palavra.equalsIgnoreCase("exit")){
                        out.println("[SERVIDOR] Até a próxima\n");
                        break;
                    }

                    String significado = dicionario.get(palavra.toUpperCase());
                    if(significado != null){
                        out.println("[SERVIDOR] Significado: " + significado + "\n");
                    }
                    else{
                    out.println("[SERVIDOR] Ainda nao possuimos essa palavra \n");
                }
                    
                } while (true);
                
            } catch (Exception e) {
                System.out.println("[Servidor] Cliente desconectou: " + e.getMessage());
            }
            finally{
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
}
