package com.twinkle.framework.struct.asm.designer;

import com.twinkle.framework.asm.Bean;
import com.twinkle.framework.asm.designer.InstanceBuilderDesigner;
import com.twinkle.framework.asm.utils.TypeUtil;
import com.twinkle.framework.struct.asm.builder.StructAttributeImplBuilder;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     9/1/19 7:33 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class StructAttributeImplBuilderDesigner extends InstanceBuilderDesigner {
    public StructAttributeImplBuilderDesigner(String _attrClassName, String _interfaceName) {
        super(getImplBuilderInnerClassName(_attrClassName), Type.getType(StructAttributeImplBuilder.class), Type.getObjectType(toInternalName(_interfaceName)), Type.getObjectType(toInternalName(_attrClassName)));
    }
    @Override
    protected Type getInterfaceBaseType() {
        return TypeUtil.BEAN_TYPE;
    }
    @Override
    protected ClassVisitor addClassDeclaration(ClassVisitor _visitor) {
        super.addClassDeclaration(_visitor);
        _visitor.visitInnerClass(this.getBuilderClassName(), this.getInstanceType().getInternalName(), Bean.IMPL_BUILDER_INNER_NAME, Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC);
        return _visitor;
    }
    @Override
    protected void addClassDefinition(ClassVisitor _visitor) {
        super.addClassDefinition(_visitor);
        this.addSyntheticNewInstanceDefinition(_visitor, TypeUtil.OBJECT_TYPE);
        this.addSyntheticNewArrayDefinition(_visitor, TypeUtil.OBJECT_TYPE);
        this.addGetAttributeReferenceDefinition(_visitor);
    }

    /**
     * Add public AttributeRef getAttributeReference(String _attrRefName) method.
     *
     * @param _visitor
     * @return
     */
    private MethodVisitor addGetAttributeReferenceDefinition(ClassVisitor _visitor) {
        MethodVisitor tempVisitor = _visitor.visitMethod(Opcodes.ACC_PUBLIC, "getAttributeReference", StructAttributeClassDesigner.GET_ATTR_REF_METHOD_SIGNATURE, null, null);
        tempVisitor.visitCode();
        tempVisitor.visitVarInsn(Opcodes.ALOAD, 1);
        tempVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, this.getInstanceType().getInternalName(), "getAttributeReference", StructAttributeClassDesigner.GET_ATTR_REF_METHOD_SIGNATURE);
        tempVisitor.visitInsn(Opcodes.ARETURN);
        tempVisitor.visitMaxs(AUTO_STACK_SIZE, AUTO_LOCAL_VARS);
        tempVisitor.visitEnd();
        return tempVisitor;
    }

    /**
     * Get the ImplBuilder inner class name.
     *
     * @param _className
     * @return
     */
    private static String getImplBuilderInnerClassName(String _className) {
        return _className + Bean.IMPL_BUILDER_SUFFIX;
    }
}
