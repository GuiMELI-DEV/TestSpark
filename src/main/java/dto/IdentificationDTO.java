package dto;

public class IdentificationDTO {
    private String type;
    private String number;

    public IdentificationDTO(String type, String number) {
        this.type = type;
        this.number = number;
    }

    public IdentificationDTO() { }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "IdentificationDTO{" +
                "type='" + type + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
