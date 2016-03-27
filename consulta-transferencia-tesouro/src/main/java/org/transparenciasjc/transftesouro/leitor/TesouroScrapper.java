package org.transparenciasjc.transftesouro.leitor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import javax.inject.Qualifier;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;

@Retention(RUNTIME)
@Qualifier
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.TYPE,
		ElementType.PARAMETER })
public @interface TesouroScrapper {

}
