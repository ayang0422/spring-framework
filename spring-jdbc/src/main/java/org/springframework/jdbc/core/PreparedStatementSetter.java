/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * General callback interface used by the {@link JdbcTemplate} class.
 *
 * <p>This interface sets values on a {@link java.sql.PreparedStatement} provided
 * by the JdbcTemplate class, for each of a number of updates in a batch using the
 * same SQL. Implementations are responsible for setting any necessary parameters.
 * SQL with placeholders will already have been supplied.
 *
 * <p>It's easier to use this interface than {@link PreparedStatementCreator}:
 * The JdbcTemplate will create the PreparedStatement, with the callback
 * only being responsible for setting parameter values.
 *
 * <p>Implementations <i>do not</i> need to concern themselves with
 * SQLExceptions that may be thrown from operations they attempt.
 * The JdbcTemplate class will catch and handle SQLExceptions appropriately.
 *
 *
 JdbcTemplate类使用的通用回调接口。

 此接口在 JdbcTemplate 类提供的PreparedStatement上设置值，使用相同的 SQL 为批处理中的多个更新中的每一个更新。
 实现负责设置任何必要的参数。 已经提供了带有占位符的 SQL。

 这个接口比PreparedStatementCreator更容易使用：JdbcTemplate 会创建 PreparedStatement，回调只负责设置参数值。
 实现不需要关心自己与可能从他们试图操作抛出的SQLException。 JdbcTemplate 类将适当地捕获和处理 SQLExceptions
 *
 * @author Rod Johnson
 * @since March 2, 2003
 * @see JdbcTemplate#update(String, PreparedStatementSetter)
 * @see JdbcTemplate#query(String, PreparedStatementSetter, ResultSetExtractor)
 */
@FunctionalInterface
public interface PreparedStatementSetter {

	/**
	 * Set parameter values on the given PreparedStatement.
	 * @param ps the PreparedStatement to invoke setter methods on
	 * @throws SQLException if an SQLException is encountered
	 * (i.e. there is no need to catch SQLException)
	 */
	void setValues(PreparedStatement ps) throws SQLException;

}
