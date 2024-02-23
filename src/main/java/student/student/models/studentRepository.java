package student.student.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface studentRepository extends JpaRepository<student,Integer>{
    //res function to find students in database
    List<student> findByGpa(double gpa);
    List<student> findByWeight(double weight);
    List<student> findByMajor(String major);
    List<student> findByName(String name);
    List<student> findByHair(String hair);
    List<student> findByHeight(double height);
    student findBySid(int sid);
    void deleteBySid(int sid);
}
