import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Dish {
    private String title;
    private BigDecimal price;
    private int preparationTime;
    private String imageMain;
    private List<String> images;
    private Category category;
    private Boolean isInCookBook = false;
    private Boolean isOnMenu = false;

    public Dish(String title, BigDecimal price, int preparationTime, String imageMain, Category category) {
        this.title = title;
        this.price = price;
        this.preparationTime = preparationTime;
        this.imageMain = imageMain;
        this.category = category;
    }

    public Dish(String title, BigDecimal price, int preparationTime, Category category) {
        this(title, price, preparationTime, "blank" , category);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getImageMain() {
        return imageMain;
    }

    public void setImageMain(String imageMain) {
        this.imageMain = imageMain;
    }

    public List<String> getImages() {
        return new ArrayList<>(images);
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void addImage(String image) {
        this.images.add(image);
    }

    public void removeImage(String image) {
        this.images.remove(image);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getInCookBook() {
        return isInCookBook;
    }

    public void setInCookBook(Boolean inCookBook) {
        isInCookBook = inCookBook;
    }

    public Boolean getOnMenu() {
        return isOnMenu;
    }

    public void setOnMenu(Boolean onMenu) {
        isOnMenu = onMenu;
    }

    public static Dish parseDish(String data) throws OrderException {
        String [] items;
        try {
            items = data.split("\t");
            String title = items[0];
            BigDecimal price = new BigDecimal(items[1]);
            int preparationTime = Integer.parseInt(items[2]);
            String imageMain = items[3];
            Category category = Category.valueOf(items[4]);
            return new Dish(title, price, preparationTime, imageMain, category);
        } catch (IllegalArgumentException e) {
            throw new OrderException("Chybně zadaná data, nepodařilo se načíst data ze souboru.");
        }
    }

    public String exportToString() {
        return title + "\t" + price + "\t" + preparationTime + "\t" + imageMain + "\t" + category;
    }

    @Override
    public String toString() {
        return title;
    }
}
