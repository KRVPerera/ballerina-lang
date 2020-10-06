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
package org.ballerinalang.jvm.types;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represent Ballerina distinct types type-ids in the runtime.
 *
 * @since 2.0
 */
public class BTypeIdSet {
    Set<TypeId> ids;

    public BTypeIdSet() {
        this.ids = new HashSet<>();
    }

    public void add(BPackage pkg, String name, boolean isPrimary) {
        ids.add(new TypeId(pkg, name, isPrimary));
    }

    public boolean containsAll(BTypeIdSet other) {
        if (other == null) {
            return true;
        }

        return ids.containsAll(other.ids);
    }

    /**
     * Represent Ballerina distinct type id.
     *
     * @since 2.0
     */
    public static class TypeId {
        final BPackage pkg;
        final String name;
        final boolean isPrimary;

        public TypeId(BPackage pkg, String name, boolean isPrimary) {
            this.pkg = pkg;
            this.name = name;
            this.isPrimary = isPrimary;
        }

        @Override
        public int hashCode() {
            return Objects.hash(pkg, name, isPrimary);
        }

        // Equals operator does not take `this.isPrimary`
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }

            if (obj instanceof TypeId) {
                TypeId that = (TypeId) obj;
                return this.name.equals(that.name) && this.pkg.equals(that.pkg);
            }
            return false;
        }
    }
}
