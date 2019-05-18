package com.krypton.projectsrepo.repository

import com.krypton.projectsrepo.model.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectsRepository : JpaRepository<Project, String>