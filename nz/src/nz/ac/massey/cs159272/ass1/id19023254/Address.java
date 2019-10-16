package nz.ac.massey.cs159272.ass1.id19023254;

import java.io.Serializable;
import java.util.Objects;

/**
 * Address class
 */
public class Address implements Serializable {
    private String town;
    private String street;
    private int postCodeNumber;
    private int houseNumber;

    /**
     * Constructor
     */
    public Address() {

    }

    /**
     * Constructor
     *
     * @param town           town
     * @param street         street
     * @param postCodeNumber postcode number
     * @param houseNumber    house number
     */
    public Address(String town, String street, int postCodeNumber, int houseNumber) {
        this.town = town;
        this.street = street;
        this.postCodeNumber = postCodeNumber;
        this.houseNumber = houseNumber;
    }

    /**
     * Getter of town
     * @return town
     */
    public String getTown() {
        return town;
    }

    /**
     * Setter of town
     * @param town town
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * Getter of street
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Setter of street
     * @param street street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Getter of postcode number
     * @return postcode number
     */
    public int getPostCodeNumber() {
        return postCodeNumber;
    }

    /**
     * Setter of postcode number
     * @param postCodeNumber postcode number
     */
    public void setPostCodeNumber(int postCodeNumber) {
        this.postCodeNumber = postCodeNumber;
    }

    /**
     * Getter of house number
     * @return house number
     */
    public int getHouseNumber() {
        return houseNumber;
    }

    /**
     * Setter of house number
     * @param houseNumber house number
     */
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return postCodeNumber == address.postCodeNumber &&
                houseNumber == address.houseNumber &&
                Objects.equals(town, address.town) &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(town, street, postCodeNumber, houseNumber);
    }

    @Override
    public String toString() {
        return town + " " + street + " " + houseNumber + " " + postCodeNumber;
    }
}
