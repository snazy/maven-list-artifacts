/*
 * Copyright (C) 2022 Robert Stupp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.caffinitas.maven.dumpmodel;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;

public class ProjectsHierarchy {

  private final Map<Path, ProjectChildren> projectsByPathMap = new HashMap<>();
  private final ProjectChildren root;

  public static ProjectsHierarchy createHierarchyFromMavenSession(MavenSession session) {

    Path rootPath = session.getTopLevelProject().getBasedir().toPath();

    Path emptyPath = rootPath.relativize(rootPath);

    ProjectChildren root =
        ProjectChildren.builder()
            .path(emptyPath)
            .moduleName("")
            .project(session.getTopLevelProject())
            .build();
    ProjectsHierarchy projectsHierarchy = new ProjectsHierarchy(root);

    for (MavenProject project : session.getAllProjects()) {
      Path relative = rootPath.relativize(project.getBasedir().toPath());

      ProjectChildren module = projectsHierarchy.projectsByPathMap.get(relative);
      if (module == null) {
        int i = relative.getNameCount();
        Path part = i == 0 ? Paths.get("") : relative.subpath(0, i);
        String name = i == 0 ? "" : relative.getName(i - 1).toString();
        module = ProjectChildren.builder().path(relative).moduleName(name).project(project).build();
        projectsHierarchy.projectsByPathMap.put(relative, module);
      }
    }

    return projectsHierarchy;
  }

  public ProjectsHierarchy(ProjectChildren root) {
    this.root = root;
  }

  public ProjectChildren getRoot() {
    return root;
  }

  public ProjectChildren getModuleOf(ProjectChildren base, String module) {
    Path modulePath = base.getPath().resolve(module).normalize();
    return projectsByPathMap.get(modulePath);
  }

  public Collection<ProjectChildren> allProjects() {
    return projectsByPathMap.values();
  }
}
