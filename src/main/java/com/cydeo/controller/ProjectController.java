package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.ResponseWrapper;
import com.cydeo.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping
    public ResponseEntity<ResponseWrapper> getProjects(){

        List<ProjectDTO> projectDTOList = projectService.listAllProjects();
        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved", projectDTOList, HttpStatus.OK));
    }


    @GetMapping("/{code}")
    public ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("code") String code){

        ProjectDTO projectDTO = projectService.getByProjectCode(code);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully retrieved", projectDTO, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody ProjectDTO project){

        projectService.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Project is successfully created",HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateProject(@RequestBody ProjectDTO project){

        projectService.update(project);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully updated", project, HttpStatus.OK));
    }

    @DeleteMapping("/{projectCode}")
    public ResponseEntity<ResponseWrapper> deleteProject(@PathVariable("projectCode") String code){

        projectService.delete(code);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully deleted",HttpStatus.OK));
    }

    @GetMapping("/manager/project-status")
    public ResponseEntity<ResponseWrapper> deleteProject(){

        List<ProjectDTO> projects = projectService.listAllProjectDetails();
        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved", projects, HttpStatus.OK));

    }

    @PutMapping("/manager/complete/{projectCode}")
    public ResponseEntity<ResponseWrapper> managerCompleteProject(@PathVariable("projectCode") String code){

        projectService.complete(code);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully updated",HttpStatus.OK));

    }


}
