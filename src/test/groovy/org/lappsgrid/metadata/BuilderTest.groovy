package org.lappsgrid.metadata

import static org.junit.Assert.*
import org.junit.Test
import static org.lappsgrid.discriminator.Discriminators.Uri

/**
 * @author Keith Suderman
 */
class BuilderTest {

    @Test
    void testTokenizerSplitter() {
        ServiceMetadata tokenizer = new ServiceMetadataBuilder()
            .allow(Uri.ANY)
            .name("GATE Tokenizer")
            .vendor("http://www.anc.org")
            .version("2.0.0")
            .requireEncoding("UTF-8")
            .produceEncoding("UTF-8")
            .requireFormat(Uri.TEXT)
            .requireFormat(Uri.XML)
            .requireFormat(Uri.GATE)
            .produceFormat(Uri.GATE)
            .produce(Uri.TOKEN)
            .build();
        ServiceMetadata splitter = new ServiceMetadataBuilder()
            .allow(Uri.ANY)
            .name("GATE SentenceSplitter")
            .vendor("http://www.anc.org")
            .version("2.0.0")
            .requireFormat(Uri.GATE)
            .produceFormat(Uri.GATE)
            .require(Uri.TOKEN)
            .produce(Uri.SENTENCE).build();

        assertTrue tokenizer.produces.satisfies(splitter.requires)
    }
}
