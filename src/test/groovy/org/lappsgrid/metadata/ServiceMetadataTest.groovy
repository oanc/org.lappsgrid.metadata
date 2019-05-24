package org.lappsgrid.metadata

import org.junit.Ignore
import org.junit.Test
import org.lappsgrid.discriminator.Discriminators
import org.lappsgrid.serialization.Data
import org.lappsgrid.serialization.Serializer

import static org.junit.Assert.*

/**
 * @author Keith Suderman
 */
class ServiceMetadataTest {

    @Test
    void testDefaultSchema() {
        ServiceMetadata meta = new ServiceMetadata()
        assertTrue meta.schema == ServiceMetadata.DEFAULT_SCHEMA_URL
    }

    @Ignore
    void testRequiresAnnotations() {
//        ServiceMetadata metadata = new ServiceMetadata()
//        metadata.requires.annotations << AnnotationType.TOKEN
//        metadata.produces.annotations << AnnotationType.SENTENCE
//        println metadata.toPrettyJson()
    }

    @Test
    void testLoadFromJson() {
        ServiceMetadata meta = new ServiceMetadata()
        meta.name = "Test"
        meta.description = "Decription"
        meta.requires.language <<"en"
        meta.requires.format << "GATE"
        meta.produces.language << "en"
        meta.produces.format << "GATE"
        meta.produces.annotations << "TOKEN"
        String json = Serializer.toPrettyJson(meta)
        ServiceMetadata proxy = new ServiceMetadata(json)
        assertTrue proxy.name == meta.name
        assertTrue proxy.description == meta.description
        assertTrue proxy.requires.annotations.size() == meta.requires.annotations.size()
        assertTrue proxy.requires.annotations[0] == meta.requires.annotations[0]
        assertTrue proxy.produces.annotations.size() == meta.produces.annotations.size()
        assertTrue proxy.produces.annotations[0] == meta.produces.annotations[0]
    }

    @Test
    void issue17SchemaDisappears() {
       ServiceMetadata metadata = new ServiceMetadata()
        println metadata.schema
        String json = Serializer.toPrettyJson(metadata)
        Map map = Serializer.parse(json, HashMap)
        assert ServiceMetadata.DEFAULT_SCHEMA_URL == map['$schema']

        metadata = Serializer.parse(json, ServiceMetadata)

        Data data = new Data(Discriminators.Uri.META, metadata)
        println data.asPrettyJson()

        data = Serializer.parse(data.asJson(), Data)
        metadata = new ServiceMetadata((Map) data.payload)
        assert ServiceMetadata.DEFAULT_SCHEMA_URL == metadata.schema
    }

    @Ignore
    void print() {
//        ServiceMetadata meta = new ServiceMetadata()
//        meta.name = "Test"
//        meta.description = "Decription"
//        meta.requires = new IOSpecification()
//        meta.requires.language = ["en"]
//        meta.requires.format = [ ContentType.GATE ]
//        meta.produces = new IOSpecification()
//        meta.produces.language = ["en"]
//        meta.produces.format = [ ContentType.GATE ]
//        meta.produces.annotations = [ AnnotationType.TOKEN ]
//        println meta.toPrettyJson()
    }
}
