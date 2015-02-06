package org.lappsgrid.metadata

import org.junit.*

import static org.junit.Assert.*

/**
 * @author Keith Suderman
 */
class IOSpecificationTests {

    final static String XML = 'application/xml'
    final static String TEXT = 'text/plain'
    final static String GATE = 'application/xml:format=gate'
    final static String TOKEN = 'token'

    @Test
    void specSatisfiesItself() {
        println "IOSpecificationTests.test1"
        IOSpecification spec1 = newSpec('us-ascii', "en", "application/text", "token")
        assertTrue spec1.satisfies(spec1)
    }

    @Test
    void test2() {
        println "IOSpecificationTests.test2"
//        def contentType = [ContentType.TEXT, ContentType.XML, ContentType.GATE]
        def contentType = [TEXT, XML, GATE]
        IOSpecification required = newSpec(contentType, [TOKEN])
        IOSpecification spec1 = newSpec(TEXT, TOKEN)
        IOSpecification spec2 = newSpec(XML, TOKEN)
        IOSpecification spec3 = newSpec(GATE, TOKEN)
        assertTrue spec1.satisfies(required)
        assertTrue spec2.satisfies(required)
        assertTrue spec3.satisfies(required)
    }

    @Test
    void test3() {
        println "IOSpecificationTests.test3"
        def annotations = [TOKEN, 'pos', 's']
        IOSpecification required = newSpec(XML, TOKEN)
        IOSpecification provided = newSpec([XML], annotations)
        assertTrue provided.satisfies(required)
    }

    @Test
    void testEquals() {
        println "IOSpecificationTests.testEquals"
        IOSpecification s1 = newSpec(XML, TOKEN)
        IOSpecification s2 = newSpec(XML, TOKEN)
        assertTrue s1 == s1
        assertTrue s1 == s2
        assertTrue s2 == s1

    }

    IOSpecification newSpec(String encoding, String language, String contentType, String annotationType) {
        IOSpecification spec = new IOSpecification()
        spec.encoding = encoding
        spec.language << language
        spec.format << contentType
        spec.annotations << annotationType
//        spec.add(contentType)
//        spec.add(annotationType)
        return spec
    }
    IOSpecification newSpec(String contentType, String annotationType) {
        IOSpecification spec = new IOSpecification()
        spec.encoding = "UTF-8"
        spec.language = [ "en" ]
        spec.format << contentType
        spec.annotations << annotationType
//        spec.add(contentType)
//        spec.add(annotationType)
        return spec
    }
    IOSpecification newSpec(List<String> contentTypes, List<String> annotationTypes) {
        IOSpecification spec = new IOSpecification()
        spec.encoding = "UTF-8"
        spec.language = [ "en" ]
        spec.format.addAll contentTypes
        spec.annotations.addAll annotationTypes
        return spec
    }
}
