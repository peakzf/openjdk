/*
 * Copyright (c) 2017, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package gc;

import static jdk.test.lib.Asserts.*;
import gc.testlibrary.PerfCounters;


/* @test TestGenerationPerfCounter
 * @bug 8080345
 * @requires vm.gc=="null"
 * @library /test/lib /
 * @summary Tests that the sun.gc.policy.generations returns 2 for all GCs.
 * @modules java.base/jdk.internal.misc
 *          java.compiler
 *          java.management/sun.management
 *          jdk.internal.jvmstat/sun.jvmstat.monitor
 * @run main/othervm -XX:+UsePerfData -XX:+UseSerialGC gc.TestGenerationPerfCounter
 * @run main/othervm -XX:+UsePerfData -XX:+UseParallelGC gc.TestGenerationPerfCounter
 * @run main/othervm -XX:+UsePerfData -XX:+UseG1GC gc.TestGenerationPerfCounter
 */

/* @test TestGenerationPerfCounterCMS
 * @bug 8080345
 * @comment Graal does not support CMS
 * @requires vm.gc=="null" & !vm.graal.enabled
 * @library /test/lib /
 * @summary Tests that the sun.gc.policy.generations returns 2 for all GCs.
 * @modules java.base/jdk.internal.misc
 *          java.compiler
 *          java.management/sun.management
 *          jdk.internal.jvmstat/sun.jvmstat.monitor
 * @run main/othervm -XX:+UsePerfData -XX:+UseConcMarkSweepGC gc.TestGenerationPerfCounter
 */

public class TestGenerationPerfCounter {
    public static void main(String[] args) throws Exception {
        long numGenerations =
            PerfCounters.findByName("sun.gc.policy.generations").longValue();
        assertEQ(numGenerations, 2L);
    }
}
