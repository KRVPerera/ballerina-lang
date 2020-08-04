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
package io.ballerinalang.compiler.internal.parser.tree;

import io.ballerinalang.compiler.syntax.tree.Node;
import io.ballerinalang.compiler.syntax.tree.NonTerminalNode;
import io.ballerinalang.compiler.syntax.tree.ObjectConstructorExpressionNode;
import io.ballerinalang.compiler.syntax.tree.SyntaxKind;

import java.util.Collection;
import java.util.Collections;

/**
 * This is a generated internal syntax tree node.
 *
 * @since 2.0.0
 */
public class STObjectConstructorExpressionNode extends STExpressionNode {
    public final STNode metadata;
    public final STNode objectTypeQualifier;
    public final STNode objectKeyword;
    public final STNode typeDescriptor;
    public final STNode openBracket;
    public final STNode members;
    public final STNode closeBracket;

    STObjectConstructorExpressionNode(
            STNode metadata,
            STNode objectTypeQualifier,
            STNode objectKeyword,
            STNode typeDescriptor,
            STNode openBracket,
            STNode members,
            STNode closeBracket) {
        this(
                metadata,
                objectTypeQualifier,
                objectKeyword,
                typeDescriptor,
                openBracket,
                members,
                closeBracket,
                Collections.emptyList());
    }

    STObjectConstructorExpressionNode(
            STNode metadata,
            STNode objectTypeQualifier,
            STNode objectKeyword,
            STNode typeDescriptor,
            STNode openBracket,
            STNode members,
            STNode closeBracket,
            Collection<STNodeDiagnostic> diagnostics) {
        super(SyntaxKind.OBJECT_CONSTRUCTOR, diagnostics);
        this.metadata = metadata;
        this.objectTypeQualifier = objectTypeQualifier;
        this.objectKeyword = objectKeyword;
        this.typeDescriptor = typeDescriptor;
        this.openBracket = openBracket;
        this.members = members;
        this.closeBracket = closeBracket;

        addChildren(
                metadata,
                objectTypeQualifier,
                objectKeyword,
                typeDescriptor,
                openBracket,
                members,
                closeBracket);
    }

    public STNode modifyWith(Collection<STNodeDiagnostic> diagnostics) {
        return new STObjectConstructorExpressionNode(
                this.metadata,
                this.objectTypeQualifier,
                this.objectKeyword,
                this.typeDescriptor,
                this.openBracket,
                this.members,
                this.closeBracket,
                diagnostics);
    }

    public STObjectConstructorExpressionNode modify(
            STNode metadata,
            STNode objectTypeQualifier,
            STNode objectKeyword,
            STNode typeDescriptor,
            STNode openBracket,
            STNode members,
            STNode closeBracket) {
        if (checkForReferenceEquality(
                metadata,
                objectTypeQualifier,
                objectKeyword,
                typeDescriptor,
                openBracket,
                members,
                closeBracket)) {
            return this;
        }

        return new STObjectConstructorExpressionNode(
                metadata,
                objectTypeQualifier,
                objectKeyword,
                typeDescriptor,
                openBracket,
                members,
                closeBracket,
                diagnostics);
    }

    public Node createFacade(int position, NonTerminalNode parent) {
        return new ObjectConstructorExpressionNode(this, position, parent);
    }

    @Override
    public void accept(STNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T apply(STNodeTransformer<T> transformer) {
        return transformer.transform(this);
    }
}
