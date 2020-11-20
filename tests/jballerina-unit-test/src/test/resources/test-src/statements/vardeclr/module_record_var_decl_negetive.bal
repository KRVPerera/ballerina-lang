//  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
//
//  WSO2 Inc. licenses this file to you under the Apache License,
//  Version 2.0 (the "License"); you may not use this file except
//  in compliance with the License.
//  You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

type Person record {|
    string name;
    boolean married;
|};
string Fname = "myfirst name";
// Redeclare Fname inside record variable
Person {name:Fname, married:Married} = {name:"Jhone", married:true};
// Redeclare Married which is already declared inside record
boolean Married = false;
public function testBasic() returns [string, boolean] {
    return [Fname, Married];
}

// Invalid field name age which is not it Person type
Person {name:Fname2, married:Married2, age:Age2} = {name:"Jhone", married:true};
