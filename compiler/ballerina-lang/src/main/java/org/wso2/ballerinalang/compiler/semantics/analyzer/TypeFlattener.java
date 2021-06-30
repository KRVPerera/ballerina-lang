package org.wso2.ballerinalang.compiler.semantics.analyzer;

import org.wso2.ballerinalang.compiler.semantics.model.TypeCollector;
import org.wso2.ballerinalang.compiler.semantics.model.UniqueTypeVisitor;
import org.wso2.ballerinalang.compiler.semantics.model.types.BAnnotationType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BAnyType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BAnydataType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BArrayType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BBuiltInRefType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BErrorType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BFiniteType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BFutureType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BHandleType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BIntSubType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BIntersectionType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BInvokableType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BJSONType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BMapType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BNeverType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BNilType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BNoType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BObjectType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BPackageType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BParameterizedType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BRecordType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BStreamType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BStructureType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BTableType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BTupleType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BTypedescType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BUnionType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BXMLSubType;
import org.wso2.ballerinalang.compiler.semantics.model.types.BXMLType;
import org.wso2.ballerinalang.compiler.util.TypeTags;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TypeFlattener implements TypeCollector {

    private Set<BType> visited;
    private List<BType> flattenedList;
    private List<BType> basicTypeList;
    private List<BType> complexTypeList;
    private BType startingType;
    private boolean isCyclic;

    private void isCyclicType() {
        if (startingType.tag == TypeTags.UNION) {
            isCyclic = ((BUnionType) startingType).isCyclic;
        } else if (startingType.tag == TypeTags.TUPLE) {
            isCyclic = ((BTupleType) startingType).isCyclic;
        }
    }

    public TypeFlattener(BType type) {
        startingType = type;
        isCyclicType();
        basicTypeList = new ArrayList<>();
        complexTypeList = new ArrayList<>();
    }

    @Override
    public boolean isVisited(BType type) {
        return visited.contains(type);
    }

    @Override
    public List<BType> getBasicTypes() {
        return basicTypeList;
    }

    @Override
    public List<BType> getComplexTypes() {
        return complexTypeList;
    }

    @Override
    public void visit(BAnnotationType bAnnotationType) {

    }

    @Override
    public void visit(BArrayType bArrayType) {

    }

    @Override
    public void visit(BBuiltInRefType bBuiltInRefType) {

    }

    @Override
    public void visit(BAnyType bAnyType) {

    }

    @Override
    public void visit(BAnydataType bAnydataType) {

    }

    @Override
    public void visit(BErrorType bErrorType) {

    }

    @Override
    public void visit(BFiniteType bFiniteType) {

    }

    @Override
    public void visit(BInvokableType bInvokableType) {

    }

    @Override
    public void visit(BJSONType bjsonType) {

    }

    @Override
    public void visit(BMapType bMapType) {

    }

    @Override
    public void visit(BStreamType bStreamType) {

    }

    @Override
    public void visit(BTypedescType bTypedescType) {

    }

    @Override
    public void visit(BParameterizedType bTypedescType) {

    }

    @Override
    public void visit(BNeverType bNeverType) {

    }

    @Override
    public void visit(BNilType bNilType) {

    }

    @Override
    public void visit(BNoType bNoType) {

    }

    @Override
    public void visit(BPackageType bPackageType) {

    }

    @Override
    public void visit(BStructureType bStructureType) {

    }

    @Override
    public void visit(BTupleType bTupleType) {

    }

    @Override
    public void visit(BUnionType bUnionType) {

    }

    @Override
    public void visit(BIntersectionType bIntersectionType) {

    }

    @Override
    public void visit(BXMLType bxmlType) {

    }

    @Override
    public void visit(BTableType bTableType) {

    }

    @Override
    public void visit(BRecordType bRecordType) {

    }

    @Override
    public void visit(BObjectType bObjectType) {

    }

    @Override
    public void visit(BType bType) {

    }

    @Override
    public void visit(BFutureType bFutureType) {

    }

    @Override
    public void visit(BHandleType bHandleType) {

    }
}
