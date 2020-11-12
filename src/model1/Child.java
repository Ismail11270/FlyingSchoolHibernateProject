/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model1;


import goodies.BooleanConverter;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author LabHiber
 */
// <joined-subclass extends="model.Person" lazy="false" name="model.Child" table="CHILDREN">
@Entity
//   <key foreign-key="FK_CHILD_PERSON">
//      <column name="CHILD_ID"/>
//    </key>
@PrimaryKeyJoinColumn(name="CHILD_ID", foreignKey = @javax.persistence.ForeignKey(name = "FK_CHILD_PERSON"))
@Table(name="CHILDREN")
public class Child extends Person implements Serializable{

    @Convert(converter = BooleanConverter.class)
    private Boolean learning;
    
    public Child() {
        learning = false;
    }

    public Child(String name, String surname) {
        super(name, surname);
        learning = false;
    }

    public Child(String name, String surname, LocalDate birthDate) {
        super(name, surname, birthDate);
        learning = false;
    }

    public boolean getLearning() {
        return learning;
    }

    public void setLearning(Boolean learning) {
        this.learning = learning;
    }
    
}
