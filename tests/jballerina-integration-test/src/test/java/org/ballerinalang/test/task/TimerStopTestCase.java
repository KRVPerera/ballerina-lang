/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.ballerinalang.test.task;

import org.ballerinalang.test.context.BallerinaTestException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * This is the test class to test ballerina task scheduler stop function.
 */
@Test(groups = "task-test")
public class TimerStopTestCase extends TaskBaseTest {
    @BeforeClass
    public void setup() throws BallerinaTestException {
        super.setup();
        int[] requiredPorts = new int[]{15006};
        serverInstance.startServer(balFile, "timerstop", requiredPorts);
    }

    @Test(description = "Test for task scheduler stop function")
    public void testTaskSchedulerStop() {
        String message = "Task successfully stopped";
        assertTest(10000, "http://localhost:15006/getTimerStopResult", message);
    }

    @AfterClass
    public void cleanup() throws BallerinaTestException {
        super.cleanup();
    }
}
