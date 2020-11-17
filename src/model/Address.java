package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ADDRESSES")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ADDRESS_ID")
    private Integer id;

    @Column(name="COUNTRY", length = 20)
    private String country;

    @Column(name="CITY", length = 20)
    private String city;

    @Column(name="POSTAL_CODE", length = 10)
    private String postalCode;

    @Column(name="STREET", length = 50)
    private String street;

    @OneToMany(mappedBy = "address")
    private Set<Person> persons = new HashSet<>();

    public Address() {
    }

    public Address(Integer id, String country, String city, String postalCode, String street) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
    }

    public Address(String country, String city, String postalCode, String street) {
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", street='" + street + '\'' +
                ", persons=" + persons +
                '}';
    }
}
