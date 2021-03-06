package com.twinkle.framework.asm.define;

import com.twinkle.framework.asm.builder.MethodDefBuilder;
import com.twinkle.framework.asm.descriptor.GeneralClassTypeDescriptor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-08-13 11:29<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
public class GeneralClassTypeDefImpl extends BeanTypeDefImpl implements GeneralClassTypeDef {
    /**
     * Class's methods.
     */
    private List<MethodDef> methods;

    public GeneralClassTypeDefImpl(GeneralClassTypeDescriptor _descriptor, ClassLoader _classLoader) throws ClassNotFoundException {
        super(_descriptor, _classLoader);
        this.methods = MethodDefBuilder.getMethodDefs(_descriptor.getMethods(), _classLoader, this.getTypeDefMap());
    }

    public GeneralClassTypeDefImpl(GeneralClassTypeDescriptor _descriptor, ClassLoader _classLoader, Map<String, TypeDef> _typeDefMap) throws ClassNotFoundException {
        super(_descriptor, _classLoader, _typeDefMap);
        this.methods = MethodDefBuilder.getMethodDefs(_descriptor.getMethods(), _classLoader, _typeDefMap);
    }

    public GeneralClassTypeDefImpl(GeneralClassTypeDefImpl _beanTypeDefine) {
        super(_beanTypeDefine);
        this.methods = _beanTypeDefine.getMethods();
    }
}
