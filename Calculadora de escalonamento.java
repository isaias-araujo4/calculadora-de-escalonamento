import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Main {

    // Classe interna que representa um Processo do sistema operacional
    public static class Process {
        // Atributos de tempo do processo
        private int arrivalTime, bustTime, statTime, exitTime, turnaroundTime, waitingTime;

        // Construtor para inicializar o processo já com chegada e duração
        public Process(int arrivalTime, int bustTime) {
            this.arrivalTime = arrivalTime;
            this.bustTime = bustTime;
        }

        // Métodos Getters e Setters para acessar e modificar os atributos de forma segura
        public int getArrivalTime() { return arrivalTime; }
        public int getBustTime() { return bustTime; }
        
        public int getStatTime() { return statTime; }
        public void setStatTime(int statTime) { this.statTime = statTime; }

        public int getExitTime() { return exitTime; }
        public void setExitTime(int exitTime) { this.exitTime = exitTime; }

        public int getTurnaroundTime() { return turnaroundTime; }
        public void setTurnaroundTime(int turnaroundTime) { this.turnaroundTime = turnaroundTime; }

        public int getWaitingTime() { return waitingTime; }
        public void setWaitingTime(int waitingTime) { this.waitingTime = waitingTime; }
    }

    // Método responsável por interagir com o usuário e cadastrar os processos em loop
    public static List<Process> lerProcessos() {
        Scanner sc = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();
        int i = 1;

        while (true) {
            System.out.println("\nDigite '1' para adicionar o processo " + i + " ou '2' para encerrar:");
            if (sc.nextInt() == 2) break; // Sai do loop imediatamente se o usuário escolher 2

            System.out.print("Tempo de chegada: ");
            int chegada = sc.nextInt();

            System.out.print("Tempo de execução: ");
            int duracao = sc.nextInt();

            // Cria o objeto utilizando o construtor e o adiciona na lista de processos
            processes.add(new Process(chegada, duracao));
            i++;
        }
        return processes;
    }

    // Método que aplica a lógica matemática do escalonamento FIFO (First-In, First-Out)
    public static void executarFIFO(List<Process> processes) {
        // Ordena os processos do que chegou primeiro para o último com base no tempo de chegada
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));

        int currentTime = 0; // Relógio simulado da CPU
        for (Process p : processes) {
            // Se a CPU estiver ociosa e o processo chegou depois do tempo atual, adianta o relógio
            if (currentTime < p.getArrivalTime()) {
                currentTime = p.getArrivalTime();
            }

            // 1. Tempo de Início (quando o processo pega a CPU)
            p.setStatTime(currentTime);
            
            // 2. Tempo de Término (Início + Tempo de Execução)
            p.setExitTime(p.getStatTime() + p.getBustTime());
            
            // 3. Turnaround (Tempo Total no Sistema = Término - Chegada)
            p.setTurnaroundTime(p.getExitTime() - p.getArrivalTime());
            
            // 4. Tempo de Espera (Turnaround - Execução)
            p.setWaitingTime(p.getTurnaroundTime() - p.getBustTime());

            // O relógio da CPU avança para o momento em que este processo terminou
            currentTime = p.getExitTime();
        }
    }

    // Método responsável por exibir a tabela de resultados individuais e as médias gerais
    public static void exibirResultados(List<Process> processes) {
        System.out.println("\n--- RESULTADOS DO ESCALONAMENTO FIFO ---");
        for (int j = 0; j < processes.size(); j++) {
            Process p = processes.get(j);
            System.out.println("Processo " + (j + 1) +
                               " | Chegada: " + p.getArrivalTime() +
                               " | Execução: " + p.getBustTime() +
                               " | Início: " + p.getStatTime() +
                               " | Término: " + p.getExitTime() +
                               " | Turnaround: " + p.getTurnaroundTime() +
                               " | Espera: " + p.getWaitingTime());
        }

        // Utiliza Java Streams para calcular as médias de forma limpa, direta e otimizada
        double avgWaiting = processes.stream().mapToInt(Process::getWaitingTime).average().orElse(0.0);
        double avgTurnaround = processes.stream().mapToInt(Process::getTurnaroundTime).average().orElse(0.0);

        System.out.println("\n--- MÉDIAS DO ESCALONAMENTO FIFO ---");
        System.out.println("Tempo médio de espera: " + avgWaiting);
        System.out.println("Tempo médio de retorno: " + avgTurnaround);
    }

    // Ponto de entrada principal do programa
    public static void main(String[] args) {
        // 1. Coleta os dados informados pelo usuário
        List<Process> processes = lerProcessos();
        
        // 2. Executa a lógica e mostra os resultados apenas se houver processos cadastrados
        if (!processes.isEmpty()) {
            executarFIFO(processes);
            exibirResultados(processes);
        } else {
            System.out.println("Nenhum processo cadastrado.");
        }
    }
}