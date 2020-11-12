/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model1;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author LabHiber
 */
//<class name="model.Departement" table="DEPARTEMENTS">
@Entity
@Table(name="DEPARTEMENTS")
public class Department implements Serializable{
//    <id name="id" type="int">
//      <column name="DEPT_ID"/>
//      <generator class="native"/>
//    </id>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPT_ID")
    private int id;
    private String name;
    
//    <set lazy="true" name="employees" inverse="true">
//      <key >
//        <column name="DEPT_ID"/>
//      </key>
//      <one-to-many class="model.Employee"/>
//    </set>
    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new HashSet<>(10);
    
//   <many-to-one class="model.Manager" column="MAN_ID" 
//    foreign-key="FK_DEPT_MAN" name="manager" unique="true"/>

    @Basic(optional = true, fetch = FetchType.EAGER)
    @OneToOne
    @JoinColumn(name="MAN_ID", foreignKey = @javax.persistence.ForeignKey(name="FK_DEPT_MAN"))
    private Manager manager;
    
    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", employees=" + employees.size() + ", manager=" + manager.getName() + '}';
    }
    
    
}
