package com.example.muahexanh_resigtration.services.Project;

import com.example.muahexanh_resigtration.dtos.ProjectDTO;
import com.example.muahexanh_resigtration.entities.ProjectEntity;
import com.example.muahexanh_resigtration.exceptions.DataNotFoundException;
import com.example.muahexanh_resigtration.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.text.SimpleDateFormat;

@Service
@AllArgsConstructor
public class ProjectService implements iProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectEntity insertProject(ProjectDTO projectDTO) {
        ProjectEntity newProject = ProjectEntity
                .builder()
                .title(projectDTO.getTitle())
                .description(projectDTO.getDescription())
                .status(projectDTO.getStatus())
                .build();
        return projectRepository.save(newProject);
    }

    @Override
    public ProjectEntity getProjectById(long id) throws Exception {
        Optional<ProjectEntity> optionalProduct = projectRepository.getDetailProject(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        throw new DataNotFoundException("Cannot find project with id =" + id);
    }

    @Override
    public List<ProjectEntity> getAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public ProjectEntity updateProject(long id, ProjectDTO projectDTO) throws Exception {
        Optional<ProjectEntity> optionalProduct = projectRepository.findById(id);
        if (optionalProduct.isPresent()) {
            SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
            java.util.Date parsed;
            java.sql.Date sqlDateStart, sqlDateEnd;

            ProjectEntity existingProject = optionalProduct.get();

            if (projectDTO.getTitle() != null)
                existingProject.setTitle(projectDTO.getTitle());
            if (projectDTO.getDescription() != null)
                existingProject.setDescription(projectDTO.getDescription());
            if (projectDTO.getStatus() != null)
                existingProject.setStatus(projectDTO.getStatus());
            if (projectDTO.getAddress() != null)
                existingProject.setAddress(projectDTO.getAddress());
            if (projectDTO.getMaximumStudents() != 0)
                existingProject.setMaximumStudents(projectDTO.getMaximumStudents());
            if (projectDTO.getDateStart() != null) {
                parsed = format.parse(projectDTO.getDateStart());
                sqlDateStart = new java.sql.Date(parsed.getTime());
                existingProject.setDateStart(sqlDateStart);
            }
            if (projectDTO.getDateEnd() != null) {
                parsed = format.parse(projectDTO.getDateEnd());
                sqlDateEnd = new java.sql.Date(parsed.getTime());
                existingProject.setDateEnd(sqlDateEnd);
            }
            return projectRepository.save(existingProject);
        }
        throw new DataNotFoundException("Cannot find project with id =" + id);
    }

    @Override
    public void deleteProject(long id) {

    }
}
