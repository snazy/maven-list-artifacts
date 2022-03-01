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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.util.Collections;
import org.apache.maven.AbstractMavenLifecycleParticipant;
import org.apache.maven.MavenExecutionException;
import org.apache.maven.execution.MavenSession;
import org.codehaus.plexus.component.annotations.Component;

/** A very special Maven lifecycle extension that emits (most of) the Maven model as JSON. */
@Component(role = AbstractMavenLifecycleParticipant.class, hint = "dump-model")
public class DumpModelExtension extends AbstractMavenLifecycleParticipant {

  @SuppressWarnings("unused")
  public DumpModelExtension() {
    // noop
  }

  @Override
  public void afterProjectsRead(MavenSession session) throws MavenExecutionException {
    ModelContainer container = ModelContainer.create(session);

    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter prettyPrinter = mapper.writerWithDefaultPrettyPrinter();
    try {
      JsonGenerator generator = prettyPrinter.createGenerator(System.out);
      generator.writeObject(container);
    } catch (IOException e) {
      throw new MavenExecutionException("Failed to serialize Maven model to JSON", e);
    }

    session.setProjects(Collections.singletonList(session.getTopLevelProject()));
  }
}
