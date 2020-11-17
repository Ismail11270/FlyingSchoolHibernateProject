package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "THEORY_CLASSES")
public class TheoryClass implements Serializable {

    @Id
    @Column(name = "CLASS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "HOURS")
    private Integer hours;

    @Column(name = "GRADE")
    private Integer grade;

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "STUDENTS_CLASSES",
            joinColumns = @JoinColumn(name = "CLASS_ID"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_ID"),
            foreignKey = @javax.persistence.ForeignKey(name = "FK_CLASS_STUDENT"),
            inverseForeignKey = @javax.persistence.ForeignKey(name = "FK_STUDENT_CLASS"))
    private Set<Student> students = new HashSet<>();

    public TheoryClass(int id, String name, int hours, int grade) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.grade = grade;
    }

    public TheoryClass(String name, int hours, int grade) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
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
