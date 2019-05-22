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

package org.ballerinalang.stdlib.crypto.nativeimpl;

import org.ballerinalang.bre.Context;
import org.ballerinalang.bre.bvm.BlockingNativeCallableUnit;
import org.ballerinalang.jvm.Strand;
import org.ballerinalang.jvm.values.ArrayValue;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.model.values.BValueArray;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.stdlib.crypto.Constants;
import org.ballerinalang.stdlib.crypto.CryptoUtils;

/**
 * Extern function ballerina.crypto:encryptAesCbc.
 *
 * @since 0.990.4
 */
@BallerinaFunction(orgName = "ballerina", packageName = "crypto", functionName = "encryptAesCbc", isPublic = true)
public class EncryptAesCbc extends BlockingNativeCallableUnit {

    @Override
    public void execute(Context context) {
        BValue inputBValue = context.getRefArgument(0);
        byte[] input = ((BValueArray) inputBValue).getBytes();
        BValue keyBValue = context.getRefArgument(1);
        byte[] key = ((BValueArray) keyBValue).getBytes();
        BValue ivBValue = context.getRefArgument(2);
        byte[] iv = null;
        if (ivBValue != null) {
            iv = ((BValueArray) ivBValue).getBytes();
        }
        String padding = context.getRefArgument(3).stringValue();
        CryptoUtils.aesEncryptDecrypt(context, CryptoUtils.CipherMode.ENCRYPT, Constants.CBC, padding, key, input, iv,
                -1);
    }

    public static Object encryptAesCbc(Strand strand, Object padding, ArrayValue inputValue, ArrayValue keyValue,
                                       ArrayValue ivValue) {
        byte[] input = inputValue.getBytes();
        byte[] key = keyValue.getBytes();
        byte[] iv = null;
        if (ivValue != null) {
            iv = ivValue.getBytes();
        }
        return CryptoUtils.aesEncryptDecrypt(CryptoUtils.CipherMode.ENCRYPT, Constants.CBC, padding.toString(), key,
                                             input, iv, -1);
    }
}
