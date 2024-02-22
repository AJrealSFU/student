package student.student.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    private String name;
    private String hair;
    private double gpa;
    private double weight;
    private double height;
    private String major;

    public student(){
        
    }

    public student(String name, String hair, double gpa, double weight, double height, String major){
        this.name = name;
        this.hair = hair;
        this.gpa = gpa;
        this.weight = weight;
        this.height = height;
        this.major = major;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public String getHair() {
        return hair;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getMajor() {
        return major;
    }
    
    
}
