package org.lappsgrid.metadata

import org.junit.*
import static org.junit.Assert.*


/**
 * @author Keith Suderman
 */
class ContentTypeTests {

    @Test
    void isaTest() {
        assertTrue ContentType.GATE.isa(ContentType.XML)
        assertTrue(ContentType.UIMA.isa(ContentType.XML))
//        assertTrue(ContentType.JSONLD.isa(ContentType.JSON))
//        assertTrue(ContentType.LAPPS.isa(ContentType.JSONLD))
//        assertTrue(ContentType.LAPPS.isa(ContentType.JSON))
    }

    @Test
    void equalsTest() {
        assertTrue ContentType.GATE.equals(ContentType.GATE)
        ContentType gate = new ContentType("application/xml; schema=gate")
        assertTrue gate.equals(ContentType.GATE)
        assertTrue ContentType.GATE.equals(gate)
        assertTrue ContentType.TEXT == new ContentType("text/plain")
        assertTrue new ContentType("text/plain") == ContentType.TEXT

    }

    @Test
    void listTest() {
        List<ContentType> list = [ ContentType.TEXT, new ContentType("application/xml") ]
        assertTrue list.contains(ContentType.TEXT)
        assertTrue list.contains(new ContentType("text/plain"))
        assertTrue list.contains(ContentType.XML)
        assertTrue list.contains(new ContentType("application/xml"))
    }
    @Test
    void constructor1Test() {
        ContentType type = new ContentType("text/plain")
        assertTrue type.type == "text/plain"
        assertTrue type.parameters.size() == 0
    }

    @Test
    void constructor2Test() {
        ContentType type = new ContentType("application/xml; schema=gate")
        assertTrue type.type == "application/xml"
        assertTrue type.parameters.size() == 1
        assertTrue type.parameters.schema == "gate"
    }

    @Test
    void constructor21Test() {
        ContentType type = new ContentType("application/xml; schema=gate;")
        assertTrue type.type == "application/xml"
        assertTrue type.parameters.size() == 1
        assertTrue type.parameters.schema == "gate"
        assertTrue type.toString() == "application/xml; schema=gate"
    }

    @Test
    void constructorMapTest() {
        ContentType type = new ContentType("application/xml", [charset:'UTF-8', schema:'gate'])
        assertTrue type.type == "application/xml"
        assertTrue type.parameters.size() == 2
        assertTrue type.parameters.schema == "gate"
        assertTrue type.parameters.charset == 'UTF-8'
    }

    @Test
    void constructorParametersTest() {
        ContentType type = new ContentType("application/xml; charset=UTF-8; schema=gate")
        assertTrue type.type == "application/xml"
        assertTrue type.parameters.size() == 2
        assertTrue type.parameters.schema == "gate"
        assertTrue type.parameters.charset == 'UTF-8'
    }
}
