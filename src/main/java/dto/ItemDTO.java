package dto;

public class ItemDTO {
    private String title;
    private int quantity;
    private float unit_price;

    public ItemDTO(String title, int quantity, float unit_price) {
        this.title = title;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public ItemDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public String toString() {
        return "ItensDTO{" +
                "title='" + title + '\'' +
                ", quantity=" + quantity +
                ", unit_price=" + unit_price +
                '}';
    }
}
