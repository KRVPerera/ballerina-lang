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
package org.ballerinalang.bindgen.model;

import org.apache.commons.lang3.StringUtils;
import org.ballerinalang.bindgen.utils.BindgenEnv;

import java.lang.reflect.Field;

import static org.ballerinalang.bindgen.utils.BindgenConstants.ACCESS_FIELD_INTEROP_TYPE;
import static org.ballerinalang.bindgen.utils.BindgenConstants.BALLERINA_STRING;
import static org.ballerinalang.bindgen.utils.BindgenConstants.MUTATE_FIELD_INTEROP_TYPE;
import static org.ballerinalang.bindgen.utils.BindgenUtils.getBallerinaHandleType;
import static org.ballerinalang.bindgen.utils.BindgenUtils.getBallerinaParamType;
import static org.ballerinalang.bindgen.utils.BindgenUtils.getJavaType;
import static org.ballerinalang.bindgen.utils.BindgenUtils.isStaticField;

/**
 * Class for storing details pertaining to a specific Java field used for Ballerina bridge code generation.
 *
 * @since 1.2.0
 */
public class JField extends BFunction {

    private JClass jClass;
    private String fieldName;
    private String fieldType;
    private String interopType;
    private String externalType;
    private String returnTypeJava;
    private String fieldMethodName;

    private boolean isArray;
    private boolean isStatic;
    private boolean isString;
    private boolean isObject = true;
    private boolean isSetter = false;
    private boolean returnError = false;
    private boolean javaArraysModule = false;

    private JParameter fieldObj;

    JField(Field field, BFunction.BFunctionKind fieldKind, BindgenEnv env, JClass jClass) {
        super(fieldKind, env);
        this.jClass = jClass;
        Class type = field.getType();
        fieldType = getBallerinaParamType(type);
        externalType = getBallerinaHandleType(type);
        isStatic = isStaticField(field);
        super.setStatic(isStatic);
        fieldName = field.getName();
        fieldObj = new JParameter(type, field.getDeclaringClass(), env);
        fieldObj.setHasNext(false);

        if (type.isPrimitive() || type.equals(String.class)) {
            isObject = false;
        }
        if (fieldType.equals(BALLERINA_STRING)) {
            isString = true;
        }
        if (type.isArray()) {
            isArray = true;
            returnError = true;
            if (!type.getComponentType().isPrimitive()) {
                isObject = false;
            }
            javaArraysModule = true;
        }

        if (fieldKind == BFunctionKind.FIELD_GET) {
            fieldMethodName = "get" + StringUtils.capitalize(fieldName);
            interopType = ACCESS_FIELD_INTEROP_TYPE;
            returnTypeJava = getJavaType(type);
        } else if (fieldKind == BFunctionKind.FIELD_SET) {
            fieldMethodName = "set" + StringUtils.capitalize(fieldName);
            interopType = MUTATE_FIELD_INTEROP_TYPE;
            isSetter = true;
        }
        setExternalFunctionName(field.getDeclaringClass().getName().replace(".", "_")
                .replace("$", "_") + "_" + fieldMethodName);

        if (isStatic) {
            super.setFunctionName(jClass.getShortClassName() + "_" + fieldMethodName);
        } else {
            super.setFunctionName(fieldMethodName);
        }
    }

    public boolean isString() {
        return isString;
    }

    public String getExternalType() {
        return externalType;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public boolean isSetter() {
        return isSetter;
    }

    public String getFieldName() {
        return fieldName;
    }

    boolean requireJavaArrays() {
        return javaArraysModule;
    }

    public JParameter getFieldObj() {
        return fieldObj;
    }
}
