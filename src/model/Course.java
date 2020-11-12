package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "COURSES")
public class Course {

    @Id
    @Column(name = "course_id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "CERT_TYPE", nullable = false)
    private CertificationType certType;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "course",orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();

    public enum CertificationType {
        SPORT_PILOT, RECREATIONAL_PILOT, PRIVATE_PILOT, COMMERCIAL_PILOT, FLIGHT_INSTRUCTOR, AIRLINE_TRANSPORT_PILOT
    }

    public Course() {
    }

    public Course(int id, CertificationType certType, LocalDate startDate, LocalDate endDate, String description) {
        this.id = id;
        this.certType = certType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CertificationType getCertType() {
        return certType;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void setCertType(CertificationType certType) {
        this.certType = certType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", certType='" + certType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                '}';
    }
}
