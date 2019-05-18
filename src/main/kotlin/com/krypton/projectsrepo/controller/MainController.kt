package com.krypton.projectsrepo.controller

import com.krypton.projectsrepo.model.Project
import com.krypton.projectsrepo.service.ProjectsServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/projects")
class MainController(val projectsService: ProjectsServiceImpl) {

    /**
     * Get all [Project]'s from database
     *
     * @return list of [Project]'s
     */
    @GetMapping("/all")
    fun getProjects() : List<Project> {
        return projectsService.getProjects()
    }

    /**
     * force update [Project]'s in database
     *
     * @return [HttpStatus]
     */
    @PostMapping("/forceUpdate")
    fun updateProjects() : HttpStatus {
        return try {
            projectsService.getGithubProjects()
            HttpStatus.OK
        } catch (e : Exception) {
            HttpStatus.INTERNAL_SERVER_ERROR
        }
    }
}