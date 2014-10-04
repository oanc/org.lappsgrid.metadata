package org.lappsgrid.metadata

import com.fasterxml.jackson.annotation.JsonValue
import groovy.transform.CompileStatic
import org.lappsgrid.discriminator.Discriminator
import org.lappsgrid.discriminator.DiscriminatorRegistry

/**
 * A helper class used to represent Discriminators that represent annotation
 * types.
 *
 * @author Keith Suderman
 */
@CompileStatic
class AnnotationType {
    String name
    String uri
    long id

    public static final AnnotationType TOKEN = type("token")
    public static final AnnotationType SENTENCE = type("sentence")
    public static final AnnotationType DATE = type("date")
    public static final AnnotationType PERSON = type("person")
    public static final AnnotationType LOCATION = type("location")
    public static final AnnotationType ORGANIZATION = type("organization")
    public static final AnnotationType POS = type("pos")
    public static final AnnotationType NAMED_ENTITY = type("ne")
    public static final AnnotationType MATCHES = type("matches")
    public static final AnnotationType NOUN_CHUNK = type("nchunk")
    public static final AnnotationType VERB_CHUNK = type("vchunk")

    public AnnotationType() { }
    public AnnotationType(AnnotationType type) {
        this.name = type.name
        this.uri = type.uri
        this.id = type.id
    }
    public AnnotationType(String uri) {
        Discriminator d = DiscriminatorRegistry.getByUri(uri)
        this.name = d?.name
        this.uri = d?.uri
        this.id = d?.id;
    }

    public AnnotationType(Discriminator d) {
        this.name = d.name
        this.uri = d.uri
        this.id = d.id
    }

    @Override
    boolean equals(Object object) {
        if (object instanceof AnnotationType) {
            return uri == ((AnnotationType)object).uri
        }
        return false
    }

    @Override
    int hashCode() {
        return uri.hashCode()
    }

    @JsonValue
    @Override
    String toString() {
        return uri
    }

    private static AnnotationType type(String name)
    {
        return new AnnotationType(DiscriminatorRegistry.getByName(name))
    }
}

