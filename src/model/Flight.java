package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "flights")
public class Flight implements Serializable {

    @Id
    @Column(name = "FLIGHT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "DESCRIPTION", length = 250)
    private String description;

    @ManyToOne
    @JoinColumn(name = "INSTRUCTOR_ID", foreignKey = @ForeignKey(name = "FK_FLIGHT_INSTRUCTOR"))
    private FlightInstructor instructor;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", foreignKey = @ForeignKey(name = "FK_FLIGHT_STUDENT"))
    private Student student;

    public Flight() {
    }

    public Flight(int id, LocalDate date, String description) {
        this.id = id;
        this.date = date;
        this.description = description;
    }

    public Flight(int id, LocalDate date, String description, FlightInstructor instructor, Student student) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.instructor = instructor;
        this.student = student;
    }

    public Flight(LocalDate date, String description, FlightInstructor instructor, Student student) {
        this.date = date;
        this.description = description;
        this.instructor = instructor;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FlightInstructor getInstructor() {
        return instructor;
    }

    public void setInstructor(FlightInstructor instructor) {
        this.instructor = instructor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
