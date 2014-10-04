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

    public DataSourceMetadata(File file) {
        this(file.text)
    }
    
    public DataSourceMetadata(String json)
    {
        DataSourceMetadata proxy = compressedMapper.readValue(json, DataSourceMetadata)
        this.schema = proxy.schema
        this.name = proxy.name
        this.vendor = proxy.vendor
        this.version = proxy.version
        this.description = proxy.description
        this.allow = proxy.allow
        this.license = proxy.license
        this.language = proxy.language
        this.format = proxy.format
        this.encoding = proxy.encoding
    }

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
