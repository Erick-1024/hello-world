package org.springframework.context.annotation;

import static org.springframework.context.annotation.MetadataUtils.attributesFor;

import java.util.List;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.TzTypeFilter;

public class TzAnnotationConfigUtils {

	public static void processCommonDefinitionAnnotations(AnnotatedBeanDefinition abd, List<TzTypeFilter> forceLazyIncludefilters, List<TzTypeFilter> forceLazyExcludefilters) {

		AnnotationMetadata metadata = abd.getMetadata();
		if (metadata.isAnnotated(Primary.class.getName())) {
			abd.setPrimary(true);
		}

		if (metadata.isAnnotated(Lazy.class.getName())) {
			abd.setLazyInit(attributesFor(metadata, Lazy.class).getBoolean("value"));
		} else {
			for (TzTypeFilter filter : forceLazyIncludefilters) {
				if (filter.match(metadata)) {
					abd.setLazyInit(true);
				}
			}

			for (TzTypeFilter filter : forceLazyExcludefilters) {
				if (filter.match(metadata)) {
					abd.setLazyInit(false);
				}
			}

		}

		if (metadata.isAnnotated(DependsOn.class.getName())) {
			abd.setDependsOn(attributesFor(metadata, DependsOn.class).getStringArray("value"));
		}
		if (abd instanceof AbstractBeanDefinition) {
			if (metadata.isAnnotated(Role.class.getName())) {
				Integer role = attributesFor(metadata, Role.class).getNumber("value");
				((AbstractBeanDefinition) abd).setRole(role);
			}
		}
	}
}
