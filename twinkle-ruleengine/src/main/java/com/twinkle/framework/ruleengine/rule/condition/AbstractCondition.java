package com.twinkle.framework.ruleengine.rule.condition;

import com.twinkle.framework.core.context.ContextSchema;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-07-19 11:46<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public abstract class AbstractCondition implements ICondition {
    /**
     * The Context Schema.
     */
    protected ContextSchema contextSchema;

    public AbstractCondition() {
        this.contextSchema = ContextSchema.getInstance();
    }
}
