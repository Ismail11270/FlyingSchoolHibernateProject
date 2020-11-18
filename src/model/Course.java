package model;

import goodies.CertTypeConverter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "COURSES")
public class Course {

    @Id
    @Column(name = "COURSE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "CERT_TYPE", nullable = false, unique = true )
    @Convert(converter = CertTypeConverter.class)
    private CertificationType certType;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "course")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Student> students = new HashSet<>();

    public Course() {
    }

    public Course(int id, CertificationType certType, LocalDate startDate, LocalDate endDate, String description) {
        this.id = id;
        this.certType = certType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public Course(CertificationType certType, LocalDate startDate, LocalDate endDate, String description) {
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

    public void setCertType(CertificationType certType) {
        this.certType = certType;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
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
