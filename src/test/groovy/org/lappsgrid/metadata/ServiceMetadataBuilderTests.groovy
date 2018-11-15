/*
 * Copyright (C) 2017 The Language Applications Grid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.lappsgrid.metadata

import org.junit.Test
import org.lappsgrid.serialization.Serializer

import static org.lappsgrid.discriminator.Discriminators.*

/**
 * @author Keith Suderman
 */
class ServiceMetadataBuilderTests {

    @Test
    void testProducesAddArray() {
        ServiceMetadata md = new ServiceMetadataBuilder()
            .produces(['t1', 't2', 't3'] as String[])
            .build()

        assert 3 == md.produces.annotations.size();
        assert 't1' == md.produces.annotations[0]
        assert 't2' == md.produces.annotations[1]
        assert 't3' == md.produces.annotations[2]
    }

    @Test
    void testProducesAddVarargs() {
        ServiceMetadata md = new ServiceMetadataBuilder()
                .produces('t1', 't2', 't3')
                .build()

        assert 3 == md.produces.annotations.size();
        assert 't1' == md.produces.annotations[0]
        assert 't2' == md.produces.annotations[1]
        assert 't3' == md.produces.annotations[2]

    }

    @Test
    void testRequiresAddArray() {
        ServiceMetadata md = new ServiceMetadataBuilder()
                .requires(['t1', 't2', 't3'] as String[])
                .build()

        assert 3 == md.requires.annotations.size();
        assert 't1' == md.requires.annotations[0]
        assert 't2' == md.requires.annotations[1]
        assert 't3' == md.requires.annotations[2]
    }

    @Test
    void testRequiresAddVarargs() {
        ServiceMetadata md = new ServiceMetadataBuilder()
                .requires('t1', 't2', 't3')
                .build()

        assert 3 == md.requires.annotations.size();
        assert 't1' == md.requires.annotations[0]
        assert 't2' == md.requires.annotations[1]
        assert 't3' == md.requires.annotations[2]

    }

    @Test
    void requiresTagSetByAlias() {
        ServiceMetadata md = new ServiceMetadataBuilder()
                .requireTagSet(Uri.POS, Alias.TAGS_POS_PENNTB)
                .build()

        assert 1 == md.requires.tagSets.size()
        assert md.requires.tagSets.containsKey(Uri.POS)
        assert Uri.TAGS_POS_PENNTB == md.requires.tagSets.get(Uri.POS)
        assert 0 == md.produces.tagSets.size()
    }

    @Test
    void requiresTagSetByUri() {
        ServiceMetadata md = new ServiceMetadataBuilder()
                .requireTagSet(Uri.POS, Uri.TAGS_POS_PENNTB)
                .build()

        assert 1 == md.requires.tagSets.size()
        assert md.requires.tagSets.containsKey(Uri.POS)
        assert Uri.TAGS_POS_PENNTB == md.requires.tagSets.get(Uri.POS)
        assert 0 == md.produces.tagSets.size()
    }

    @Test
    void producesTagSets() {
        ServiceMetadata md = new ServiceMetadataBuilder()
                .produceTagSet(Uri.POS, Alias.TAGS_POS_PENNTB)
                .produceTagSet(Uri.NE, Alias.TAGS_NER_OPENNLP)
                .build()

        assert 0 == md.requires.tagSets.size()
        assert 2 == md.produces.tagSets.size()
        assert Uri.TAGS_POS_PENNTB == md.produces.tagSets.get(Uri.POS)
        assert Uri.TAGS_NER_OPENNLP == md.produces.tagSets.get(Uri.NE)

        println Serializer.toPrettyJson(md)
    }
}
