/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import javax.persistence.*;

import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;


/**
 * @author LabHiber
 */
@Entity
@PrimaryKeyJoinColumn(name = "EMP_ID", foreignKey = @ForeignKey(name = "FK_EMP_PER"))
@Table(name = "EMPLOYEES")
public class Employee extends Person implements Serializable {

    @ManyToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "ADR_ID", foreignKey = @ForeignKey(name = "FK_EMP_ADR"))
    private Address address;
    private String job;

    @ManyToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "DEPT_ID", foreignKey = @ForeignKey(name = "FK_EMP_DEP"))
    private Department department;

    @OneToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "EMP_ID", foreignKey = @ForeignKey(name = "FK_EMP_CHILDREN"))
    private Set<Child> children = new HashSet<>(3);

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "EMP_PROJ",
            joinColumns = @JoinColumn(name = "EMP_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROJ_ID"),
            foreignKey = @javax.persistence.ForeignKey(name = "FK_EMP_PROJ"),
            inverseForeignKey = @javax.persistence.ForeignKey(name = "FK_PROJ_EMP"))
    private Set<Project> projects = new HashSet<>(3);

    public Employee() {
    }

    public Employee(String imie, String nazwisko, LocalDate dataUr) {
        super(imie, nazwisko, dataUr);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Child> getChildren() {
        return children;
    }

    public void setChildren(Set<Child> children) {
        this.children = children;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return (super.getName() + " "
                + super.getSurname() + "  " + super.getBirthDayS()
                + " stanowisko: " + job);
    }
}
