package groupId.service;

import groupId.*;
import groupId.db.CRUDService;

import java.util.Scanner;

public class PlayerService {

    private final Scanner scanner;
    private final CRUDService crudService;

    public PlayerService() {
        this.scanner = new Scanner(System.in);
        this.crudService = new CRUDService();
    }

    public void createPlayer() {
        System.out.println("Введите id игрока: ");
        long id = scanner.nextLong();
        System.out.println("Введите playerId игрока: ");
        long playerId = scanner.nextLong();
        System.out.println("Введите имя игрока: ");
        String name = scanner.next();
        if (crudService.createPlayer(new Player(id, playerId, name))) {
            System.out.println(crudService.getPlayer(playerId));
        }
    }

    public void createAchievement() {
        System.out.println("Введите id игрока: ");
        long id = scanner.nextLong();
        System.out.println("Введите playerId игрока: ");
        long playerId = scanner.nextLong();
        System.out.println("Введите название ачивки: ");
        String name = scanner.next();
        if (crudService.createAchievement(new Achievement(id, playerId, name))) {
            System.out.println(crudService.getPlayer(playerId));
        }
    }

    public void createItem() {
        System.out.println("Введите id предмета: ");
        long id = scanner.nextLong();
        System.out.println("Введите playerId игрока: ");
        long playerId = scanner.nextLong();
        System.out.println("Введите название предмета: ");
        String name = scanner.next();
        System.out.println("Введите количество предметов: ");
        int count = scanner.nextInt();
        if (crudService.createItem(new Item(id, playerId, name, count))) {
            System.out.println(crudService.getPlayer(playerId));
        }
    }

    public void createCurrency() {
        System.out.println("Введите id валюты: ");
        long id = scanner.nextLong();
        System.out.println("Введите playerId игрока: ");
        long playerId = scanner.nextLong();
        System.out.println("Введите название валюты: ");
        String name = scanner.next();
        System.out.println("Введите количество валюты: ");
        int count = scanner.nextInt();
        if (crudService.createCurrency(new Currency(id, playerId, name, count))) {
            System.out.println(crudService.getPlayer(playerId));
        }
    }

    public void createEffect() {
        System.out.println("Введите id эффекта: ");
        long id = scanner.nextLong();
        System.out.println("Введите playerId игрока: ");
        long playerId = scanner.nextLong();
        System.out.println("Введите название эффетка: ");
        String name = scanner.next();
        System.out.println("Введите продолжительность эффекта: ");
        int duration = scanner.nextInt();
        if (crudService.createEffect(new Effect(id, playerId, name, duration))) {
            System.out.println(crudService.getPlayer(playerId));
        }
    }

    public void createSpell() {
        System.out.println("Введите id спелла: ");
        long id = scanner.nextLong();
        System.out.println("Введите playerId игрока: ");
        long playerId = scanner.nextLong();
        System.out.println("Введите название спелла: ");
        String name = scanner.next();
        System.out.println("Введите уровень спелла: ");
        int level = scanner.nextInt();
        if (crudService.createSpell(new Spell(id, playerId, name, level))) {
            System.out.println(crudService.getPlayer(playerId));
        }
    }

    public void deletePlayer() {
        System.out.println("Введите playerId игрока: ");
        long playerId = scanner.nextLong();
        crudService.deletePlayer(playerId);
    }

    public void updatePlayer() {
        System.out.println("Введите playerId игрока: ");
        long playerId = scanner.nextLong();
        System.out.println("Введите name игрока: ");
        String name = scanner.next();
        if (crudService.updateNamePlayers(playerId, name)) {
            System.out.println(crudService.getPlayer(playerId));
        }
    }

    public void updateIndicator() {
        System.out.println("Введите playerId игрока: ");
        long playerId = scanner.nextLong();
        System.out.println("Введите hp: ");
        int hp = scanner.nextInt();
        System.out.println("Введите mana: ");
        int mana = scanner.nextInt();
        System.out.println("Введите power: ");
        int power = scanner.nextInt();
        System.out.println("Введите dexterity: ");
        int dexterity = scanner.nextInt();
        System.out.println("Введите intelligence: ");
        int intelligence = scanner.nextInt();
        if (crudService.updateIndicators(new Indicators(playerId, hp, mana, power, dexterity, intelligence))) {
            System.out.println(crudService.getPlayer(playerId));
        }
    }
    public void updateAchievement() {
        System.out.println("Введите id ачивки: ");
        long id = scanner.nextLong();
        System.out.println("Введите name ачивки: ");
        String name = scanner.next();
        crudService.updateAchievements(new Achievement(id, name));
    }

    public void updateItem() {
        System.out.println("Введите id предмета: ");
        long id = scanner.nextLong();
        System.out.println("Введите name предмета: ");
        String name = scanner.next();
        System.out.println("Введите count предмета: ");
        int count = scanner.nextInt();
        crudService.updateItems(new Item(id, name, count));
    }

    public void updateCurrency(){
        System.out.println("Введите id валюты: ");
        long id = scanner.nextLong();
        System.out.println("Введите name предмета: ");
        String name = scanner.next();
        System.out.println("Введите count предмета: ");
        int count = scanner.nextInt();
        crudService.updateCurrencies(new Currency(id, name, count));
    }

    public void updateEffect(){
        System.out.println("Введите id эффекта: ");
        long id = scanner.nextLong();
        System.out.println("Введите name предмета: ");
        String name = scanner.next();
        System.out.println("Введите duration предмета: ");
        int duration = scanner.nextInt();
        crudService.updateEffects(new Effect(id, name, duration));
    }

    public void updateSpell(){
        System.out.println("Введите id spell: ");
        long id = scanner.nextLong();
        System.out.println("Введите name предмета: ");
        String name = scanner.next();
        System.out.println("Введите level предмета: ");
        int level = scanner.nextInt();
        crudService.updateSpells(new Spell(id, name, level));
    }

    public void deleteAchievement(){
        System.out.println("Введите id ачивки:");
        long id = scanner.nextLong();
        if (crudService.deleteAchievement(id)){
            System.out.println("Ачивка успешно удалена!");
        }else {
            System.out.println("Такого id нет в БД");
        }
    }

    public void deleteItem(){
        System.out.println("Введите id предмета:");
        long id = scanner.nextLong();
        if (crudService.deleteItem(id)){
            System.out.println("Ачивка успешно удалена!");
        }else {
            System.out.println("Такого id нет в БД");
        }
    }

    public void deleteCurrency(){
        System.out.println("Введите id валюты:");
        long id = scanner.nextLong();
        if (crudService.deleteCurrency(id)){
            System.out.println("Валюта успешно удалена!");
        }else {
            System.out.println("Такого id нет в БД");
        }
    }

    public void deleteEffect(){
        System.out.println("Введите id эффекта:");
        long id = scanner.nextLong();
        if (crudService.deleteEffect(id)){
            System.out.println("Эффект успешно удалена!");
        }else {
            System.out.println("Такого id нет в БД");
        }
    }

    public void deleteSpell(){
        System.out.println("Введите id:");
        long id = scanner.nextLong();
        if (crudService.deleteSpell(id)){
            System.out.println("Спэл успешно удален!");
        }else {
            System.out.println("Такого id нет в БД");
        }
    }
}
