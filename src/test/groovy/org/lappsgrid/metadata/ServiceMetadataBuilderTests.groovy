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
}
