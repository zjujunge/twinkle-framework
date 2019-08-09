package com.twinkle.framework.core.datastruct.descriptor;

import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-08-01 18:19<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface TypeDescriptor {
    String getName();

    String getDescription();

    boolean isPrimitive();

    boolean isBean();

    Set<String> getAnnotations();

    String getClassName();
}
