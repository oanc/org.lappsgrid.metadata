package org.lappsgrid.metadata

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import groovy.transform.CompileStatic

/**
 * @author Keith Suderman
 */
@CompileStatic
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(["schema","name","version","description","vendor","allow","license","url", "parameters", "requires", "produces"])
class ServiceMetadata {

    public static final String DEFAULT_SCHEMA_URL = 'http://vocab.lappsgrid.org/schema/service-schema-1.0.0.json'

    // ObjectMapper instances are thread safe as long as we don't try to reconfigure
    // them after they have been created.  Therefore we create two instances of
    // ObjectMapper, one to pretty print the JSON and one that prints a compressed
    // form of JSON.
    private static final ObjectMapper compressedMapper
    private static final ObjectMapper prettyMapper

    static {
        compressedMapper = new ObjectMapper()
        compressedMapper.disable(SerializationFeature.INDENT_OUTPUT)

        prettyMapper = new ObjectMapper()
        prettyMapper.enable(SerializationFeature.INDENT_OUTPUT)
    }

    public ServiceMetadata() {
        this.schema = DEFAULT_SCHEMA_URL
    }

    public ServiceMetadata(String json)
    {
        ServiceMetadata proxy = compressedMapper.readValue(json, ServiceMetadata)
//        ServiceMetadata.getDeclaredFields().each { Field f ->
//            if ((f.modifiers & Modifier.PUBLIC) != 0) {
//                println "Found public field ${f.name}"
//                Object fieldValue = f.get(proxy)
//                if (fieldValue != null) {
//                    println "Setting ${fieldValue}"
//                    f.set(this, fieldValue)
//                }
//            }
//        }
        this.schema = proxy.schema
        this.name = proxy.name
        this.vendor = proxy.vendor
        this.version = proxy.version
        this.description = proxy.description
        this.allow = proxy.allow
        this.license = proxy.license
        this.url = proxy.url
        this.parameters = proxy.parameters
        this.requires = proxy.requires
        this.produces = proxy.produces
    }

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
     * The license for this service.
     */
    String license

    /**
     * The full URL used to invoke the service.
     */
    String url

    /**
     * Descriptions of the parameters required by the service.
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List parameters = []

    /**
     * Output format specification.  The output specification consists of:
     *  - the character encoding
     *  - the language produced.
     *  - the document format (ContentType)
     *  - annotations produced.
     */
    IOSpecification produces = new IOSpecification()

    /**
     * The input requirements of the service.  The input specification consists of:
     *  - the character encoding
     *  - the input language accepted
     *  - the document format(s) (ContentTypes) accepted.
     *  - required annotations.
     */
    IOSpecification requires = new IOSpecification()

//    IOSpecification getRequires() {
//        if (requires == null) {
//            requires = new IOSpecification()
//        }
//        return requires
//    }
//
//    IOSpecification getProduces() {
//        if (produces == null) {
//            produces = new IOSpecification()
//        }
//        return produces
//    }

    String toJson() {
//        return new JsonBuilder(this).toString()
        return compressedMapper.writeValueAsString(this)
    }

    String toPrettyJson() {
//        return new JsonBuilder(this).toPrettyString()
        return prettyMapper.writeValueAsString(this)
    }

    String toString()
    {
        return toPrettyJson()
    }

}
