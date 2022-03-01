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
import org.apache.maven.model.RepositoryPolicy;

@SuppressWarnings("unused")
public class ModelRepositoryPolicy {
  private final RepositoryPolicy repositoryPolicy;

  public ModelRepositoryPolicy(RepositoryPolicy repositoryPolicy) {
    this.repositoryPolicy = repositoryPolicy;
  }

  @JsonInclude(Include.NON_NULL)
  public String getChecksumPolicy() {
    return repositoryPolicy.getChecksumPolicy();
  }

  public String getEnabled() {
    return repositoryPolicy.getEnabled();
  }

  @JsonInclude(Include.NON_NULL)
  public String getUpdatePolicy() {
    return repositoryPolicy.getUpdatePolicy();
  }

  public boolean isEnabled() {
    return repositoryPolicy.isEnabled();
  }
}
