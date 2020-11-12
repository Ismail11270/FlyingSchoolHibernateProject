package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="flights")
public class Flight implements Serializable  {

    @Id
    @Column(name="flight_id")
    private int id;

    @Column(name="DATE", nullable = false)
    private LocalDate date;

    @Column(name="DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name="INSTRUCTOR_ID", foreignKey = @ForeignKey(name = "FK_FLIGHT_INSTRUCTOR_ID"), nullable = false)
    private FlightInstructor instructor;

    @ManyToOne
    @JoinColumn(name="STUDENT_ID", foreignKey = @ForeignKey(name = "FK_FLIGHT_STUDENT_ID"), nullable = false)
    private Student student;

    public Flight() {
    }

    public Flight(int id, LocalDate date, String description) {
        this.id = id;
        this.date = date;
        this.description = description;
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
