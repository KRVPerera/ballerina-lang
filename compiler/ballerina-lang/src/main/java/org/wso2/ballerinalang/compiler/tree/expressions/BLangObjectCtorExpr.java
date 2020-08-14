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

package org.wso2.ballerinalang.compiler.tree.expressions;

import org.ballerinalang.jvm.util.exceptions.BallerinaException;
import org.ballerinalang.model.tree.NodeKind;
import org.ballerinalang.model.tree.SimpleVariableNode;
import org.ballerinalang.model.tree.types.StructureTypeNode;
import org.ballerinalang.model.tree.types.TypeNode;
import org.wso2.ballerinalang.compiler.tree.BLangFunction;
import org.wso2.ballerinalang.compiler.tree.BLangNodeVisitor;
import org.wso2.ballerinalang.compiler.tree.BLangSimpleVariable;
import org.wso2.ballerinalang.compiler.tree.types.BLangObjectTypeNode;
import org.wso2.ballerinalang.compiler.tree.types.BLangType;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the object-constructor-expr.
 *
 * @since slp3
 */
public class BLangObjectCtorExpr extends BLangExpression implements StructureTypeNode {

    public BLangObjectTypeNode objectTypeNode;
    public BLangFunction initFunction;
    public BLangTypeInit typeInit;
    public BLangType referenceType;
    public boolean desugarPhase;

    public BLangObjectCtorExpr(BLangObjectTypeNode objectTypeNode) {
        super();
        this.objectTypeNode = objectTypeNode;
        desugarPhase = false;
    }

    @Override
    public void accept(BLangNodeVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Returns the kind of this node.
     *
     * @return the kind of this node.
     */
    @Override
    public NodeKind getKind() {

        return NodeKind.OBJECT_CTOR_EXPRESSION;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("OCE {\n");
        sb.append(this.objectTypeNode.toString());
        sb.append("\nDesugared :  ");
        sb.append(this.desugarPhase);
        sb.append("};\n");
        return sb.toString();
    }

    @Override
    public boolean getIsAnonymous() {
        return this.objectTypeNode.getIsAnonymous();
    }

    @Override
    public boolean getIsLocal() {
        return this.objectTypeNode.getIsLocal();
    }

    @Override
    public List<? extends SimpleVariableNode> getFields() {
        return objectTypeNode.fields;
    }

    @Override
    public void addField(SimpleVariableNode field) {
        this.objectTypeNode.fields.add((BLangSimpleVariable) field);
    }

    /**
     * Get the list of types that are referenced by this type.
     *
     * @return list of types that are referenced by this type.
     */
    @Override
    public List<? extends TypeNode> getTypeReferences() {
        List<BLangType> typeRefs = new ArrayList<>();
        typeRefs.add(this.referenceType);
        return typeRefs;
    }

    /**
     * Add a type reference.
     *
     * @param type Type that is referenced by this type.
     */
    @Override
    public void addTypeReference(TypeNode type) {
        if (this.referenceType == null) {
            this.referenceType = (BLangType) type;
            this.objectTypeNode.addTypeReference(type);
            return;
        }
        throw new BallerinaException("object-constructor-expr can only have one type-reference");
    }

    /**
     * Returns whether this {@code TypeNode} is a nullable type.
     * <p>
     * e.g. T?
     *
     * @return whether this {@code TypeNode} is a nullable type.
     */
    @Override
    public boolean isNullable() {
        return this.objectTypeNode.isNullable();
    }

    /**
     * Returns whether this {@code TypeNode} is a grouped type.
     * e.g. (T)
     *
     * @return whether this {@code TypeNode} is a grouped type.
     */
    @Override
    public boolean isGrouped() {
        return this.objectTypeNode.isGrouped();
    }
}
