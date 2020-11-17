package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "STUDENTS")
@PrimaryKeyJoinColumn(name = "STUDENT_ID", foreignKey = @ForeignKey(name = "FK_STUDENT_PERSON"))
public class Student extends Person {

    @Column(name = "MEDICAL_TESTS")
    private String medicalTests;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "COURSE_ID", foreignKey = @ForeignKey(name = "FK_STUDENT_COURSE"))
    private Course course;

    @OneToMany(mappedBy = "student")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Flight> flights = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "INSTRUCTOR_ID",  foreignKey = @ForeignKey(name = "FK_STUDENT_INSTRUCTOR"))
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private FlightInstructor instructor;

    @ManyToMany(mappedBy = "students")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<TheoryClass> theoryClasses = new HashSet<>();

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
    public void preRemove() {
        for (Flight flight : flights) {
            flight.setStudent(null);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "medicalTests='" + medicalTests + '\'' +
                ", course=" + course +
                ", instructor=" + instructor +
                '}';
    }
}
