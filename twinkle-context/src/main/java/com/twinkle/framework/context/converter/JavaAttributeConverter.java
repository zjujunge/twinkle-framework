package com.twinkle.framework.context.converter;

import com.twinkle.framework.api.context.AttributeInfo;
import com.twinkle.framework.core.lang.Attribute;
import com.twinkle.framework.core.lang.JavaAttributeInfo;
import com.twinkle.framework.struct.ref.AttributeRef;
import com.twinkle.framework.struct.lang.StructAttribute;
import com.twinkle.framework.struct.utils.StructAttributeUtil;
import com.twinkle.framework.struct.utils.StructTypeUtil;
import org.apache.commons.lang3.StringUtils;
import org.objectweb.asm.Type;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     8/25/19 6:21 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class JavaAttributeConverter {
    /**
     * Convert AttributeInfo to Java Attribute Info.
     *
     * @param _attrInfo
     * @return
     */
    public static JavaAttributeInfo convertToJavaAttribute(AttributeInfo _attrInfo) throws ClassNotFoundException {
        JavaAttributeInfo tempInfo = new JavaAttributeInfo();
        tempInfo.setPrimitiveType(_attrInfo.getPrimitiveType());
        switch (_attrInfo.getPrimitiveType()) {
            case Attribute.INTEGER_TYPE:
                tempInfo.setName("Integer");
                tempInfo.setClassName(Integer.class.getName());
                tempInfo.setAttributeClass(Integer.class);
                tempInfo.setDescription(Type.getDescriptor(Integer.TYPE));
                break;
            case Attribute.UNICODE_STRING_TYPE:
            case Attribute.STRING_TYPE:
                tempInfo.setName("String");
                tempInfo.setClassName(String.class.getName());
                tempInfo.setAttributeClass(String.class);
                tempInfo.setDescription(Type.getDescriptor(String.class));
                break;
            case Attribute.LONG_TYPE:
                tempInfo.setName("Long");
                tempInfo.setClassName(Long.class.getName());
                tempInfo.setAttributeClass(Long.class);
                tempInfo.setDescription(Type.getDescriptor(Long.TYPE));
                break;
            case Attribute.FLOAT_TYPE:
                tempInfo.setName("Float");
                tempInfo.setClassName(Float.class.getName());
                tempInfo.setAttributeClass(Float.class);
                tempInfo.setDescription(Type.getDescriptor(Float.TYPE));
                break;
            case Attribute.DOUBLE_TYPE:
                tempInfo.setName("Double");
                tempInfo.setClassName(Double.class.getName());
                tempInfo.setAttributeClass(Double.class);
                tempInfo.setDescription(Type.getDescriptor(Double.TYPE));
                break;
            case Attribute.OBJECT_TYPE:
                tempInfo.setName(_attrInfo.getName());
                if (_attrInfo.getName().indexOf(":") > 0) {
                    Class<?> tempAttrClass = getStructAttributeClass(_attrInfo.getName());
                    tempInfo.setClassName(tempAttrClass.getName());
                    tempInfo.setAttributeClass(tempAttrClass);
                    tempInfo.setDescription(Type.getDescriptor(tempAttrClass));
                } else {
                    tempInfo.setClassName(_attrInfo.getClassName());
                    tempInfo.setAttributeClass(_attrInfo.getAttributeClass());
                    tempInfo.setDescription(_attrInfo.getDescription());
                }
                break;
            case Attribute.STRUCT_TYPE:
                tempInfo.setName(_attrInfo.getName());
                tempInfo.setClassName(_attrInfo.getValueClass().getName());
                tempInfo.setAttributeClass(_attrInfo.getValueClass());
                tempInfo.setDescription(Type.getDescriptor(_attrInfo.getValueClass()));
                break;
            case Attribute.LIST_ATTRIBUTE_TYPE:
                tempInfo.setName(_attrInfo.getName());
                AttributeInfo tempElementAttributeInfo = _attrInfo.getValueAttributeInfo();
                if (tempElementAttributeInfo != null) {
                    JavaAttributeInfo tempElementJavaInfo = convertToJavaAttribute(tempElementAttributeInfo);
                    Class<?> tempClass = Array.newInstance(tempElementJavaInfo.getAttributeClass(), 1).getClass();
                    tempInfo.setClassName(tempClass.getName());
                    tempInfo.setAttributeClass(tempClass);
                    tempInfo.setDescription(Type.getDescriptor(tempClass));
                } else {
                    tempInfo.setClassName(List.class.getName());
                    tempInfo.setAttributeClass(List.class);
                    tempInfo.setDescription(Type.getDescriptor(List.class));
                }
                break;
            case Attribute.LIST_STRUCT_ATTRIBUTE_TYPE:
                tempInfo.setName(_attrInfo.getName());
                Class<?> tempClass = Array.newInstance(_attrInfo.getValueClass(), 1).getClass();
                tempInfo.setClassName(tempClass.getName());
                tempInfo.setAttributeClass(tempClass);
                tempInfo.setDescription(Type.getDescriptor(tempClass));
                break;
            default:
                throw new RuntimeException("Encountered unsupported attribute type [{" + _attrInfo + "}].");
        }
        return tempInfo;
    }

    /**
     * Get the Struct Attribute class with given type name.
     *
     * @param _typeName
     * @return
     * @throws ClassNotFoundException
     */
    private static Class<?> getStructAttributeClass(String _typeName) throws ClassNotFoundException {
        String tempAttrTypeName = _typeName;
        String tempAttrItemName = "";
        int tempDotIndex = _typeName.indexOf(".");
        if (tempDotIndex > 0) {
            tempAttrItemName = tempAttrTypeName.substring(tempDotIndex + 1);
            tempAttrTypeName = tempAttrTypeName.substring(0, tempDotIndex);
        }
        StructAttribute tempAttr = null;
        if (_typeName.indexOf(":") > 0) {
            tempAttr = StructAttributeUtil.newStructAttribute(tempAttrTypeName);
        }
        if (StringUtils.isNotBlank(tempAttrItemName)) {
            AttributeRef tempRef = tempAttr.getAttributeRef(tempAttrItemName);
            if (!tempRef.getType().isStructType()) {
                return StructTypeUtil.getTypeClass(tempRef.getType());
            }
            StructAttribute tempSubAttr = tempAttr.getStruct(tempRef);
            return tempSubAttr.getClass();
        }
        return tempAttr.getClass();
    }
}
