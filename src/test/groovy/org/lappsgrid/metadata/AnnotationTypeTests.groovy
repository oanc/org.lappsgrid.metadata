package org.lappsgrid.metadata

import org.junit.Ignore
import spock.lang.*
//import static org.lappsgrid.metadata.AnnotationType.*
import org.lappsgrid.discriminator.Uri


/**
 * @author Keith Suderman
 */
@Ignore
class AnnotationTypeTests { //} extends Specification {
//    def "test annotation types"() {
//        expect:
//        AnnotationType.TOKEN.uri == Uri.TOKEN
//        AnnotationType.SENTENCE.uri == Uri.SENTENCE
//        AnnotationType.DATE.uri == Uri.DATE
//        AnnotationType.PERSON.uri == Uri.PERSON
//        AnnotationType.LOCATION.uri == Uri.LOCATION
//        AnnotationType.ORGANIZATION.uri == Uri.ORGANIZATION
//        AnnotationType.POS.uri == Uri.POS
//    }

//    @Test
//    void testAll() {
//        def STATIC = java.lang.reflect.Modifier.STATIC
//        def FINAL = java.lang.reflect.Modifier.FINAL
//
//        def test = { f ->
//            return (f.type == AnnotationType) && (f.modifiers & (STATIC | FINAL))
//        }
//        def fields = AnnotationType.declaredFields.findAll { test it }
//        fields.each { Field field ->
//            AnnotationType type = field.get(null)
//            if (type.is(AnnotationType.POS)) {
//                assertTrue type.uri == "http://vocab.lappsgrid.org/Token#pos"
//            }
//            else {
//                String expected = "http://vocab.lappsgrid.org/${type.name}"
//                assertTrue "Expected: $expected buf found ${type.uri}", type.uri == expected
//            }
//        }
//    }
}
