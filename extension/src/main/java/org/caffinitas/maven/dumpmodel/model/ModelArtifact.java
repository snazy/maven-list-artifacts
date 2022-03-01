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
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.versioning.OverConstrainedVersionException;

@SuppressWarnings("unused")
public class ModelArtifact {
  private final Artifact artifact;

  public ModelArtifact(Artifact artifact) {
    this.artifact = artifact;
  }

  public String getGroupId() {
    return artifact.getGroupId();
  }

  public String getArtifactId() {
    return artifact.getArtifactId();
  }

  @JsonInclude(Include.NON_NULL)
  public String getVersion() {
    return artifact.getVersion();
  }

  @JsonInclude(Include.NON_NULL)
  public String getScope() {
    return artifact.getScope();
  }

  @JsonInclude(Include.NON_NULL)
  public String getType() {
    return artifact.getType();
  }

  @JsonInclude(Include.NON_NULL)
  public String getClassifier() {
    return artifact.getClassifier();
  }

  @JsonInclude(Include.NON_NULL)
  public String getId() {
    return artifact.getId();
  }

  @JsonInclude(Include.NON_NULL)
  public String getDownloadUrl() {
    return artifact.getDownloadUrl();
  }

  @JsonInclude(Include.NON_NULL)
  public ModelArtifactVersion getSelectedVersion() throws OverConstrainedVersionException {
    return Optional.ofNullable(artifact.getSelectedVersion())
        .map(ModelArtifactVersion::new)
        .orElse(null);
  }
}
