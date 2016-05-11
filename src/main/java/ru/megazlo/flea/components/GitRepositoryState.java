package ru.megazlo.flea.components;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@PropertySource("classpath:git.properties")
public class GitRepositoryState {

	/*@Value("${git.build.version}")
	private String buildVer;*/
	@Value("${git.commit.id.abbrev}")
	private String abbrev;
	@Value("${git.branch}")
	private String branch;
	/*@Value("${git.build.version}")
	private String version;*/
	@Value("${git.commit.user.name}")
	private String user;
}
