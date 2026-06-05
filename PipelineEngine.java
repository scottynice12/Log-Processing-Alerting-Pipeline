import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PipelineEngine {
    private int totalLogs = 0;
    private int errorCount = 0;
    private final List<String> alertTriggers = new ArrayList<>();

    public void processLogStream() {
        // Reset counters
        totalLogs = 0;
        errorCount = 0;
        alertTriggers.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("server_logs.txt"))) {
            String logLine;

            while ((logLine = reader.readLine()) != null) {
                if (logLine.trim().isEmpty()) continue;
                totalLogs++;

                // Stage 1: Filter and Match
                if (logLine.contains("ERROR")) {
                    errorCount++;
                    // Stage 2: Alert routing execution
                    alertTriggers.add("🚨 CRITICAL ALERT COMPONENT ROUTED: " + logLine);
                }
            }
        } catch (Exception e) {
            System.err.println("Pipeline reading error: " + e.getMessage());
        }
    }

    public void printDashboardMetrics() {
        System.out.println("\n===== PIPELINE TELEMETRY DASHBOARD =====");
        System.out.println("Total Streams Processed : " + totalLogs);
        System.out.println("Total Faults Detected   : " + errorCount);
        
        if (totalLogs > 0) {
            double failureRate = ((double) errorCount / totalLogs) * 100;
            System.out.printf("System Error Rate       : %.1f%%\n", failureRate);
        }
        System.out.println("========================================");

        if (!alertTriggers.isEmpty()) {
            System.out.println("\nActive Incident Logs:");
            for (String alert : alertTriggers) {
                System.out.println(alert);
            }
        }
        System.out.println();
    }
}
