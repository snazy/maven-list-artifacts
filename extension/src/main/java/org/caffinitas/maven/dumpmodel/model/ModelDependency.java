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
import org.apache.maven.model.Dependency;

@SuppressWarnings("unused")
public class ModelDependency {
  private final Dependency dependency;

  public ModelDependency(Dependency dependency) {
    this.dependency = dependency;
  }

  public String getArtifactId() {
    return dependency.getArtifactId();
  }

  @JsonInclude(Include.NON_NULL)
  public String getClassifier() {
    return dependency.getClassifier();
  }

  public String getGroupId() {
    return dependency.getGroupId();
  }

  @JsonInclude(Include.NON_NULL)
  public String getOptional() {
    return dependency.getOptional();
  }

  @JsonInclude(Include.NON_NULL)
  public String getScope() {
    return dependency.getScope();
  }

  @JsonInclude(Include.NON_NULL)
  public String getSystemPath() {
    return dependency.getSystemPath();
  }

  @JsonInclude(Include.NON_NULL)
  public String getType() {
    return dependency.getType();
  }

  @JsonInclude(Include.NON_NULL)
  public String getVersion() {
    return dependency.getVersion();
  }

  @JsonInclude(Include.NON_NULL)
  public String getManagementKey() {
    return dependency.getManagementKey();
  }
}
