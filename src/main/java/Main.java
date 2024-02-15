import service.TobService;
import service.TobServiceImpl;
import service.TobServiceNewImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TobService tobService = new TobServiceNewImpl();
        while (scanner.hasNext()) {
            System.out.println(tobService.tobLogic(scanner.next()));
        }
    }
}