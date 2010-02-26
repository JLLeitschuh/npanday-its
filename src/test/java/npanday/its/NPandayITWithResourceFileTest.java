package npanday.its;

/*
 * Copyright 2009
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

public class NPandayITWithResourceFileTest
    extends AbstractNPandayIntegrationTestCase
{
    public NPandayITWithResourceFileTest()
    {
        super( "(1.0,)" ); // 1.0.1+
    }

    public void testWithResourceFile()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/WithResourceFile" );
        Verifier verifier = getVerifier( testDir );
        verifier.executeGoal( "test" );
        String assembly = new File( testDir, "ClassLibrary1/" +
            getAssemblyFile( "ClassLibrary1", "1.0.0", "dll" ) ).getAbsolutePath();
        verifier.assertFilePresent( assembly );
        String path = "ClassLibrary1/target/assembly-resources/resource/ClassLibrary1.Resource1.resources";
        verifier.assertFilePresent( new File( testDir, path ).getAbsolutePath() );
        assertResourcePresent( assembly, "Resource1.resources" );
        assertClassPresent( assembly, "ClassLibrary1.Resource1" );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();
    }
}
