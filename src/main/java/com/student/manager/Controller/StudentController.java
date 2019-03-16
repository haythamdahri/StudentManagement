package com.student.manager.Controller;

import com.student.manager.entity.Result;
import com.student.manager.entity.Student;
import com.student.manager.service.ResultService;
import com.student.manager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class StudentController {

    /**
     * @autor HAYTHAM DAHRI
     * */

    /**
     * @Inject student service from spring container
     * */
    @Autowired
    private StudentService studentService;

    /**
     * @Inject result service from spring container
     * */
    @Autowired
    private ResultService resultService;

    /**
     * @Get login page
     * */
    @GetMapping(value="/login")
    public String login(){
        return "login";
    }


    /**
     * @Get home page
     * */
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String home(@RequestParam(name="student", defaultValue="") String studentSearch, @RequestParam(value="page", defaultValue = "0") int page,Model model){

        Page<Student> studentPage = this.studentService.getPagedSearchStudents(studentSearch, page, 10);

        model.addAttribute("studentPage",studentPage);
        model.addAttribute("page", page);
        model.addAttribute("studentSearch", studentSearch);

        return "index";
    }

    /**
     * @Get student form - insert
     * */
    @GetMapping("/student")
    public String addStudentGet(Model model){

        Student student = new Student();

        model.addAttribute("student", student);

        return "student-form-page";
    }

    /**
     * @Post student form - save Or Update
     * */
    @PostMapping("/student")
    public String addStudentPost(@ModelAttribute("student") Student student, BindingResult bindingResult){

        if( bindingResult.hasErrors() ){
            return "add-student";
        }

        this.studentService.saveStudent(student);

        return "redirect:/?studentSaved";

    }


    /**
     * @Get student form - update
     * */
    @GetMapping("/students/{studentId}")
    public String updateStudentGet(@PathVariable("studentId") int studentId, Model model){

        Student student = this.studentService.getStudent(studentId);
        model.addAttribute("student", student);

        return student != null ? "student-form-page" : "redirect:/?studentNotFound";

    }

    /**
     * @Post delete student
     * */
    @PostMapping("/delete-student")
    public String deleteStudent(@RequestParam("studentId") int studentId){

        if( this.studentService.getStudent(studentId) == null ){
            return "redirect:/?studentNotFound";
        }

        return this.studentService.deleteStudent(studentId) ? "redirect:/?studentDeleted" : "redirect:/";

    }

    /**
     * @Get student results page
     * */
    @GetMapping("/students/{studentId}/results")
    public String results(@RequestParam(name="student", defaultValue="") String studentSearch, Model model, @PathVariable("studentId") int studentId, @RequestParam(value="page", defaultValue="0") int page){

        Student student = this.studentService.getStudent(studentId);
        if( student != null ) {
            List<Result> results = student.getResults();

            PagedListHolder<Result> pagedListHolder = new PagedListHolder<Result>(results);
            pagedListHolder.setPage(page);
            pagedListHolder.setPageSize(10);

            model.addAttribute("pagedListHolder", pagedListHolder);
            model.addAttribute("student", student);
            model.addAttribute("studentSearch", studentSearch);
        }

        return student != null ? "results" : "redirect:/?resultsNotFound";
    }

    /**
     * @Get student result form
     * */
    @GetMapping(value="/students/{studentId}/results/{resultId}")
    public String editResult(@PathVariable("studentId") int studentId, @PathVariable("resultId") int resultId, Model model){

        Student student = this.studentService.getStudent(studentId);
        Result result = null;
        if( student != null ){
            model.addAttribute("students", this.studentService.getStudents());
            result = this.searchResult(resultId, student.getResults());
            model.addAttribute("result", result);
        }

        return student != null && result != null ? "student-result-form.html" : "redirect:/?resultsNotFound";
    }


    /**
     * @Get student result form
     * */
    @GetMapping("/result")
    public String addResultGet(Model model)
    {
        Result result = new Result();
        result.setId(0);

        model.addAttribute("result", result);
        model.addAttribute("students", this.studentService.getStudents());

        return "student-result-form";
    }

    /**
     * @Post student result form - save Or Update
     * */
    @PostMapping("/result")
    public String updateResult(@ModelAttribute("result") Result result, BindingResult bindingResult){

        if( bindingResult.hasErrors() ){
            return "student-result-form";
        }

        Student student = result.getStudent();

        this.resultService.saveResult(result);

        return "redirect:/students/" + student.getId() + "/results/?resultSaved";

    }

    /**
     * @Post delete result
     * */
    @PostMapping("/delete-result")
    public String deleteResult(@RequestParam("resultId") int resultId){

        if( this.resultService.getResult(resultId) == null ){
            return "redirect:/?resultNotFound";
        }

        Result result = this.resultService.getResult(resultId);
        Student student = result.getStudent();

        return this.resultService.deleteResult(resultId) ? "redirect:/students/" + student.getId() + "/results?resultDeleted" : "redirect:/";

    }


    // private method to search and retrieve a result based on the id
    private Result searchResult(int id, List<Result> results){
        for( Result result : results ){
            if( result.getId() == id ){
                return result;
            }
        }
        return null;
    }

}
