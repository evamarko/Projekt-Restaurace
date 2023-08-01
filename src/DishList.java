import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DishList {

     List<Dish> dishes = new ArrayList<>();

    public List<Dish> getDishes() {
        return new ArrayList<>(dishes);
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    public void exportToFile(String fileName) throws OrderException {
        try (PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Dish dish : dishes) {
                outputWriter.println(dish.exportToString());
            }
        } catch (IOException e) {
            throw new OrderException("Nepodařilo se nahrát data do souboru: " + fileName);
        }
    }

    public void importFromFile(String fileName) throws OrderException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                dishes.add(Dish.parseDish(line)); }
        } catch (FileNotFoundException e) {
            throw new OrderException("Soubor " + fileName + " nenalezen!");
        }
    }

    public Dish getDishObject(String dishTitle) {
        for (Dish dish : dishes) {
            if (dishTitle == dish.getTitle()) {
                return dish;
            }
        }
        return null;
    }
}
