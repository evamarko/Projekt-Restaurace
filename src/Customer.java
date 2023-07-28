public class Customer {
    private String notes;

    public Customer(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "notes='" + notes + '\'' +
                '}';
    }
}
