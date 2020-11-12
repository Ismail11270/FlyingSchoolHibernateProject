package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class TheoryClass implements Serializable {

    @Id
    @Column (name = "CLASS_ID")
    private int id;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="HOURS")
    private int hours;

    @Column(name="GRADE")
    private int grade;

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "STUDENTS_CLASSES",
            joinColumns = @JoinColumn(name = "CLASS_ID"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_ID"),
            foreignKey = @javax.persistence.ForeignKey(name = "FK_CLASS_STUDENT"),
            inverseForeignKey = @javax.persistence.ForeignKey(name = "FK_STUDENT_CLASS"))
    private Set<Student> students;

    public TheoryClass(int id, String name, int hours, int grade) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.grade = grade;
    }

    public TheoryClass() {
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "TheoryClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", grade=" + grade +
                '}';
    }

}
