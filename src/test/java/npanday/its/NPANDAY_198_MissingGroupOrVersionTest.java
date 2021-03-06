package npanday.its;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;

import org.apache.maven.it.Verifier;

public class NPANDAY_198_MissingGroupOrVersionTest
    extends AbstractNPandayIntegrationTestCase
{
    public NPANDAY_198_MissingGroupOrVersionTest()
    {
        super( "[1.1,)" );
    }

    public void testMissingGroupIdAndVersionShouldBeInherited()
        throws Exception
    {
        clearRdfCache();

        Verifier verifier = getDefaultVerifier();
        String testDir = verifier.getBasedir();
        verifier.executeGoal( "package" );
        verifier.assertFilePresent(
            new File( testDir, getAssemblyFile( "NPanday.ITs11579", "1.0-SNAPSHOT", "dll" ) ).getAbsolutePath() );
        verifier.assertFilePresent(
            new File( testDir, getAssemblyFile( "NPanday.ITs11579-test", "1.0-SNAPSHOT", "dll" ) ).getAbsolutePath() );
        verifier.assertFilePresent(
            new File( testDir, "target/test-assemblies/NPanday.ITs11579.dll" ).getAbsolutePath() );
        verifier.assertFilePresent(
            new File( testDir, "target/test-assemblies/NPanday.ITs11579-test.dll" ).getAbsolutePath() );
        verifier.assertFilePresent(
            new File( testDir, "target/test-assemblies/NPanday11579Dependency.dll" ).getAbsolutePath() );
        verifier.verifyErrorFreeLog();
    }
}
