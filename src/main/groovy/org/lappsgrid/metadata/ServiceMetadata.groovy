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
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import groovy.transform.CompileStatic
import org.lappsgrid.discriminator.Discriminators
import org.lappsgrid.serialization.Data
import org.lappsgrid.serialization.Serializer

/**
 * The JSON objects returned by calls to
 * {@link org.lappsgrid.api.ProcessingService#getMetadata}.
 *
 * Typically Lapp services will either generate this metadata at compile time or at
 * runtime when the service is first launched.  The metadata can then be cached
 * until {@code getMetadata()} is called.
 *
 * @author Keith Suderman
 */
@CompileStatic
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(["schema","name","version", "toolVersion", "description","vendor","allow","license", "licenseDesc","url", "parameters", "requires", "produces"])
class  ServiceMetadata {

    public static final String DEFAULT_SCHEMA_URL = 'https://vocab.lappsgrid.org/schema/1.1.0/metadata-schema.json'

    /** The JSON schema that describes the JSON format. */
    @JsonProperty('$schema')
    String schema

    /** A human readable name for the service. */
    String name

    /** Name or URI of the organization providing the service. */
    String vendor

    /**
     * The service version number in [major].[minor].[revision] format with
     * an optional trailing qualifier. E.G. 1.1.0-SNAPSHOT
     */
    String version

    /**
     * The version number of the tool being wrapped.  Not all LAPPS Grid tools
     * wrap other software packages so this field is optional.
     */
    String toolVersion

    /**
     * A plain text description of the service or the URL to an online
     * description.
     */
    String description

    /**
     * Permitted uses of this service.
     * <p>
     * This field is actually a URI that describes the allowable usages, e.g. commerial,
     * research, etc.  If this field is omitted then it is assumed that the service
     * can be used for any purpose.
     */
    String allow

    /**
     * URL to the license for this service.
     */
    String license

    /**
     * A human readable description of the license terms.  May also include markdown.
     */
    String licenseDesc

    /**
     * The full URL used to invoke the service.
     * TODO Should this be deprecated since services do not know the URL they have been deployed to.
     */
    String url

    /**
     * Descriptions of the parameters required by the service.
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List parameters = []

    /**
     * Output format specification.  The output specification consists of:
     * <ul>
     * <li> the character encoding
     * <li> the language produced.
     * <li> the document format (ContentType)
     * <li> annotations produced.
     * </ul>
     */
    IOSpecification produces = new IOSpecification()

    /**
     * The input requirements of the service.  The input specification consists of:
     * <ul>
     * <li> the character encoding
     * <li> the input language accepted
     * <li> the document format(s) (ContentTypes) accepted.
     * <li> required annotations.
     * </ul>
     */
    IOSpecification requires = new IOSpecification()

    ServiceMetadata() {
        this.schema = DEFAULT_SCHEMA_URL
    }

    ServiceMetadata(Object object) throws UnsupportedOperationException {
        if (object instanceof String) {
            mapConstructor(Serializer.parse(object.toString(), HashMap))
        }
        else if (object instanceof Map) {
            mapConstructor((Map) object)
        }
        else {
            throw new UnsupportedOperationException("Invalid object for constructopr")
        }
    }

    ServiceMetadata(String json) {
        this(Serializer.parse(json, HashMap))
    }

    ServiceMetadata(Map map) {
        mapConstructor(map)
    }

    private void mapConstructor(Map map) {
        this.schema = map.schema
        this.name = map.name
        this.vendor = map.vendor
        this.version = map.version
        this.description = map.description
        this.allow = map.allow
        this.license = map.license
        this.licenseDesc = map.licenseDesc
        this.url = map.url
        this.parameters = (List) map.parameters
        this.requires = new IOSpecification((Map)map.requires)
        this.produces = new IOSpecification((Map)map.produces)
    }

    String toString() {
        return new Data<>(Discriminators.Uri.META, this).asPrettyJson()
    }

}
