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

import org.lappsgrid.serialization.Data
import org.lappsgrid.serialization.Serializer
import static org.lappsgrid.discriminator.Discriminators.Uri

/** Implements the Builder pattern for generating {@link ServiceMetadata}.
 * <code>
 *     <pre>
 *         ServiceMetadata md = new ServiceMetadataBuilder()
 *                                  .version("2.0.0")
 *                                  .vendor("http://example.com")
 *                                  .produces("http://vocab.lappsgrid.org/Token")
 *                                  .build();
 *     </pre>
 * </code>
 * The {@link ServiceMetadataBuilder#toString} method can be used to obtain the
 * JSON representation rather than the {@link ServiceMetadata} object itself.
 *
 * @author Keith Suderman
 */
class ServiceMetadataBuilder {
    ServiceMetadata metadata

    ServiceMetadataBuilder() {
        metadata = new ServiceMetadata()
    }

    ServiceMetadataBuilder name(String name) {
        metadata.name = name
        return this
    }

    ServiceMetadataBuilder description(String description) {
        metadata.description = description
        return this
    }

    ServiceMetadataBuilder vendor(String vendor) {
        metadata.vendor = vendor
        return this
    }

    ServiceMetadataBuilder version(String version) {
        metadata.version = version
        return this
    }

    ServiceMetadataBuilder allow(String allow) {
        metadata.allow = allow
        return this
    }

    ServiceMetadataBuilder license(String license) {
        metadata.license = license
        return this
    }
    ServiceMetadataBuilder licenseDesc(String description) {
        metadata.licenseDesc = description
        return this
    }

    ServiceMetadataBuilder produce(String type) {
        metadata.produces.addAnnotation(type)
        return this
    }

    ServiceMetadataBuilder produces(String[] types) {
        metadata.produces.addAnnotations(types)
        return this
    }

    ServiceMetadataBuilder produceFormat(String format) {
        metadata.produces.addFormat(format)
        return this
    }

    ServiceMetadataBuilder produceFormat(List<String> formats) {
        metadata.produces.addFormats(formats)
        return this
    }

    ServiceMetadataBuilder produceFormats(String[] formats) {
        metadata.produces.addFormats(formats)
        return this
    }

    ServiceMetadataBuilder produceLanguage(String lang) {
        metadata.produces.addLanguage(lang)
        return this
    }

    ServiceMetadataBuilder produceLanguages(String[] langs) {
        metadata.produces.addLanguages(langs)
        return this
    }

    ServiceMetadataBuilder produceEncoding(String encoding) {
        metadata.produces.encoding = encoding
        return this
    }


    ServiceMetadataBuilder require(String type) {
        metadata.requires.addAnnotation(type)
        return this
    }

    ServiceMetadataBuilder requires(String[] types) {
        metadata.requires.addAnnotations(types)
        return this
    }

    ServiceMetadataBuilder requireFormat(String format) {
        metadata.requires.addFormat(format)
        return this
    }

    ServiceMetadataBuilder requireFormat(List<String> formats) {
        metadata.requires.addFormats(formats)
        return this
    }

    ServiceMetadataBuilder requireFormats(String[] formats) {
        metadata.requires.addFormats(formats)
        return this
    }

    ServiceMetadataBuilder requireLanguage(String lang) {
        metadata.requires.addLanguage(lang)
        return this
    }

    ServiceMetadataBuilder requireLanguages(String[] langs) {
        metadata.requires.addLanguages(langs)
        return this
    }

    ServiceMetadataBuilder requireEncoding(String encoding) {
        metadata.requires.encoding = encoding
        return this
    }

    ServiceMetadata build() {
        return metadata
    }

    String toString() {
        Data<ServiceMetadata> data = new Data<>(Uri.META, metadata)
        metadata = null
        return data.asPrettyJson()
    }
}
