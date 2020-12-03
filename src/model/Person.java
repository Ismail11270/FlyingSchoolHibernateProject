package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PERSONS")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSON_ID")
    private int id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "SECOND_NAME", nullable = false)
    private String secondName;

    @Column(name = "SSN", unique = true)
    private Double SSN;

    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID", foreignKey = @ForeignKey(name = "FK_PERSON_ADDRESS_ID"))
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Address address;

    public Person() {
    }

    public Person(String firstName, String secondName, Double SSN, Address address) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.SSN = SSN;
        this.address = address;
    }

    public Person(int id, String firstName, String secondName, Double SSN, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.SSN = SSN;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Double getSSN() {
        return SSN;
    }

    public void setSSN(Double SSN) {
        this.SSN = SSN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person1{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", SSN=" + SSN +
                ", address=" + address +
                '}';
    }
}
