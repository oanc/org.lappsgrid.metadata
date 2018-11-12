org.lappsgrid.metadata
======================

### Build Status

[![Master Status](http://grid.anc.org:9080/travis/svg/lapps/org.lappsgrid.metadata?branch=master)](https://travis-ci.org/lapps/org.lappsgrid.metadata) [![Develop Status](http://grid.anc.org:9080/travis/svg/lapps/org.lappsgrid.metadata?branch=develop)](https://travis-ci.org/lapps/org.lappsgrid.metadata)

### Deployment

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.lappsgrid/metadata/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/org.lappsgrid/metadata)

## Examples

### Creating and Writing Metadata

```
ServiceMetadata metadata = new ServiceMetaData();
metadata.setVersion("1.0.0");
metadata.setVendor("http://www.anc.org");
metadata.setAllows("http://vocab.lappsgrid.org/ns/usage#any");
metadata.setLicense("http://vocab.lappsgrid.org/ns/license/apache-2.0");
IOSpecification requires = metadata.getRequires();
requires.addFormat("http://vocab.lappsgrid.org/ns/media/xml#gate");
requires.setEncoding("UTF-8");
requires.addAnnotation("http://vocab.lappsgrid.org/Token");
IOSpecification produces = metadata.getProduces();
produces.addFormat("http://vocab.lappsgrid.org/ns/media/xml#gate");
produces.setEncoding("UTF-8");
produces.addAnnotation("http://vocab.lappsgrid.org/Sentence")"

System.out.println(metadata.toPrettyJson());
```

### Builders

The classes `ServiceMetadataBuilder` and `DataSourceMetaDataBuilder` provide a Fluent API for constructing ServiceMetadata and DataSourceMetadata object respectively.

```groovy
ServiceMetadata md = new ServiceMetadataBuilder()
    .licence(Uri.APACHE2)
    .version("1.0.0")
    .requires(URI.TOKEN)
    .produces(Uri.SENTENCE)
    .build();

```
### Reading Metadata

```
File file = ... /* The file containing the metadata in JSON. */
ServiceMetadata metadata = new ServiceMetadata(file);
System.out.println(metadata.getVendor());
```
There is also a constructor that accepts a String object containing the JSON
```
String json = "{ ... }";
ServiceMetadata metadata = new ServiceMetadata(json);
System.out.println(metadata.getVendor());
```
