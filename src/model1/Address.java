/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model1;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 *
 * @author LabHiber
 */

@Entity 
@Table(name="ADDRESSES")
public class Address implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADR_ID")
    private int id;
    private String postalCode;
    private String city;
    private String street;
    private String houseNo;
    private String apartNo;

    public Address() {
    }

    public Address(String postalCode, String city, String street, String houseNo, String apartNo) {
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.houseNo = houseNo;
        this.apartNo = apartNo;
    }

    @Override
    public String toString() {
        return (" "+postalCode + " " + city + " ul." + street + " " + houseNo + "/" + apartNo+" ");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getApartNo() {
        return apartNo;
    }

    public void setApartNo(String apartNo) {
        this.apartNo = apartNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
