package com.yang.test_converter;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.datetime.DateFormatter;

import java.util.Date;

/**
 * @author yangpeng
 * @version 1.0.0
 * @date 2021年06月24日
 */
public class String2DateConverter implements Converter<String, Date> {
	@Override
	public Date convert(String source) {
		return DateUtil.parse(source,"yyyy-MM-dd HH:mm:ss");
	}
}
