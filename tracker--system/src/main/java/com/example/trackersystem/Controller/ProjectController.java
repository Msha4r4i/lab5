package com.example.trackersystem.Controller;


import com.example.trackersystem.Api.Api;
import com.example.trackersystem.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/project")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();




    @PutMapping("/add")
    public Api addProject(@RequestBody Project project){
        projects.add(project);
        return new Api("success");
    }

    @GetMapping("/get")
    public ArrayList<Project> getProjects(){
        return projects;
    }

    @PutMapping("/updet/{id}")
    public Api updateProject(@PathVariable String id ,@RequestBody Project project){
       for (Project p : projects){
           if(p.getId().equals(id)){
              p.setId(project.getId());
              p.setTitle(project.getTitle());
              p.setDescription(project.getDescription());
              p.setStatus(project.isStatus());
              return new Api("success");

           }
       }
       return new Api("fail");

    }

    @DeleteMapping("/delet/{id}")
    public Api deleteProject(@PathVariable String id){
        for (Project p : projects){
            if(p.getId().equals(id)){
                projects.remove(p);
                return new Api("success");
            }
        }
        return new Api("fail");
    }

    @PutMapping("/change/{id}")
    public Api putProject(@PathVariable String id ,@RequestBody Project project){
        for (Project p : projects){
            if(p.getId().equals(id)){
                p.setStatus(!project.isStatus());
                return new Api("success");
            }
        }
        return new Api("fail");
    }
    @GetMapping("/search/{title}")
    public ArrayList<Project> getProjectByTitle(@PathVariable String title){
        ArrayList<Project> projectsFounded = new ArrayList<>();
        for (Project p : projects){
            if(p.getTitle().equals(title)){
                projectsFounded.add(p);
                return projectsFounded;
            }
        }
        return projectsFounded;
    }

    @GetMapping("/search-by-companyName/{companyName}")
    public ArrayList<Project> getProjectByCompanyName(@PathVariable String companyName){
        ArrayList<Project> projectsFounded = new ArrayList<>();
        for (Project p : projects){
            if(p.getCompanyName().equals(companyName)){
                projectsFounded.add(p);
                return projectsFounded;
            }
        }
        return projectsFounded;
    }


}


