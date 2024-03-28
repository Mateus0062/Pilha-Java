import java.util.Scanner;

public class codigo {
    static int proximoPID = 0;
    int PID;
    int memoria;
    int tempo;
    int totTempo;
    int totMemoria;

    public codigo() {

    }
    
    public codigo(int memoria, int tempo) {
        this.PID = proximoPID++;
        this.memoria = memoria;
        this.tempo = tempo;
        this.totTempo = 0;
        this.totMemoria = 0;
    }

    codigo[] codigos;
    int topo;

    public codigo(int quantidade) {
        codigos = new codigo[quantidade];
        topo = 0;
        totMemoria = 0;
        totTempo = 0;
    }

    public void push(int mem, int t) {
        if (topo >= codigos.length) {
            System.out.println("Pilha cheia");
            return;
        }

        codigos[topo] = new codigo(mem, t);
        topo++;

        totMemoria += mem;
        totTempo += t;
    }

    public void pop() {
        if (topo <= 0) {
            System.out.println("Pilha vazia");
            return;
        }

        topo--;
        codigo codigoremovido = codigos[topo];
        System.out.println();
        System.out.println("Processo removido: PID - " + codigoremovido.PID + ", Memória - " + codigoremovido.memoria + ", Tempo - " + codigoremovido.tempo);
    }

    public void mostraPilha() {
        if (topo == 0) {
            System.out.println("Pilha vazia");
            return;
        }

        System.out.println("Pilha:");
        for (int i = 0; i < topo; i++) {
            System.out.println(codigos[i].PID + " " + codigos[i].memoria + " " + codigos[i].tempo); ;
        }
        System.out.println();
    }

    public void executaProcesso() throws InterruptedException {
        if (topo == 0) {
            System.out.println("Pilha vazia. Não há processos para executar.");
            return;
        }

        System.out.println("Executando processos:");

        for (int i = topo - 1; i >= 0; i--) {
            codigo processo = codigos[i];
            System.out.println();
            System.out.println("Iniciando execução do processo PID " + processo.PID + " com tempo de execução " + processo.tempo + " segundos.");

            for (int j = 0; j < processo.tempo; j++) {
                System.out.print("#"); 
                Thread.sleep(1000); 
            }
            System.out.println("\nProcesso PID " + processo.PID + " concluído.");
            System.out.println();

            pop();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Quantos processos deseja inserir?");
        int quantidade = sc.nextInt();

        codigo pilha = new codigo(quantidade);

        for (int i = 0; i < quantidade; i++) {
            System.out.println("Insira a quantidade de memória para o processo " + (i + 1) + ":");
            int mem = sc.nextInt();

            System.out.println("Insira o tempo de execução para o processo " + (i + 1) + ":");
            int t = sc.nextInt();

            if (t < 30 || t > 90) {
                System.out.println("Insira um intervalo de tempo válido entre 30 e 90 segundos");
                return;
            }

            pilha.push(mem, t);
        }

        pilha.mostraPilha();
        pilha.executaProcesso();
        pilha.mostraPilha();

        System.out.println();
        
        System.out.println("Total tempo de execução: " + pilha.totTempo + " segundos");	
        System.out.println("Total memória utilizada: " + pilha.totMemoria + " MB");

        sc.close();
    }
}