package com.mdbs.web.support;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;


import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.incrementer.AbstractColumnMaxValueIncrementer;

import com.mdbs.util.StringUtil;



public class MySqlDailyValueIncrementer extends AbstractColumnMaxValueIncrementer {

	/*** The SQL string for retrieving the new sequence value */
	private static final String VALUE_SQL = "select last_insert_id()";

	private String date;

	/*** The next id to serve */
	private long nextId = 0;

	/*** The max id to serve */
	private long maxId = 0;


	/***
	 * Default constructor for bean property style usage.
	 * @see #setDataSource
	 * @see #setIncrementerName
	 * @see #setColumnName
	 */
	public MySqlDailyValueIncrementer() {
	}

	/***
	 * Convenience constructor.
	 * @param dataSource the DataSource to use
	 * @param incrementerName the name of the sequence/table to use
	 * @param columnName the name of the column in the sequence table to use
	 */
	public MySqlDailyValueIncrementer(DataSource dataSource, String incrementerName, String columnName) {
		super(dataSource, incrementerName, columnName);
	}


	@Override
	protected synchronized long getNextKey() throws DataAccessException {
    	SimpleDateFormat smf = new SimpleDateFormat("yyyyMMdd");
    	String now = smf.format(new Date());
		if (this.maxId == this.nextId || !now.equals(date) ) {
			date = now;
			Connection con = DataSourceUtils.getConnection(getDataSource());
			Statement stmt = null;
			try {
				stmt = con.createStatement();
				DataSourceUtils.applyTransactionTimeout(stmt, getDataSource());
				// Increment the sequence column...
				String columnName = getColumnName();
				String incrementerName = getIncrementerName();
				ResultSet rs = stmt.executeQuery("select count(*) from "+ incrementerName + " where date = '"+date+"'");
				try {
					if (!rs.next()) {
						throw new DataAccessResourceFailureException("last_insert_id() failed after executing an update");
					}
					if( rs.getLong(1) == 0 ){
						stmt.executeUpdate("insert into "+ incrementerName + "(date," + columnName +
								")values('"+date+"','0')");
					}
				}
				finally {
					JdbcUtils.closeResultSet(rs);
				}
				stmt.executeUpdate("update "+ incrementerName + " set " + columnName +
						" = last_insert_id(" + columnName + " + " + getCacheSize() + ") where date = '"+date+"'");
				// Retrieve the new max of the sequence column...
				rs = stmt.executeQuery(VALUE_SQL);
				try {
					if (!rs.next()) {
						throw new DataAccessResourceFailureException("last_insert_id() failed after executing an update");
					}
					this.maxId = rs.getLong(1);
				}
				finally {
					JdbcUtils.closeResultSet(rs);
				}
				this.nextId = this.maxId - getCacheSize() + 1;
			}
			catch (SQLException ex) {
				throw new DataAccessResourceFailureException("Could not obtain last_insert_id()", ex);
			}
			finally {
				JdbcUtils.closeStatement(stmt);
				DataSourceUtils.releaseConnection(con, getDataSource());
			}
		}
		else {
			this.nextId++;
		}
		long r = StringUtil.getLong(String.format("%s%04d", date,this.nextId),this.nextId);
		return r;
	}

}