package org.springframework.core.type.filter;

import org.springframework.core.type.ClassMetadata;

public interface TzTypeFilter {

	boolean match(ClassMetadata metadata);

}