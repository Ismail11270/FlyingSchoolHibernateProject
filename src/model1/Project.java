/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author LabHiber
 */
//<class name="model.Project" table="PROJECTS">
@Entity
@Table(name="PROJECTS")
public class Project implements Serializable {
//    <id name="id" type="int">
//      <column name="PROJ_ID"/>
//      <generator class="native"/>
//    </id>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJ_ID")
    private int id;
//    <property name="name" type="java.lang.String" length="30"/>
    @Column(length = 30)
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
     @Column(length = 500)
    private String description;
//    <set cascade="save-update" inverse="true" lazy="true" name="empTeam" table="EMP_PROJ">
//      <key foreign-key="FK_EMP_PROJ">
//        <column name="PROJ_ID"/>
//      </key>
//      <many-to-many class="model.Employee" column="EMP_ID"/>
//    </set>
    @ManyToMany(mappedBy="projects")
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
//    following annotation settings are guaranteed by "mappedBy" annotation
//    @JoinTable(name="EMP_PROJ",
//        joinColumns=@JoinColumn(name="EMP_ID"),
//        inverseJoinColumns=@JoinColumn(name="PROJ_ID"))
//    @JoinTable(foreignKey = @javax.persistence.ForeignKey(name="FK_PROJ_EMP"))
    private Set<Employee> empTeam = new HashSet<>(10);


    public Project() {
    }

    public Project(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public Set<Employee> getEmpTeam() {
        return empTeam;
    }

    public void setEmpTeam(Set<Employee> empTeam) {
        this.empTeam = empTeam;
    }

    public String getPeriod() {
        return startDate+ "-"+ endDate;
    }

    
    @Override
    public String toString() {
        return ("Project: " + getName() + " Realisation period: "
                + getPeriod());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
