/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model1;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;



/**
 *
 * @author LabHiber
 */
@Entity

// <joined-subclass extends="model.Person" lazy="false" name="model.Pracownik" table="EMPLOYEES">
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="PERSONS")
public class Person implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PER_ID")
    private int id;
  
    private String name;

    private String surname;

    private LocalDate birthDate;

    public Person() {
    }
    
    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
      return (getName() + " "+getSurname() + " ur. " + getBirthDayS());
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public String getBirthDayS(){
        return(birthDate.toString());
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public int getAge(){
         if (birthDate != null) {
             return(LocalDate.now().getYear()-birthDate.getYear());
         }
         else {
             return(0);
         } 
    }
    
}
