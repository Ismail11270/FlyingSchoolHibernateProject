package model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="STUDENTS")
@PrimaryKeyJoinColumn(name = "STUDENT_ID", foreignKey = @ForeignKey(name = "FK_STUDENT_PERSON"))
public class Student extends Person {

    @Column(name = "MEDICAL_TESTS")
    String medicalTests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID")
    Course course;

    @OneToMany(mappedBy = "student")
    Set<Flight> flights;

    @ManyToOne
    @JoinColumn(name = "INSTRUCTOR_ID")
    FlightInstructor instructor;

    @ManyToMany(mappedBy = "students")
    Set<TheoryClass> theoryClasses;

    public Student(String medicalTests) {
        this.medicalTests = medicalTests;
    }

    public Student(String firstName, String secondName, Double SSN, Address address, String medicalTests) {
        super(firstName, secondName, SSN, address);
        this.medicalTests = medicalTests;
    }

    public Student() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    public FlightInstructor getInstructor() {
        return instructor;
    }

    public void setInstructor(FlightInstructor instructor) {
        this.instructor = instructor;
    }

    public Set<TheoryClass> getTheoryClasses() {
        return theoryClasses;
    }

    public void setTheoryClasses(Set<TheoryClass> theoryClasses) {
        this.theoryClasses = theoryClasses;
    }

    public String getMedicalTests() {
        return medicalTests;
    }

    public void setMedicalTests(String medicalTests) {
        this.medicalTests = medicalTests;
    }

    public Course getCourses() {
        return course;
    }

    public void setCourses(Course course) {
        this.course = course;
    }

    @PreRemove
    public void preRemove(){
        for(Flight flight : flights){
            flight.setInstructor(null);
        }
    }
}
