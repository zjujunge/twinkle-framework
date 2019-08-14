package com.twinkle.framework.bootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-07-19 22:00<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@SpringBootApplication(scanBasePackages={"com.twinkle.framework"})
public class TwinkleApplicationBootStrap {
    public static void main(String[] args) {
        SpringApplication.run(TwinkleApplicationBootStrap.class, args);
    }
}
