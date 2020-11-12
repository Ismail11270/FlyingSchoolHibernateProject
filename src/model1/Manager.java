/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model1;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



/**
 *
 * @author LabHiber
 */
//<joined-subclass extends="model.Employee" lazy="false" name="model.Manager" table="MANAGERS">
//    <key foreign-key="FK_MAN_EMP">
//      <column name="MAN_ID"/>
//    </key>
@Entity 
@PrimaryKeyJoinColumn(name="MAN_ID", foreignKey = @javax.persistence.ForeignKey(name = "FK_MAN_EMP"))
@Table(name="MANAGERS")
public class Manager extends Employee{
    //salary supplement
    private double salSup;

    public Manager() {
    }

    public Manager(String name, String surname, LocalDate birthDate) {
        super(name, surname, birthDate);
    }

    public double getSalSup() {
        return salSup;
    }

    public void setSalSup(double salSup) {
        this.salSup = salSup;
    }
    

 
}
