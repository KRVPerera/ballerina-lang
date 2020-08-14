// Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
//
// WSO2 Inc. licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.
// You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

type MO object {
    int x = 0;
};

type MOO object {
    *MO;
    int n = 0;

    public function init() {
        self.x = 5;
    }
};

var objCreatedViaConstructor = object MOO {
    int n = 20;

    public function init() {
        self.x = 4;
    }
};

function testObjectCreationViaObjectConstructor() {
    assertTrue(objCreatedViaConstructor.n == 20);
    assertTrue(objCreatedViaConstructor.x == 4);
    assertTrue(false);
}


const ASSERTION_ERROR_REASON = "AssertionError";

function assertTrue(any|error actual) {
    if actual is boolean && actual {
        return;
    }

    panic error(ASSERTION_ERROR_REASON,
                message = "expected 'true', found '" + actual.toString () + "'");
}

function assertFalse(any|error actual) {
    if actual is boolean && !actual {
        return;
    }

    panic error(ASSERTION_ERROR_REASON,
                message = "expected 'false', found '" + actual.toString () + "'");
}

function assertValueEquality(anydata|error expected, anydata|error actual) {
    if expected == actual {
        return;
    }

    panic error(ASSERTION_ERROR_REASON,
                message = "expected '" + expected.toString() + "', found '" + actual.toString () + "'");
}