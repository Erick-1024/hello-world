package com.cana.vbam.common.utils;

import com.cana.vbam.common.dto.Pagination;

public class PaginationUtils {

	public static <T extends Pagination> void StandardizingPagination(T pagination) {
		pagination.setPage(pagination.getPage() < 1 ? 1 : pagination.getPage());
		pagination.setPageSize(pagination.getPageSize() < 1 ? 10 : pagination.getPageSize());
	}
	
}
