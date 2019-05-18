package com.krypton.projectsrepo.service

import com.krypton.projectsrepo.model.Project

interface ProjectsService {

    /**
     * Save all [Project]'s to database
     */
    fun saveProjects(projects: List<Project>)

    /**
     * Save [Project] from github to database
     *
     * @param project   [Project]
     */
    fun saveProject(project : Project)

    /**
     * Delete [Project] from database
     *
     * @param project   [Project]
     */
    fun deleteProject(project: Project)

    /**
     * Get [Project] by id
     *
     * @param id        [Project] name
     * @return [Project]
     */
    fun getProject(id : String) : Project

    /**
     * Check if [Project] from database was deleted from github and remove it
     *
     * @param projects      list of [Project]'s
     * */
    fun removeRepoDeletedProjects(projects : ArrayList<Project>)

    /**
     * Get all projects from database
     *
     * @return [Project]'s list
     */
    fun getProjects() : List<Project>

    /**
     * Get projects from github and save them to database,
     * if project was deleted,will delete it from database
     *
     * @throws Exception if [requestProjects] failed
     */
    fun getGithubProjects()

    /**
     * Get projects from  github and deserialize them to list of [Project]'s
     *
     * @return list of [Project]'s
     * @throws NullPointerException if github response was empty
     */
    fun requestProjects() : ArrayList<Project>
}