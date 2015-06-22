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
 * The JSON objects returned by calls to
 * {@link org.lappsgrid.api.DataSource#getMetadata}.
 *
 * Typically Lapp services will either generate the metadata at compile time or at
 * runtime when the service is first launched.  The metadata can then be cached
 * until {@code getMetadata()} is called.
 *
 * @author Keith Suderman
 */
@CompileStatic
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(["schema","name","version","description","vendor","allow","license"])
class DataSourceMetadata {
    public static final String DEFAULT_SCHEMA_URL = 'http://vocab.lappsgrid.org/schema/datasource-schema-1.0.0.json'

    /** The JSON-LD schema that defines the metadata layout.  This will be provided
     * automatically by the framework, but can be overridden if needed.
     */
    @JsonProperty('$schema')
    String schema

    /** The name of the service.  For Java services this is typically the fully
     * qualified class name of the class implementing the service and will be filled
     * in automatically by the framework.
     */
    String name

    /** A URI defining the organization providing the service. */
    String vendor

    /** The service version.  For Maven projects the version number will be parsed
     * from the pom.xml file.
     */
    String version

    /** A brief description of the service. */
    String description

    /** A URI from the Lapps vocabulary defining allowable usages.  Defaults to any. */
    String allow

    /** A URI from the Lapps vocabulary defining the software license.  Defaults to
     * the Apache 2.0 license.
     */
    String license

    /** Languages available from the data source. */
    List<String> language

    /** Formats available from the data source.  The data format should be a URI
     * from the Lapps vocabulary.
     */
    List<String> format

    /** The character encoding for documents from the data source. Default is UTF-8 */

    String encoding

    public DataSourceMetadata() {
        this.schema = DEFAULT_SCHEMA_URL
    }

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

    public void addLanguage(String lang) {
        if (language == null) {
            language = new ArrayList<String>();
        }
        language.add(lang);
    }

    public void addFormat(String f) {
        if (format == null) {
            format = new ArrayList<>();
        }
        format.add(f);
    }
}
