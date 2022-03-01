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
import org.eclipse.aether.repository.RemoteRepository;

@SuppressWarnings("unused")
public class ModelRemoteRepository {

  private final RemoteRepository remoteRepository;

  public ModelRemoteRepository(RemoteRepository remoteRepository) {
    this.remoteRepository = remoteRepository;
  }

  public String getId() {
    return remoteRepository.getId();
  }

  @JsonInclude(Include.NON_NULL)
  public String getContentType() {
    return remoteRepository.getContentType();
  }

  public String getUrl() {
    return remoteRepository.getUrl();
  }

  @JsonInclude(Include.NON_NULL)
  public String getProtocol() {
    return remoteRepository.getProtocol();
  }

  @JsonInclude(Include.NON_NULL)
  public String getHost() {
    return remoteRepository.getHost();
  }

  public boolean isRepositoryManager() {
    return remoteRepository.isRepositoryManager();
  }

  public boolean isBlocked() {
    return remoteRepository.isBlocked();
  }
}
