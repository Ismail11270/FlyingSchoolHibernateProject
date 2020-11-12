package model;

import goodies.BooleanConverter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="FLIGHT_INSTRUCTORS")
@OnDelete(action = OnDeleteAction.CASCADE)
@PrimaryKeyJoinColumn(name = "INSTRUCTOR_ID", foreignKey = @ForeignKey(name = "FK_INSTRUCTOR_PERSON"))
public class FlightInstructor extends Person{

    @Column(name="LICENCE_NO", nullable = false)
    private Long licenceNo;

    @Column(name="VALID", nullable = false)
    @Convert(converter = BooleanConverter.class)
    private Boolean valid;

    @OneToMany(mappedBy = "instructor")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.PERSIST})
    private Set<Flight> flights = new HashSet<>();

    @OneToMany(mappedBy = "instructor")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.PERSIST})
    private Set<Student> students = new HashSet<>();

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public FlightInstructor(String firstName, String secondName, Double SSN, Long licenceNo, Boolean valid, Address address) {
        super(firstName, secondName, SSN, address);
        this.licenceNo = licenceNo;
        this.valid = valid;
    }

    public FlightInstructor() {
    }

    public Long getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(Long licenceNo) {
        this.licenceNo = licenceNo;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @PreRemove
    public void preRemove(){
        for(Flight flight : flights){
            flight.setInstructor(null);
        }
    }

    @Override
    public String toString() {
        return "FlightInstructor{" +
                "licenceNo=" + licenceNo +
                ", valid=" + valid +
                '}';
    }
}
