package groupId.cui;

import groupId.Player;
import groupId.service.PlayerService;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleApplication {

    private Scanner scanner;
    private PlayerService playerService;

    public ConsoleApplication() {
        this.scanner = new Scanner(System.in);
        this.playerService = new PlayerService();
    }

    public void start(){
        while (true) {
            int choice;
            System.out.println();
            System.out.println("Что хотите сделать?");
            System.out.println("1 - Добавить игрока | 2 - Удалить игрока | 3 - Изменить данные игрока | - 4 Остановить программу");
            choice = scanner.nextInt();

            if (choice == 1) {
                playerService.createPlayer();
            } else if (choice == 2) {
                playerService.deletePlayer();
            } else if (choice == 3) {
                System.out.println("""
                        1 - Создать             | 2 - Изменить           | 3 - Удалить         |""");
                int updateChoice = scanner.nextInt();
                update(updateChoice);
            } else if (choice == 4){
                break;
            }
        }
    }

    public void update(int updateChoice){
        if (updateChoice == 1){
            System.out.println("""
                        1 - Создать ачивку      | 2 - Создать предмет    | 3 - Создать валюту  |\s
                        4 - Создать эффект      | 5 - Создать заклинание |                     |""");
            int create = scanner.nextInt();
            createChoice(create);
        } else if (updateChoice == 2) {
            System.out.println("""
                        1 - Изменить игрока     | 2 - Изменить индикатор | 3 - Изменить ачивку |\s
                        4 - Изменить предмет    | 5 - Изменить валюту    | 6 - Изменить эффект |\s
                        7 - Изменить заклинание |                        |                     |""");
            int update = scanner.nextInt();
            updateChoices(update);
        } else if (updateChoice == 3) {
            System.out.println("""
                        1 - Удалить ачивку      | 2 - Удалить предмет    | 3 - Удалить валюту  |\s
                        4 - Удалить эффект      | 5 - Удалить заклинание |                     |""");
            int delete = scanner.nextInt();
            deleteChoice(delete);
        }
    }

    public void updateChoices(int choice){
        if (choice == 1){
            playerService.updatePlayer();
        } else if (choice == 2) {
            playerService.updateIndicator();
        } else if (choice == 3) {
            playerService.updateAchievement();
        } else if (choice == 4) {
            playerService.updateItem();
        } else if (choice == 5) {
            playerService.updateCurrency();
        } else if (choice == 6) {
            playerService.updateEffect();
        } else if (choice == 7) {
            playerService.updateSpell();
        }
    }

    public void deleteChoice(int choice){
        if (choice == 1) {
            playerService.deleteAchievement();
        } else if (choice == 2) {
            playerService.deleteItem();
        } else if (choice == 3) {
            playerService.deleteCurrency();
        } else if (choice == 4) {
            playerService.deleteEffect();
        } else if (choice == 5) {
            playerService.deleteSpell();
        }
    }

    public void createChoice(int choice){
        if (choice == 1) {
            playerService.createAchievement();
        } else if (choice == 2) {
            playerService.createItem();
        } else if (choice == 3) {
            playerService.createCurrency();
        } else if (choice == 4) {
            playerService.createEffect();
        } else if (choice == 5) {
            playerService.createSpell();
        }
    }
}
