package org.lappsgrid.metadata

import org.junit.*
import static org.junit.Assert.*

/**
 * @author Keith Suderman
 */
class JsonTests {

    @Test
    void displayJson() {
        ServiceMetadata meta = new ServiceMetadata()
        meta.name = "GATE Tokenizer"
        meta.version = "2.0.0"
        meta.description = "ANNIE English Tokenizer from GATE."
        meta.allow = "http://vocab.lappsgrid.org/ns/allow/any"
        meta.vendor = "http://www.anc.org"
        meta.requires = newSpec([ContentType.TEXT, ContentType.GATE, ContentType.XML], [AnnotationType.TOKEN, AnnotationType.SENTENCE])
        meta.produces = newSpec([ContentType.GATE], [AnnotationType.TOKEN,AnnotationType.SENTENCE,AnnotationType.POS])

        String json = meta.toPrettyJson()
        ServiceMetadata meta2 = new ServiceMetadata(json)

        assertTrue meta.name == meta2.name
        assertTrue meta.vendor == meta2.vendor
        assertTrue meta.version == meta2.version
        assertTrue meta.description == meta2.description
        assertTrue meta.url == meta2.url
        assertTrue meta.requires == meta2.requires
        assertTrue meta.produces == meta2.produces
        println json
    }

    def newSpec(List contentType, List annotations) {
        IOSpecification spec = new IOSpecification()
        spec.encoding = "UTF-8"
        spec.language << "en"
        spec.format = contentType
        spec.annotations = annotations
        return spec
    }
}
