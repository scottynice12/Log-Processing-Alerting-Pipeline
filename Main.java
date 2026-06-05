import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PipelineEngine engine = new PipelineEngine();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("==================================================");
        System.out.println(" TELEMETRY STREAM PIPELINE TERMINAL INITIALIZED ");
        System.out.println("==================================================");

        while (true) {
            System.out.println("Select Pipeline Action:");
            System.out.println("1) Execute pipeline parse run over 'server_logs.txt'");
            System.out.println("2) Close pipeline engine framework");
            System.out.print("Action > ");
            
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                engine.processLogStream();
                engine.printDashboardMetrics();
            } else if (choice.equals("2") || choice.equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println("Invalid selection. Try again.\n");
            }
        }
        
        scanner.close();
        System.out.println("Pipeline shutdown complete.");
    }
}
