package com.krypton.projectsrepo.config

import com.krypton.projectsrepo.service.ProjectsServiceImpl
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
class Scheduler(val projectsService : ProjectsServiceImpl) {

	@Scheduled(cron = "0 0 1 * * ?")
	fun updateProjectsDB() {
		projectsService.getGithubProjects()
	}
}