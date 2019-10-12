package edu.mum.cs.cs425.eRegistrar.controller;

import edu.mum.cs.cs425.eRegistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import edu.mum.cs.cs425.eRegistrar.model.Student;

import javax.validation.Valid;

@Controller
public class StudentController {

//    @Autowired
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studnetService) {
        this.studentService = studnetService;
    }

    @GetMapping(value = {"/eregister/student/list"})
    public ModelAndView listStudents(@RequestParam(defaultValue = "0") int pageno) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("students", studentService.getAllStudentsPaged(pageno));
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("student/list");
        return modelAndView;
    }

    @GetMapping(value = {"/eregister/student/new"})
    public String displayNewStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/new";
    }

    @PostMapping(value = {"/eregister/student/new"})
    public String addNewStudent(@Valid @ModelAttribute("student") Student student,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "student/new";
        }
        student = studentService.saveStudent(student);
        return "redirect:/eregister/student/list";
    }

    @GetMapping(value = {"/eregister/student/edit/{studentId}"})
    public String editStudent(@PathVariable Integer studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            model.addAttribute("student", student);
            return "student/edit";
        }
        return "student/list";
    }

    @PostMapping(value = {"/eregister/student/edit"})
    public String updateStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "student/edit";
        }
        student = studentService.saveStudent(student);
        return "redirect:/eregister/student/list";
    }

    @GetMapping(value = {"/eregister/student/delete/{sudentId}"})
    public String deleteStudent(@PathVariable Integer studentId, Model model) {
        studentService.deleteStudentById(studentId);
        return "redirect:/eregister/student/list";
    }

}
