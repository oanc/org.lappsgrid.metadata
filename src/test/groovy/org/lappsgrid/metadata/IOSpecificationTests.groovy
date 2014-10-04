package org.lappsgrid.metadata

import org.junit.*

import static org.junit.Assert.*

/**
 * @author Keith Suderman
 */
class IOSpecificationTests {

    @Test
    void test1() {
        println "IOSpecificationTests.test1"
        IOSpecification spec1 = newSpec('us-ascii', "en", ContentType.TEXT, AnnotationType.TOKEN)
        assertTrue spec1.satisfies(spec1)
    }

    @Test
    void test2() {
        println "IOSpecificationTests.test2"
        def contentType = [ContentType.TEXT, ContentType.XML, ContentType.GATE]
        IOSpecification required = newSpec(contentType, [AnnotationType.TOKEN])
        IOSpecification spec1 = newSpec(ContentType.TEXT, AnnotationType.TOKEN)
        IOSpecification spec2 = newSpec(ContentType.XML, AnnotationType.TOKEN)
        IOSpecification spec3 = newSpec(ContentType.GATE, AnnotationType.TOKEN)
        assertTrue spec1.satisfies(required)
        assertTrue spec2.satisfies(required)
        assertTrue spec3.satisfies(required)
    }

    @Test
    void test3() {
        println "IOSpecificationTests.test3"
        def annotations = [AnnotationType.TOKEN, AnnotationType.POS, AnnotationType.SENTENCE]
        IOSpecification required = newSpec(ContentType.XML, AnnotationType.TOKEN)
        IOSpecification provided = newSpec([ContentType.XML], annotations)
        assertTrue provided.satisfies(required)
    }

    @Test
    void testEquals() {
        println "IOSpecificationTests.testEquals"
        IOSpecification s1 = newSpec(ContentType.XML, AnnotationType.TOKEN)
        IOSpecification s2 = newSpec(ContentType.XML, AnnotationType.TOKEN)
        assertTrue s1 == s1
        assertTrue s1 == s2
        assertTrue s2 == s1

    }

    IOSpecification newSpec(String encoding, String language, ContentType contentType, AnnotationType annotationType) {
        IOSpecification spec = new IOSpecification()
        spec.encoding = encoding
        spec.language = [ language ]
        spec.add(contentType)
        spec.add(annotationType)
        return spec
    }
    IOSpecification newSpec(ContentType contentType, AnnotationType annotationType) {
        IOSpecification spec = new IOSpecification()
        spec.encoding = "UTF-8"
        spec.language = [ "en" ]
        spec.add(contentType)
        spec.add(annotationType)
        return spec
    }
    IOSpecification newSpec(List<ContentType> contentTypes, List<AnnotationType> annotationTypes) {
        IOSpecification spec = new IOSpecification()
        spec.encoding = "UTF-8"
        spec.language = [ "en" ]
        spec.format = contentTypes
        spec.annotations = annotationTypes
        return spec
    }
}
