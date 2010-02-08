package npanday.its;

/*
 * Copyright 2010
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
 */

import java.io.File;

import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;

public class NPandayIT0002Test
    extends AbstractNPandayIntegrationTestCase
{
    public NPandayIT0002Test()
    {
        super( "[1.1,)" );
    }

    public void testNetModuleDependency()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/NPandayITNetModuleDependency" );
        Verifier verifier = getVerifier( new File( testDir, "dependency" ) );
        verifier.executeGoal( "install" );
        verifier.assertFilePresent(
            new File( testDir, getAssemblyFile( "dependency", "1.0.0.0", "dll", null ) ).getAbsolutePath() );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();

        verifier = getVerifier( new File( testDir, "library" ) );
        verifier.executeGoal( "install" );
        verifier.assertFilePresent(
            new File( testDir, getAssemblyFile( "dependency", "1.0.0.0", "netmodule", null ) ).getAbsolutePath() );
        verifier.assertFilePresent(
            new File( testDir, getAssemblyFile( "library", "1.0.0.0", "dll", null ) ).getAbsolutePath() );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();
    }
}
