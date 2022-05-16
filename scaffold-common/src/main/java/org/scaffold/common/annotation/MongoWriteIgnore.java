/**
 * Author : chiziyue
 * Date : 2022年5月15日 下午8:39:13
 * Title : org.scaffold.common.annotation.MongoWriteIgnore.java
 *
**/
package org.scaffold.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MongoWriteIgnore {

}
