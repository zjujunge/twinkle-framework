package com.twinkle.framework.struct.asm.classloader;

import com.twinkle.framework.struct.resolver.StructAttributeTypeResolver;
import com.twinkle.framework.struct.type.StructType;
import lombok.extern.slf4j.Slf4j;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     8/31/19 10:05 AM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
public final class StructAttributeClassLoader extends AbstractStructAttributeClassLoader {
    private final StructAttributeTypeResolver typeResolver;

    public StructAttributeClassLoader(ClassLoader _classLoader, StructAttributeTypeResolver _resolver) {
        super(_classLoader);
        this.typeResolver = _resolver;
    }

    public StructAttributeClassLoader(StructAttributeTypeResolver _resolver) {
        this.typeResolver = _resolver;
    }
    @Override
    protected StructType getStructAttributeType(String _attrName) {
        return this.typeResolver.getStructAttributeType(_attrName);
    }
}
