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
package org.caffinitas.maven.dumpmodel.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Optional;
import org.apache.maven.artifact.repository.ArtifactRepository;

@SuppressWarnings("unused")
public class ModelArtifactRepository {

  private final ArtifactRepository artifactRepository;

  public ModelArtifactRepository(ArtifactRepository artifactRepository) {
    this.artifactRepository = artifactRepository;
  }

  @JsonInclude(Include.NON_NULL)
  public String getUrl() {
    return artifactRepository.getUrl();
  }

  @JsonInclude(Include.NON_NULL)
  public String getBasedir() {
    return artifactRepository.getBasedir();
  }

  @JsonInclude(Include.NON_NULL)
  public String getProtocol() {
    return artifactRepository.getProtocol();
  }

  public String getId() {
    return artifactRepository.getId();
  }

  @JsonInclude(Include.NON_NULL)
  public ModelArtifactRepositoryPolicy getSnapshots() {
    return Optional.ofNullable(artifactRepository.getSnapshots())
        .map(ModelArtifactRepositoryPolicy::new)
        .orElse(null);
  }

  @JsonInclude(Include.NON_NULL)
  public ModelArtifactRepositoryPolicy getReleases() {
    return Optional.ofNullable(artifactRepository.getReleases())
        .map(ModelArtifactRepositoryPolicy::new)
        .orElse(null);
  }

  @JsonInclude(Include.NON_NULL)
  public ModelArtifactRepositoryLayout getLayout() {
    return Optional.ofNullable(artifactRepository.getLayout())
        .map(ModelArtifactRepositoryLayout::new)
        .orElse(null);
  }

  @JsonInclude(Include.NON_NULL)
  public String getKey() {
    return artifactRepository.getKey();
  }
}
