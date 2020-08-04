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
package io.ballerinalang.compiler.syntax.tree;

import io.ballerinalang.compiler.internal.parser.tree.STNode;

import java.util.Objects;
import java.util.Optional;

/**
 * This is a generated syntax tree node.
 *
 * @since 2.0.0
 */
public class ObjectConstructorExpressionNode extends ExpressionNode {

    public ObjectConstructorExpressionNode(STNode internalNode, int position, NonTerminalNode parent) {
        super(internalNode, position, parent);
    }

    public Optional<MetadataNode> metadata() {
        return optionalChildInBucket(0);
    }

    public Optional<Token> objectTypeQualifier() {
        return optionalChildInBucket(1);
    }

    public Token objectKeyword() {
        return childInBucket(2);
    }

    public Optional<TypeDescriptorNode> typeDescriptor() {
        return optionalChildInBucket(3);
    }

    public Token openBracket() {
        return childInBucket(4);
    }

    public NodeList<Node> members() {
        return new NodeList<>(childInBucket(5));
    }

    public Token closeBracket() {
        return childInBucket(6);
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T apply(NodeTransformer<T> visitor) {
        return visitor.transform(this);
    }

    @Override
    protected String[] childNames() {
        return new String[]{
                "metadata",
                "objectTypeQualifier",
                "objectKeyword",
                "typeDescriptor",
                "openBracket",
                "members",
                "closeBracket"};
    }

    public ObjectConstructorExpressionNode modify(
            MetadataNode metadata,
            Token objectTypeQualifier,
            Token objectKeyword,
            TypeDescriptorNode typeDescriptor,
            Token openBracket,
            NodeList<Node> members,
            Token closeBracket) {
        if (checkForReferenceEquality(
                metadata,
                objectTypeQualifier,
                objectKeyword,
                typeDescriptor,
                openBracket,
                members.underlyingListNode(),
                closeBracket)) {
            return this;
        }

        return NodeFactory.createObjectConstructorExpressionNode(
                metadata,
                objectTypeQualifier,
                objectKeyword,
                typeDescriptor,
                openBracket,
                members,
                closeBracket);
    }

    public ObjectConstructorExpressionNodeModifier modify() {
        return new ObjectConstructorExpressionNodeModifier(this);
    }

    /**
     * This is a generated tree node modifier utility.
     *
     * @since 2.0.0
     */
    public static class ObjectConstructorExpressionNodeModifier {
        private final ObjectConstructorExpressionNode oldNode;
        private MetadataNode metadata;
        private Token objectTypeQualifier;
        private Token objectKeyword;
        private TypeDescriptorNode typeDescriptor;
        private Token openBracket;
        private NodeList<Node> members;
        private Token closeBracket;

        public ObjectConstructorExpressionNodeModifier(ObjectConstructorExpressionNode oldNode) {
            this.oldNode = oldNode;
            this.metadata = oldNode.metadata().orElse(null);
            this.objectTypeQualifier = oldNode.objectTypeQualifier().orElse(null);
            this.objectKeyword = oldNode.objectKeyword();
            this.typeDescriptor = oldNode.typeDescriptor().orElse(null);
            this.openBracket = oldNode.openBracket();
            this.members = oldNode.members();
            this.closeBracket = oldNode.closeBracket();
        }

        public ObjectConstructorExpressionNodeModifier withMetadata(
                MetadataNode metadata) {
            Objects.requireNonNull(metadata, "metadata must not be null");
            this.metadata = metadata;
            return this;
        }

        public ObjectConstructorExpressionNodeModifier withObjectTypeQualifier(
                Token objectTypeQualifier) {
            Objects.requireNonNull(objectTypeQualifier, "objectTypeQualifier must not be null");
            this.objectTypeQualifier = objectTypeQualifier;
            return this;
        }

        public ObjectConstructorExpressionNodeModifier withObjectKeyword(
                Token objectKeyword) {
            Objects.requireNonNull(objectKeyword, "objectKeyword must not be null");
            this.objectKeyword = objectKeyword;
            return this;
        }

        public ObjectConstructorExpressionNodeModifier withTypeDescriptor(
                TypeDescriptorNode typeDescriptor) {
            Objects.requireNonNull(typeDescriptor, "typeDescriptor must not be null");
            this.typeDescriptor = typeDescriptor;
            return this;
        }

        public ObjectConstructorExpressionNodeModifier withOpenBracket(
                Token openBracket) {
            Objects.requireNonNull(openBracket, "openBracket must not be null");
            this.openBracket = openBracket;
            return this;
        }

        public ObjectConstructorExpressionNodeModifier withMembers(
                NodeList<Node> members) {
            Objects.requireNonNull(members, "members must not be null");
            this.members = members;
            return this;
        }

        public ObjectConstructorExpressionNodeModifier withCloseBracket(
                Token closeBracket) {
            Objects.requireNonNull(closeBracket, "closeBracket must not be null");
            this.closeBracket = closeBracket;
            return this;
        }

        public ObjectConstructorExpressionNode apply() {
            return oldNode.modify(
                    metadata,
                    objectTypeQualifier,
                    objectKeyword,
                    typeDescriptor,
                    openBracket,
                    members,
                    closeBracket);
        }
    }
}
