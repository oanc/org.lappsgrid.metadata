/*-
 * Copyright 2015 The Language Application Grid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.lappsgrid.metadata

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.CompileStatic

/**
 * Defines the sets of annotations, and their properties, exchanged by Lapps
 * web services.
 *
 * A service on the Lapps Grid must specify the annotations it requires
 * to perform its job, as well as the languages, formats, and character
 * encodings it understands.  Each service must also specify the annotations,
 * languages, formats, and character encodings it produces.
 *
 * @author Keith Suderman
 */
@CompileStatic
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IOSpecification {
    /** The characer encoding, defaults to UTF-8 */
    String encoding

    /** A list of ISO language codes. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<String> language = []

    /** A list of URI from the Lapps vocabulary specifying the document format. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<String> format = []

    /** A list of URI from the Lapps vocabulary specifying the annotation types. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<String> annotations = []

    public IOSpecification() {}

    public IOSpecification(Map map) {
        this.encoding = map.encoding
        map.language?.each { language.add(it.toString()) }
        map.format?.each { format.add(it.toString()) }
        map.annotations?.each { annotations << it.toString()}
    }

    void addFormat(String format) {
        this.format.add(format)
    }

    void addFormats(List<String> formats)
    {
        format.addAll(formats)
    }

    void addFormats(String[] formats)
    {
        format.addAll(Arrays.asList(formats))
    }

    void addAnnotation(String annotation) {
        annotations.add(annotation)
    }

    void addAnnotations(String[] annotation) {
        annotations.addAll(Arrays.asList(annotation))
    }

//    void add(ContentType type) {
//        format << type
//    }

//    void add(AnnotationType type) {
//        annotations << type
//    }

    void addLanguage(String language) {
        this.language.add(language)
    }

    void addLanguages(String[] languages) {
        //this.language.addAll(languages)
        for (String lang : languages) {
            this.language.add(lang)
        }
    }

//    boolean satisfies(IOSpecification required) {
//        if (required.encoding && this.encoding != required.encoding) {
//           return false
//        }
//        def intersection = language.intersect(required.language)
//        if (intersection.size() == 0) {
//            return false
//        }
//
//        intersection = format.intersect(required.format)
//        if (intersection.size() == 0) {
//            return false
//        }
//        intersection = annotations.intersect(required.annotations)
//        if (intersection.size() != required.annotations.size()) {
//            return false
//        }
//        return true
//    }

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

    boolean satisfies(IOSpecification required) {
//        return this.encoding == required.encoding &&
//                subsumes(this.language, required.language) &&
//                subsumes(this.format, required.format) &&
//                subsumes(required.annotations, this.annotations)
        if (required.encoding && this.encoding != required.encoding) {
            return false;
        }
        return subsumes(this.language, required.language) &&
                subsumes(this.format, required.format) &&
                subsumes(required.annotations, this.annotations)
    }

    private <T> boolean subsumes(String type, List<T> list1, List<T> list2) {
        if (!subsumes(list1, list2)) {
            println "Subsumes failed for $type"
            return false
        }
        return true
    }

    private <T> boolean subsumes(List<T> list1, List<T> list2)
    {
        Iterator<T> it = list1.iterator();
        while (it.hasNext()) {
            T t = it.next()
            if (!list2.contains(t)) {
                println "Required does not contain $t"
                return false;
            }
        }
        return true;
    }

}
