# Simulador de Escalonamento de Processos (Java)

Um projeto desenvolvido em **Java** para simular algoritmos de escalonamento de processos em Sistemas Operacionais. O projeto foi arquitetado de forma modular e extensível, contando atualmente com a implementação completa do algoritmo **FIFO (First-In, First-Out)** e preparado para receber novos modelos de escalonamento no futuro.

---

## 🚀 Funcionalidades Atuais

- **Cadastro Dinâmico:** Adicione quantos processos desejar em tempo de execução via terminal.
- **Ordenação Automática:** O sistema organiza os processos com base no **Tempo de Chegada** (*Arrival Time*).
- **Cálculo Completo de Métricas:**
  - Tempo de Início (*Start Time*)
  - Tempo de Término (*Exit Time*)
  - Tempo de Retorno / Turnaround (*Turnaround Time*)
  - Tempo de Espera (*Waiting Time*)
- **Estatísticas Médias:** Calcula o tempo médio de espera e o tempo médio de retorno do sistema utilizando a API de *Streams* do Java.

---

## 🛠️ Tecnologias Utilizadas

- **Java** (Estruturas de dados, Collections Framework, Streams API)
- **Paradigma Orientado a Objetos**

---

## ⚙️ Como Executar o Projeto

Certifique-se de ter o **Java Development Kit (JDK)** instalado em sua máquina.

1. Clone o repositório ou baixe o arquivo do projeto (`Main.java`).
2. Abra o terminal na pasta onde o arquivo está salvo.
3. Compile o programa:
   ```bash
   javac Main.java
   ```
4. Execute o programa:
   ```bash
   java Main
   ```

---

## 💻 Exemplo de Uso (FIFO)

```text
Digite '1' para adicionar o processo 1 ou '2' para encerrar:
1
Tempo de chegada: 0
Tempo de execução: 5

Digite '1' para adicionar o processo 2 ou '2' para encerrar:
1
Tempo de chegada: 2
Tempo de execução: 3

Digite '1' para adicionar o processo 3 ou '2' para encerrar:
2

--- RESULTADOS DO ESCALONAMENTO FIFO ---
Processo 1 | Chegada: 0 | Execução: 5 | Início: 0 | Término: 5 | Turnaround: 5 | Espera: 0
Processo 2 | Chegada: 2 | Execução: 3 | Início: 5 | Término: 8 | Turnaround: 6 | Espera: 3

--- MÉDIAS DO ESCALONAMENTO FIFO ---
Tempo médio de espera: 1.5
Tempo médio de retorno: 5.5
```

---

## 📈 Fórmulas Utilizadas

- **Turnaround (Tempo de Retorno):** $Tempo\ de\ Término - Tempo\ de\ Chegada$
- **Tempo de Espera:** $Turnaround - Tempo\ de\ Execução$

---

## 🔮 Futuras Atualizações e Modelos

- [x] **FIFO** (*First-In, First-Out*)
- [ ] **SJF** (*Shortest Job First* - Não preemptivo e Preemptivo)
- [ ] **Round Robin** (Com Quantum configurável)
- [ ] **Prioridade** (Com e sem preempção)
- [ ] Geração de Gráfico de Gantt visual no terminal.