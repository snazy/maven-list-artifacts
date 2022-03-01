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
import java.util.List;
import java.util.stream.Collectors;
import org.apache.maven.model.Plugin;

@SuppressWarnings("unused")
public class ModelPlugin {

  private final Plugin plugin;

  public ModelPlugin(Plugin plugin) {
    this.plugin = plugin;
  }

  public String getArtifactId() {
    return plugin.getArtifactId();
  }

  @JsonInclude(Include.NON_EMPTY)
  public List<ModelDependency> getDependencies() {
    return plugin.getDependencies().stream().map(ModelDependency::new).collect(Collectors.toList());
  }

  @JsonInclude(Include.NON_NULL)
  public String getExtensions() {
    return plugin.getExtensions();
  }

  //  public Object getGoals() {
  //    return plugin.getGoals();
  //  }

  public String getGroupId() {
    return plugin.getGroupId();
  }

  @JsonInclude(Include.NON_NULL)
  public String getVersion() {
    return plugin.getVersion();
  }

  //  public Map<String, PluginExecution> getExecutionsAsMap() {
  //    return plugin.getExecutionsAsMap();
  //  }
  //
  //  public List<PluginExecution> getExecutions() {
  //    return plugin.getExecutions();
  //  }

  public String getId() {
    return plugin.getId();
  }

  @JsonInclude(Include.NON_NULL)
  public String getKey() {
    return plugin.getKey();
  }

  @JsonInclude(Include.NON_NULL)
  public String getInherited() {
    return plugin.getInherited();
  }
}
