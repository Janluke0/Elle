package progettoelle.registrazionevoti.controllers.professor;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.Flash;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.omnifaces.util.Faces;
import progettoelle.registrazionevoti.domain.Course;
import progettoelle.registrazionevoti.domain.Student;
import progettoelle.registrazionevoti.repositories.DataLayerException;
import progettoelle.registrazionevoti.services.ServiceInjection;
import progettoelle.registrazionevoti.services.managecourse.LoadEnrolledStudentsService;

@ManagedBean
@RequestScoped
public class CourseEnrolledStudents {
    
    private final LoadEnrolledStudentsService service = ServiceInjection.provideLoadEnrolledStudentsService();
    
    private DataModel<Student> enrolledStudents;

    public CourseEnrolledStudents() {
    
    }
    
    @PostConstruct
    public void initialize() {
        Flash flash = Faces.getFlash();
        Course course = (Course)flash.get("course");
        flash.keep("course");
        
        try {
            List<Student> results = service.getEnrolledStudents(course);
            enrolledStudents = new ListDataModel<>(results);
        } catch (DataLayerException ex) {
            
        }
    }
    
    public DataModel<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(DataModel<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

}
