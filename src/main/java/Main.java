import service.TobService;
import service.TobServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TobService tobService = new TobServiceImpl();
        while (scanner.hasNext()) {
            System.out.println(tobService.tobLogic(scanner.next()));
        }
    }
}