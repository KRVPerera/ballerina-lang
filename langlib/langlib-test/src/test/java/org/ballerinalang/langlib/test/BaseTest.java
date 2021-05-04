/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.ballerinalang.langlib.test;

import org.ballerinalang.test.CompileResult;
import org.testng.annotations.AfterClass;

import java.lang.reflect.Field;

/**
 * BaseTest case for cleaning `CompileResult` object.
 *
 * @since 2.0.0
 */
public class BaseTest {

    protected boolean skipPrivateFields = true;
    protected boolean skipFinalFields = false;

    @AfterClass(alwaysRun = true)
    public void destroy() {

        Class<?> compileResultClass = CompileResult.class;
        Class<?> compileResultUtilClass = org.ballerinalang.test.util.CompileResult.class;

        for (Field field : getClass().getDeclaredFields()) {
            /*
                To collect execution data JaCoCo instruments the classes under test which adds two members to the
                classes: A private static field $jacocoData and a private static method $jacocoInit(). Both members are
                marked as synthetic.
             */
            if (field.isSynthetic()) {
                continue;
            }
            var fieldType = field.getType();
            if (fieldType.isPrimitive()) {
                continue;
            }

            var fieldModifiers = field.getModifiers();
            boolean isPrivate = java.lang.reflect.Modifier.isPrivate(fieldModifiers);
            boolean isFinal = java.lang.reflect.Modifier.isFinal(fieldModifiers);

            boolean isCompileResult = fieldType.isAssignableFrom(compileResultClass) ||
                    fieldType.isAssignableFrom(compileResultUtilClass);

            if (isCompileResult) {
                /*
                    make sure to forcefully ask users to change `CompileResult` field modifiers to accessible ones
                 */
                if (!field.canAccess(this)) {
                    field.setAccessible(true);
                }
                if (isPrivate) {
                    throw new PrivateFieldException(field);
                }
                if (isFinal) {
                    throw new FinalFieldException(field);
                }
            }

            if (isPrivate && skipPrivateFields) {
                continue;
            }

            if (isFinal && skipFinalFields) {
                continue;
            }

            try {
                if (field.get(this) != null) {
                    field.set(this, null);
                }
            } catch (IllegalArgumentException e) {
                throw new PrivateFieldException(field, e);
            } catch (IllegalAccessException e) {
                throw new FinalFieldException(field, e);
            }
        }
    }
}
