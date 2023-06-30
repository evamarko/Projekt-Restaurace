import java.math.BigDecimal;
import java.util.List;

public class Dish {
    private String title;
    private BigDecimal price;
    private int preparationTime;
    private String imageMain;
    private List<String> images;
    private Category category;

    public Dish(String title, BigDecimal price, int preparationTime, String imageMain, List<String> images, Category category) {
        this.title = title;
        this.price = price;
        this.preparationTime = preparationTime;
        this.imageMain = imageMain;
        this.images = images;
        this.category = category;
    }

   /* public Dish(String title, BigDecimal price, int preparationTime, String imageMain, Category category) {
        this(title, price, preparationTime, imageMain, List.of("") , category);
    }*/

    /*public Dish(String title, BigDecimal price, int preparationTime, Category category) {
        this(title, price, preparationTime, "blank" , category);
    }*/

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
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void addImage(String image) {
        this.images.add(image);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String exportToString() {
        return title + "\t" + price + "\t" + preparationTime + "\t" + imageMain + "\t" + images + "\t" + category;
    }

    @Override
    public String toString() {
        return title;
    }
}
