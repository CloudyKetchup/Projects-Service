package com.krypton.projectsrepo.config

import com.krypton.projectsrepo.service.ProjectsServiceImpl
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service

@Service
class Startup(val projectsService : ProjectsServiceImpl) : CommandLineRunner {

    override fun run(vararg args: String?) {
        projectsService.getGithubProjects()
    }
}