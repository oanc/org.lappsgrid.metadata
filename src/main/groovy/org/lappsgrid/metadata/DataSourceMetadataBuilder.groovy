package org.lappsgrid.metadata

import org.lappsgrid.discriminator.Discriminators
import org.lappsgrid.serialization.Data

/** Implements the Builder pattern for generating {@link DataSourceMetadata}.
 * <code>
 *     <pre>
 *         DataSourceMetadata md = new DataSourceMetadataBuilder()
 *                                  .version("2.0.0")
 *                                  .vendor("http://example.com")
 *                                  .produces("http://vocab.lappsgrid.org/Token")
 *                                  .build();
 *     </pre>
 * </code>
 * The {@link DataSourceMetadataBuilder#toString} method can be used to obtain the
 * JSON representation rather than the {@link DataSourceMetadata} object itself.
 *
 * @author Keith Suderman
 */
class DataSourceMetadataBuilder {
    private DataSourceMetadata metadata;

    public DataSourceMetadataBuilder() {
        metadata = new DataSourceMetadata()
    }

    DataSourceMetadataBuilder schema(String schema) {
        metadata.schema = schema
        return this
    }

    DataSourceMetadataBuilder name(String name) {
        metadata.name = name
        return this
    }

    DataSourceMetadataBuilder vendor(String vendor) {
        metadata.vendor = vendor
        return this
    }

    DataSourceMetadataBuilder version(String version) {
        metadata.version = version
        return this
    }

    DataSourceMetadataBuilder description(String description) {
        metadata.description = description
        return this
    }

    DataSourceMetadataBuilder allow(String allow) {
        metadata.allow = allow
        return this
    }

    DataSourceMetadataBuilder license(String license) {
        metadata.license = license
        return this
    }

    DataSourceMetadataBuilder language(String language) {
        metadata.addLanguage(language)
        return this
    }

    DataSourceMetadataBuilder format(String format) {
        metadata.addFormat(format)
        return this
    }

    DataSourceMetadataBuilder encoding(String encoding) {
        metadata.encoding = encoding
        return this
    }

    DataSourceMetadata build() {
        DataSourceMetadata result = metadata;
        metadata = null;
        return result
    }

    String toString() {
        Data<DataSourceMetadata> data = new Data<>(Discriminators.Uri.META, metadata)
        metadata = null;
        return data.asJson();
    }

}
