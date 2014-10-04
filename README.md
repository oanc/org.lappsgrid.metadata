org.lappsgrid.metadata
======================

Classes used to read and write LAPPS metadata.

<b color='red'>Note:</b> currently these classes reside in the package `org.lappsgrid.experimental.lappsgrid`. They will be
moved into the `org.lappsgrid.metadata` package after review.

## Maven

```xml
<groupId>org.lappsgrid.experimental</groupId>
<artifactId>metadata</artifactId>
<version>1.0.0-SNAPSHOT</version>
```

## Examples

### Creating and Writing Metadata

```java
ServiceMetadata metadata = new ServiceMetaData();
metadata.setVersion("1.0.0");
metadata.setVendor("http://www.anc.org");
metadata.setAllows("http://vocab.lappsgrid.org/ns/usage#any");
metadata.setLicense("http://vocab.lappsgrid.org/ns/license/apache-2.0");
IOSpecification io = metadata.getRequires();
it.getFormat().add(
io.getEncoding().add("UTF-8");
io.getAnnotations().add("http://vocab.lappsgrid.org/Token");
io = metadata.getProduces();
io.getEncoding().add("UTF-8");
io.getAnnotations().add("http://vocab.lappsgrid.org/Sentence")"

System.out.println(metadata.toPrettyJson());
```

### Reading Metadata

```java
File file = ... /* The file containing the metadata in JSON. */
ServiceMetadata metadata = new ServiceMetadata(file);
System.out.println(metadata.getVendor());
```
These is also a constructor that accepts a String object containing the JSON
```java
String json = "{ ... }";
ServiceMetadata metadata = new ServiceMetadata(json);
System.out.println(metadata.getVendor());
```
