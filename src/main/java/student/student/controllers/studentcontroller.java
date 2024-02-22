
package student.student.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import student.student.models.student;
import student.student.models.studentRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;


@Controller
public class studentcontroller {

    @Autowired
    private studentRepository studentRepo;

    @GetMapping("/student/view")
    public String getAllstudents(Model model){
        System.out.println("Getting All Student");
        List<student> student = studentRepo.findAll();
        model.addAttribute("stu", student);
        return "student/showAll";
    }

    @PostMapping("/student/delete")
    public String delStudent(@RequestParam Map<String, String> delStudent){
        System.out.println("del student");
        if(delStudent.get("sid").isEmpty() == true){
            return"student/faildel";
        }
        int id = Integer.parseInt(delStudent.get("sid"));
        student student = studentRepo.findBySid(id);
        if(student == null){
            return"student/faildel";
        }
        studentRepo.delete(student);
        return"student/addedStudent";
    }

    @PostMapping("student/edit")
    public String postMethodName(@RequestParam Map<String, String> edit) {
        if(edit.get("sid").isEmpty() == true){
            return"student/failedit";
        }
        int id = Integer.parseInt(edit.get("sid"));
        student editing = studentRepo.findBySid(id);
        if(editing == null){
            return"student/failedit";
        }
        String newName = edit.get("name");   
        if(newName.isBlank() == true){
            newName = editing.getName();
        } 
        String newHair = edit.get("hair");
        if(newHair.isBlank() == true){
            newHair = editing.getHair();
        } 
        String newMajor = edit.get("major");
        if(newMajor.isBlank() == true){
            newMajor = editing.getMajor();
        } 
        String newGPA = edit.get("gpa");
        String newWeight = edit.get("weight");
        String newHeight = edit.get("height");
        if((newName.isEmpty() != true)){
            editing.setName(newName);
        }
        if((newHair.isEmpty() != true)){
            editing.setHair(newHair);
        }
        if((newMajor.isEmpty() != true)){
            editing.setMajor(newMajor);
        }
        if((newGPA.isEmpty() != true)){
            editing.setGpa(Double.parseDouble(newGPA));
        }
        if((newHeight.isEmpty() != true)){
            editing.setHeight(Double.parseDouble(newHeight));
        }
        if((newWeight.isEmpty() != true)){
            editing.setWeight(Double.parseDouble(newWeight));
        }
        studentRepo.save(editing);
        return "student/addedstudent";
    }
    
    

    @PostMapping("/student/add")
    public String AddStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response){
        System.out.println("add student");
        String newName = newStudent.get("name").toLowerCase();    
        String newHair = newStudent.get("hair").toLowerCase();
        String newMajor = newStudent.get("major").toLowerCase();
        String newGPA = newStudent.get("gpa");
        String newWeight = newStudent.get("weight");
        String newHeight = newStudent.get("height");

        if(newName == null || newName.isEmpty() ||
           newHair == null || newHair.isEmpty() ||
           newMajor == null || newMajor.isEmpty() ||
           newGPA == null || newGPA.isEmpty() ||
           newWeight == null || newWeight.isEmpty() ||
           newHeight == null || newHeight.isEmpty()) {
            return "student/failed";
        }

        studentRepo.save(new student(newName, newHair, Double.parseDouble(newGPA), Double.parseDouble(newWeight), Double.parseDouble(newHeight), newMajor));  
        return"student/addedStudent";
    }


    
}
