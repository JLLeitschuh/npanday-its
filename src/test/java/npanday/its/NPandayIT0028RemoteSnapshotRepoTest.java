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

import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;

import java.io.File;

public class NPandayIT0028RemoteSnapshotRepoTest
    extends AbstractNPandayIntegrationTestCase
{
    public NPandayIT0028RemoteSnapshotRepoTest() throws VerificationException, InvalidVersionSpecificationException {
        super("[1.0.2,)");
        skipIfMavenVersion( "[3.0,)" );
    }

    public void testSnapDeploymentRemoteRepoNotUnique()
        throws Exception
    {
        Verifier verifier = getDefaultVerifier();
        String testDir = verifier.getBasedir();
        verifier.executeGoal( "deploy" );
        verifier.assertFilePresent(
            new File( testDir, getAssemblyFile( "NPandayIT0028", "1.0.0.0", "dll" ) ).getAbsolutePath() );
        String path =
            "target/remoteSnapshotRepo/snapshots/NPandayIT0028/NPandayIT0028/1.0-SNAPSHOT/NPandayIT0028-1.0-SNAPSHOT";
        verifier.assertFilePresent( new File( testDir, path + ".dll" ).getAbsolutePath() );
        verifier.assertFilePresent(new File(testDir, path + ".pom").getAbsolutePath());
        verifier.verifyErrorFreeLog();
    }
}
