package org.wso2.ballerinalang.compiler.semantics.model;

import org.wso2.ballerinalang.compiler.semantics.model.types.BType;

import java.util.List;

public interface TypeCollector extends TypeVisitor {

    default void visit(TypeCollector visitor) {
        visitor.visit(this);
    }

    boolean isVisited(BType type);

    List<BType> getBasicTypes();
    List<BType> getComplexTypes();
}
