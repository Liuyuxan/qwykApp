package com.qywk.common.core.utils;

import com.qywk.common.core.utils.sensitivewdfilter.WordFilter;

/**
 * @author qlh
 * @date 2024/04/04 0:16
 * @description	敏感词过滤器工具
 */
public class SensitiveWordFilter {

	/**
	 * 过滤替换
	 * @param content
	 * @return	过滤后的内容，被过滤的为 *
	 */
	public static String filterReplace(String content){
		return WordFilter.doFilter(content);
	}

	/**
	 * 过滤屏蔽/是否包含敏感词
	 * @param content
	 * @return true 是敏感词 / false 绿色内容
	 */
	public static boolean isContains(String content){
		return WordFilter.isContains(content);
	}
}
