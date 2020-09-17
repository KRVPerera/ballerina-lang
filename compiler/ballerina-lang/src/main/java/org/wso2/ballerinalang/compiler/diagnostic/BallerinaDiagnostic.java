/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.wso2.ballerinalang.compiler.diagnostic;

import io.ballerina.tools.diagnostics.Diagnostic;
import io.ballerina.tools.diagnostics.DiagnosticInfo;
import io.ballerina.tools.diagnostics.Location;

/**
 * Represent a diagnostic in the ballerina compiler front-end. A diagnostic can be a semantic
 * error, a warning or a info.
 *
 * @since 2.0.0
 */
public class BallerinaDiagnostic extends Diagnostic implements Comparable<BallerinaDiagnostic> {

    private Location location;
    private String msg;
    private DiagnosticInfo diagnosticInfo;

    public BallerinaDiagnostic(Location location, String msg, DiagnosticInfo diagnosticInfo) {
        this.location = location;
        this.msg = msg;
        this.diagnosticInfo = diagnosticInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location location() {
        return location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiagnosticInfo diagnosticInfo() {
        return diagnosticInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String message() {
        return msg;
    }

    @Override
    public int compareTo(BallerinaDiagnostic otherDiagnostic) {
        int value = ((BallerinaDiagnosticLocation) this.location)
                .compareTo((BallerinaDiagnosticLocation) otherDiagnostic.location);
        if (value == 0) {
            value = message().compareTo(otherDiagnostic.message());
            if (value == 0) {
                if (diagnosticInfo.hashCode() == otherDiagnostic.hashCode()) {
                    return 0;
                } else {
                    return 1;
                }
            }
            return value;
        }
        return value;
    }

    public String toString() {
        return diagnosticInfo.severity() + ": " + location.toString() + ": " + msg;
    }
}
