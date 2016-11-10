/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.scheduler.batch;

import java.util.List;

/**
 * @author ducer
 *
 */
public interface IHandler<T> {

	public void execute(List<T> args);
}
