package org.lappsgrid.metadata

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.CompileStatic

/**
 * @author Keith Suderman
 */
@CompileStatic
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IOSpecification {
    String encoding //= 'UTF-8'
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<String> language = []
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<ContentType> format = []
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<AnnotationType> annotations = []

    void addFormat(String format) {
        format << format
    }

    void addAnnotation(String annotation) {
        annotations << new AnnotationType(annotation)
    }

    void add(ContentType type) {
        format << type
    }

    void add(AnnotationType type) {
        annotations << type
    }

    void add(String language) {
        this.language << language
    }

    void add(String[] languages) {
        this.language.addAll(languages)
    }

    boolean satisfies(IOSpecification required) {
        if (required.encoding && this.encoding != required.encoding) {
            println "Encodings do not match"
           return false
        }
        def intersection = language.intersect(required.language)
        if (intersection.size() == 0) {
            println "Languages do not match"
            return false
        }

        intersection = format.intersect(required.format)
        if (intersection.size() == 0) {
            println "Formats do not match"
            return false
        }
        intersection = annotations.intersect(required.annotations)
        if (intersection.size() != required.annotations.size()) {
            println "Annotation types not satisfied."
            return false
        }
        return true
    }

    boolean equals(Object other) {
        if (other instanceof IOSpecification) {
            IOSpecification spec = (IOSpecification) other
            return this.encoding == spec.encoding &&
                    subsumes(this.language, spec.language) &&
                    subsumes(spec.language, this.language) &&
                    subsumes(this.format, spec.format) &&
                    subsumes(spec.format, this.format) &&
                    subsumes(this.annotations, spec.annotations) &&
                    subsumes(spec.annotations, this.annotations)

        }
        return false
    }
    private <T> boolean subsumes(List<T> list1, List<T> list2)
    {
//        println "${list1} subsumes ${list2}"
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            T t = it.next()
            if (!list1.contains(t)) {
//                println "List1 does not contain ${t}"
                return false;
            }
        }
        return true;
    }

}
