import java.util.Random;

/**
 * @author Shanmuga Ganesh
 */
class Process {
    Integer pid, msgsInChannel, token = 1;

    public Process(Integer pid) {
        this.pid = pid;
        this.msgsInChannel = 0;
    }

    /**
     * used to send a single token
     */
    public void sendMessage() {
        this.msgsInChannel += token;
    }

    /**
     * used to receive a single token
     */
    public void receiveMessage() {
        this.msgsInChannel -= token;
    }

    public Integer getPid() {
        return pid;
    }

    public Integer getMsgsInChannel() {
        return msgsInChannel;
    }
}

public class RandomFlooding {
    /**
     * main method
     * 
     * @param args
     */
    public static void main(String[] args) {

        Random random = new Random();
        // we can change this variables for different outcome
        Integer numberOfProcesses = 5, numberOfComputation = 50;
        Boolean initiator = true;

        // initializing the processes
        Process[] processes = new Process[numberOfProcesses];
        for (int i = 0; i < numberOfProcesses; i++) {
            processes[i] = new Process(i);
        }

        // executing the computation till numberOfComputation
        for (int i = 0; i < numberOfComputation - 1;) {
            // to execute the initiator process 0
            if (initiator) {
                processMessage(random, numberOfProcesses, processes, 0);
                initiator = false;
                continue;
            }
            // execution of followers
            int executingRandomProcess = random.nextInt(numberOfProcesses);
            if (processes[executingRandomProcess].getMsgsInChannel() > 0) {
                processes[executingRandomProcess].receiveMessage();
                processMessage(random, numberOfProcesses, processes, executingRandomProcess);
                i++;
            }
        }

        // executing the rest of the computation which has message in its channel
        int used = 0;
        for (int i = 0; i < numberOfProcesses; i++) {
            used += processes[i].getMsgsInChannel();
        }
        while (used >= 0) {
            int randomProcess = random.nextInt(numberOfProcesses);
            if (processes[randomProcess].getMsgsInChannel() != 0) {
                processes[randomProcess].receiveMessage();
                displayProcess(numberOfProcesses, processes, 0, randomProcess);
                used--;
            }
        }
    }

    /**
     * To perform sending the messages to neighbor processes
     * 
     * @param random
     * @param numberOfProcesses
     * @param processes
     * @param executingRandomProcess
     */
    private static void processMessage(Random random, Integer numberOfProcesses, Process[] processes,
            Integer executingRandomProcess) {

        Integer messages = random.nextInt(1, numberOfProcesses);
        Integer[] used = new Integer[numberOfProcesses];
        for (int j = 0; j < messages;) {
            int randomProcess = random.nextInt(numberOfProcesses);
            if (randomProcess != executingRandomProcess && used[randomProcess] == null) {
                used[randomProcess] = 1;
                processes[randomProcess].sendMessage();
                j++;
            }
        }
        displayProcess(numberOfProcesses, processes, messages, executingRandomProcess);
    }

    /**
     * To display the info of the all the processes in the network
     * 
     * @param numberOfProcesses
     * @param processes
     * @param messages
     * @param executingRandomProcess
     */
    private static void displayProcess(int numberOfProcesses, Process[] processes, Integer messages,
            Integer executingRandomProcess) {

        System.out.println("Process ID executing: " + executingRandomProcess + " Messages Sending: " + messages);
        for (int j = 0; j < numberOfProcesses; j++) {
            System.out.println(
                    "PID " + processes[j].getPid() + " # Msgs In Channel: " + processes[j].getMsgsInChannel());
        }
        System.out.println();
    }
}
