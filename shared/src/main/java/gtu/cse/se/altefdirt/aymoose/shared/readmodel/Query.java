package gtu.cse.se.altefdirt.aymoose.shared.readmodel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark a class as a query
 * builder. A query builder is a class that is used to
 * build queries. This annotation is used to mark
 * classes that are query builders.
 */
@Target(ElementType.TYPE)
public @interface Query {
    
}