package com.krypton.projectsrepo.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
class Project(
		@Id		var id			: Long?   = null,
		@Column var name		: String? = null,
		@Column var description	: String? = null,
		@Column var language	: String? = null,
		@Column var url			: String? = null
) {
	override fun toString() : String {
		return 	"Project { \n" +
				"id				-> $id			\n" +
				"title			-> $name		\n" +
				"description	-> $description	\n" +
				"language		-> $language	\n" +
				"url			-> $url			\n }"
	}
}