package npanday.its;

/*
 * Copyright 2010
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;

import java.io.File;

public class NPandayIT0004NUnitTestVerificationTest
    extends AbstractNPandayIntegrationTestCase
{
    public NPandayIT0004NUnitTestVerificationTest()
    {
        super( "[1.0.2,)" );
    }

    public void testNUnitTestsRun()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/NPandayIT0004NUnitTestVerificationTest" );
        Verifier verifier = getVerifier( testDir );
        verifier.executeGoal( "install" );

        verifier.assertFilePresent(
            new File( testDir, getAssemblyFile( "NPandayIT0004-test", "1.0.0.0", "dll" ) ).getAbsolutePath() );

        verifier.assertFilePresent(
            new File( testDir, "target/nunit-reports/TEST-NPandayIT0004-1.0-SNAPSHOT.xml" ).getAbsolutePath() );

        verifier.assertFilePresent(
            new File( testDir, getTestAssemblyFile( "NUnit.Framework", "2.2.8.0", "dll" ) ).getAbsolutePath() );

        verifier.verifyErrorFreeLog();
        verifier.resetStreams();
    }
}
