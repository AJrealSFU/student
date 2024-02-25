
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

    //Mapping allows us to view all students from the database 
    @GetMapping("/student/view")
    public String getAllstudents(Model model){
        System.out.println("Getting All Student");
        //get all students from database
        List<student> student = studentRepo.findAllByOrderBySid();
        model.addAttribute("stu", student);
        //return to view and show
        return "student/showAll";
    }

    //delete student when called
    @PostMapping("/student/delete")
    public String delStudent(@RequestParam Map<String, String> delStudent){
        System.out.println("del student");
        //case id is not enetered then direct to a page that highlights the error
        if(delStudent.get("sid").isEmpty() == true){
            return"student/faildel";
        }
        int id = Integer.parseInt(delStudent.get("sid"));
        //find student by ID
        student student = studentRepo.findBySid(id);
        //if id is not found it will be null hence result in a invalid selection direct to error page 
        if(student == null){
            return"student/faildel";
        }
        //delete if found id and return a success page
        studentRepo.delete(student);
        return"student/addedStudent";
    }

    //allows webpage to edit when button submits 
    @PostMapping("student/edit")
    public String postMethodName(@RequestParam Map<String, String> edit) {
        //check id eneter if not direct to error page 
        if(edit.get("sid").isEmpty() == true){
            return"student/failedit";
        }
        int id = Integer.parseInt(edit.get("sid"));
        student editing = studentRepo.findBySid(id);
        //cannot find student = NULL and send it to error page 
        if(editing == null){
            return"student/failedit";
        }
        //check each input with string if its blank then return the name set
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
        //if its not empty then set name to the given if it was blank then set will set its orginal data
        if((newName.isEmpty() != true)){
            editing.setName(newName);
        }
        if((newHair.isEmpty() != true)){
            editing.setHair(newHair);
        }
        if((newMajor.isEmpty() != true)){
            editing.setMajor(newMajor);
        }
        //set GPA since all number based input do cannot be blank we only need to care for empty case
        if((newGPA.isEmpty() != true)){
            //set by using parsing for each
            editing.setGpa(Double.parseDouble(newGPA));
        }
        if((newHeight.isEmpty() != true)){
            editing.setHeight(Double.parseDouble(newHeight));
        }
        if((newWeight.isEmpty() != true)){
            editing.setWeight(Double.parseDouble(newWeight));
        }
        //save studnet changes and return to ened of the data base 
        studentRepo.save(editing);
        //direct to sucess page 
        return "student/addedStudent";
    }
    
    
    //add student input in add.html
    @PostMapping("/student/add")
    public String AddStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response){
        System.out.println("add student");
        // get all student arg from input page
        String newName = newStudent.get("name").toLowerCase();    
        String newHair = newStudent.get("hair").toLowerCase();
        String newMajor = newStudent.get("major").toLowerCase();
        String newGPA = newStudent.get("gpa");
        String newWeight = newStudent.get("weight");
        String newHeight = newStudent.get("height");

        //check if any are empty if blank cannot be found if so direct to error page
        if(newName == null || newName.isEmpty() || newName.isBlank() ||
           newHair == null || newHair.isEmpty() || newHair.isBlank() ||
           newMajor == null || newMajor.isEmpty() || newMajor.isBlank() ||
           newGPA == null || newGPA.isEmpty() || newGPA.isBlank() ||
           newWeight == null || newWeight.isEmpty() || newWeight.isBlank() ||
           newHeight == null || newHeight.isEmpty()|| newHeight.isBlank()) {
            return "student/failed";
        }
        //save student after checking
        studentRepo.save(new student(newName, newHair, Double.parseDouble(newGPA), Double.parseDouble(newWeight), Double.parseDouble(newHeight), newMajor));  
        //return to sucess page
        return"student/addedStudent";
    }


    
}
