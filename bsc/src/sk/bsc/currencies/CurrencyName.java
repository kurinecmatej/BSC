package sk.bsc.currencies;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Define each currency class currency shortcut.
 * E.g. shortcut for euro is eur, but class name could have different name.
 * 
 * This annotation is used to obtain correct currency object by this value.
 * 
 * 
 * @author matej
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrencyName {
	
	/**
	 * Value indicates real currency name, or its shortcut used in application.
	 * 
	 * @return currency name.
	 */
	String value();
}
