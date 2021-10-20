package dto;

public class PayerDTO {

    private String email;

    private String firstName;

    private String lastName;

    private IdentificationDTO identification = new IdentificationDTO();

    private AddressDTO address = new AddressDTO();

    public PayerDTO() {
    }

    public PayerDTO(String email, String firstName, String lastName, IdentificationDTO identification, AddressDTO address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identification = identification;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public IdentificationDTO getIdentification() {
        return identification;
    }

    public void setIdentification(IdentificationDTO identification) {
        this.identification = identification;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

}
