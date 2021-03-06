package com.twinkle.framework.asm.descriptor;

import java.util.List;
import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-08-01 18:19 <br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface BeanTypeDescriptor extends TypeDescriptor {
    /**
     * get the super class descriptor.
     *
     * @return
     */
    TypeDescriptor getSuperDescriptor();

    /**
     * Parents' descriptor list.
     *
     * @return
     */
    Set<BeanTypeDescriptor> getInterfaceDescriptors();

    /**
     * Get attributes list.
     *
     * @return
     */
    List<AttributeDescriptor> getAttributes();

    /**
     * Get attribute descriptor by given _attrName.
     *
     * @param _attrName
     * @return
     */
    AttributeDescriptor getAttribute(String _attrName);
}
