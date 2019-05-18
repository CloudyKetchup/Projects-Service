package com.krypton.projectsrepo.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.krypton.projectsrepo.model.Project
import com.krypton.projectsrepo.repository.ProjectsRepository
import com.mashape.unirest.http.Unirest
import org.springframework.stereotype.Service


@Service
class ProjectsServiceImpl(val projectsRepository : ProjectsRepository) : ProjectsService {

    override fun saveProjects(projects : List<Project>) {
        projectsRepository.saveAll(projects)
    }

    override fun saveProject(project : Project) {
        projectsRepository.save(project)
    }

    override fun deleteProject(project : Project) {
        projectsRepository.delete(project)
    }

    override fun getProjects() : List<Project> {
        return projectsRepository.findAll()
    }

    override fun getProject(id : String) : Project {
        return projectsRepository.findById(id).get()
    }

    override fun removeRepoDeletedProjects(projects : ArrayList<Project>) {
        val projectsForDelete = ArrayList<Project>()

        // add to list projects that are deleted from github
        getProjects().forEach {dbProject ->
            // check if project from database with his id exist projects list from github request
            if(!projects.stream().anyMatch{it.id == dbProject.id}) projectsForDelete.add(dbProject)
        }
        // delete all projects that are deleted from database
        projectsRepository.deleteAll(projectsForDelete)
    }

    @Throws(Exception::class)
    override fun getGithubProjects() {
        try {
            // get projects list from github
            requestProjects().apply {
                removeRepoDeletedProjects(this)
                saveProjects(this)
            }
        }catch (e : Exception) {
            e.printStackTrace()
        }
    }

    @Throws(NullPointerException::class)
    override fun requestProjects() : ArrayList<Project> {
        val projects = Unirest.get("https://api.github.com/users/krypton17/repos").asJson().body

        // check if projects are not empty
        if (projects.array[0] == null) throw NullPointerException()

        return ArrayList<Project>().apply {
            // deserialize every project from response to Project model via object mapper
            projects.array.forEach{
                add(ObjectMapper().readValue(it.toString(), Project().javaClass))
            }
        }
    }
}