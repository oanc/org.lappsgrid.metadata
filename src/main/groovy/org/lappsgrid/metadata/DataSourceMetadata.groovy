/*-
 * Copyright 2015 The Language Application Grid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.lappsgrid.metadata

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import groovy.transform.CompileStatic

/**
 * @author Keith Suderman
 */
@CompileStatic
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(["schema","name","version","description","vendor","allow","license"])
class DataSourceMetadata {
    public static final String DEFAULT_SCHEMA_URL = 'http://vocab.lappsgrid.org/schema/datasource-schema-1.0.0.json'

    @JsonProperty('$schema')
    String schema
    String name
    String vendor
    String version
    String description
    String allow
    String license
    List<String> language
    List<String> format
    String encoding

    public DataSourceMetadata() {
        this.schema = DEFAULT_SCHEMA_URL
    }
//
//    public DataSourceMetadata(File file) {
//        this(file.text)
//    }
//
    public DataSourceMetadata(Map map)
    {
        this.schema = map.schema
        this.name = map.name
        this.vendor = map.vendor
        this.version = map.version
        this.description = map.description
        this.allow = map.allow
        this.license = map.license
        this.language = (List) map.language
        this.format = (List) map.format
        this.encoding = map.encoding
    }

}
