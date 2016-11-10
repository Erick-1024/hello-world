package org.springframework.context.annotation;

import java.util.regex.Pattern;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.filter.TzTypeFilter;
import org.springframework.util.Assert;

public class TzRegexPatternTypeFilter implements TzTypeFilter {
	private final Pattern pattern;

	public TzRegexPatternTypeFilter(Pattern pattern) {
		Assert.notNull(pattern, "Pattern must not be null");
		this.pattern = pattern;
	}

	@Override
	public boolean match(ClassMetadata metadata) {
		return this.pattern.matcher(metadata.getClassName()).matches();
	}
}
