import java.io.IOException;
import java.util.Scanner;

public class KillAndroidStudio {

    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("ps aux");

        Scanner input = new Scanner(process.getInputStream());
        boolean killed = false;
        while (input.hasNext()) {
            String line = input.nextLine();
            if (line.contains("-Didea.platform.prefix=AndroidStudio")) {
                String pid = line.split("\\s+")[1];
                if (pid.matches("\\d+")) {
                    Integer processId = Integer.parseInt(pid);
                    runtime.exec("kill " + processId);
                    System.out.println("Android Studio killed successfully!");
                    killed = true;
                    break;
                }
            }
        }
        if (!killed) {
            System.out.println("Android studio not running!");
        }
    }

}
