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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.project.MavenProject;
import org.caffinitas.maven.dumpmodel.model.ModelArtifact;
import org.caffinitas.maven.dumpmodel.model.ModelArtifactRepository;
import org.caffinitas.maven.dumpmodel.model.ModelDependency;
import org.caffinitas.maven.dumpmodel.model.ModelExtension;
import org.caffinitas.maven.dumpmodel.model.ModelPlugin;
import org.caffinitas.maven.dumpmodel.model.ModelProfile;
import org.caffinitas.maven.dumpmodel.model.ModelRemoteRepository;
import org.caffinitas.maven.dumpmodel.model.ModelRepository;

@SuppressWarnings("unused")
public class ModelWrapper {

  private final ProjectsHierarchy projectsHierarchy;
  private final ProjectChildren projectChildren;
  private final MavenProject project;

  public ModelWrapper(ProjectsHierarchy projectsHierarchy, ProjectChildren projectChildren) {
    this.projectsHierarchy = projectsHierarchy;
    this.projectChildren = projectChildren;
    this.project = projectChildren.getProject();
  }

  public Map<String, ModelWrapper> getModules() {
    return project.getModules().stream()
        .filter(mod -> projectsHierarchy.getModuleOf(projectChildren, mod) != null)
        .collect(
            Collectors.toMap(
                mod -> mod,
                mod ->
                    new ModelWrapper(
                        projectsHierarchy, projectsHierarchy.getModuleOf(projectChildren, mod))));
  }

  public Map<String, String> getProperties() {
    return project.getProperties().entrySet().stream()
        .collect(
            Collectors.toMap(
                e -> e.getKey().toString(),
                e -> e.getValue().toString(),
                (a, b) -> a,
                TreeMap::new));
  }

  @JsonInclude(Include.NON_NULL)
  public File getFile() {
    return project.getFile();
  }

  @JsonInclude(Include.NON_NULL)
  public File getBasedir() {
    return project.getBasedir();
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<String> getCompileSourceRoots() {
    return project.getCompileSourceRoots();
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<String> getTestCompileSourceRoots() {
    return project.getTestCompileSourceRoots();
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<String> getCompileClasspathElements() throws DependencyResolutionRequiredException {
    return project.getCompileClasspathElements();
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<String> getTestClasspathElements() throws DependencyResolutionRequiredException {
    return project.getTestClasspathElements();
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<String> getRuntimeClasspathElements() throws DependencyResolutionRequiredException {
    return project.getRuntimeClasspathElements();
  }

  @JsonInclude(Include.NON_NULL)
  public String getModelVersion() {
    return project.getModelVersion();
  }

  public String getId() {
    return project.getId();
  }

  public String getGroupId() {
    return project.getGroupId();
  }

  public String getArtifactId() {
    return project.getArtifactId();
  }

  public String getName() {
    return project.getName();
  }

  public String getVersion() {
    return project.getVersion();
  }

  @JsonInclude(Include.NON_NULL)
  public String getPackaging() {
    return project.getPackaging();
  }

  @JsonInclude(Include.NON_NULL)
  public String getDefaultGoal() {
    return project.getDefaultGoal();
  }

  @JsonInclude(Include.NON_NULL)
  public String getInceptionYear() {
    return project.getInceptionYear();
  }

  @JsonInclude(Include.NON_NULL)
  public String getUrl() {
    return project.getUrl();
  }

  @JsonInclude(Include.NON_NULL)
  public String getDescription() {
    return project.getDescription();
  }

  @JsonInclude(Include.NON_EMPTY)
  public Map<String, List<String>> getInjectedProfileIds() {
    return project.getInjectedProfileIds();
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<String> getFilters() {
    return project.getFilters();
  }

  @JsonInclude(Include.NON_NULL)
  public ModelArtifact getParent() {
    return Optional.ofNullable(project.getParentArtifact()).map(ModelArtifact::new).orElse(null);
  }

  @JsonInclude(Include.NON_EMPTY)
  public Map<String, ModelArtifact> getArtifacts() {
    return project.getArtifactMap().entrySet().stream()
        .collect(
            Collectors.toMap(
                Entry::getKey, e -> new ModelArtifact(e.getValue()), (a, b) -> a, TreeMap::new));
  }

  @JsonInclude(Include.NON_EMPTY)
  public Map<String, ModelArtifact> getPluginArtifacts() {
    return project.getPluginArtifactMap().entrySet().stream()
        .collect(
            Collectors.toMap(
                Entry::getKey, e -> new ModelArtifact(e.getValue()), (a, b) -> a, TreeMap::new));
  }

  @JsonInclude(Include.NON_EMPTY)
  public Map<String, ModelArtifact> getProjectReferences() {
    return project.getProjectReferences().entrySet().stream()
        .collect(
            Collectors.toMap(
                Entry::getKey,
                e -> new ModelArtifact(e.getValue().getArtifact()),
                (a, b) -> a,
                TreeMap::new));
  }

  @JsonInclude(Include.NON_EMPTY)
  public Map<String, ModelArtifact> getManagedVersionMap() {
    return project.getManagedVersionMap().entrySet().stream()
        .collect(
            Collectors.toMap(
                Entry::getKey, e -> new ModelArtifact(e.getValue()), (a, b) -> a, TreeMap::new));
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<ModelDependency> getDependencies() {
    return project.getDependencies().stream()
        .map(ModelDependency::new)
        .collect(Collectors.toList());
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<ModelPlugin> getBuildPlugins() {
    return project.getBuildPlugins().stream().map(ModelPlugin::new).collect(Collectors.toList());
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<ModelPlugin> getPluginManagement() {
    return project.getPluginManagement().getPlugins().stream()
        .map(ModelPlugin::new)
        .collect(Collectors.toList());
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<ModelArtifactRepository> getRemoteArtifactRepositories() {
    return project.getRemoteArtifactRepositories().stream()
        .map(ModelArtifactRepository::new)
        .collect(Collectors.toList());
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<ModelArtifactRepository> getPluginArtifactRepositories() {
    return project.getPluginArtifactRepositories().stream()
        .map(ModelArtifactRepository::new)
        .collect(Collectors.toList());
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<ModelRepository> getPluginRepositories() {
    return project.getPluginRepositories().stream()
        .map(ModelRepository::new)
        .collect(Collectors.toList());
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<ModelRemoteRepository> getRemoteProjectRepositories() {
    return project.getRemoteProjectRepositories().stream()
        .map(ModelRemoteRepository::new)
        .collect(Collectors.toList());
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<ModelRemoteRepository> getRemotePluginRepositories() {
    return project.getRemotePluginRepositories().stream()
        .map(ModelRemoteRepository::new)
        .collect(Collectors.toList());
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<ModelProfile> getActiveProfiles() {
    return project.getActiveProfiles().stream().map(ModelProfile::new).collect(Collectors.toList());
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<ModelArtifact> getAttachedArtifacts() {
    return project.getAttachedArtifacts().stream()
        .map(ModelArtifact::new)
        .collect(Collectors.toList());
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<ModelExtension> getBuildExtensions() {
    return project.getBuildExtensions().stream()
        .map(ModelExtension::new)
        .collect(Collectors.toList());
  }
}
