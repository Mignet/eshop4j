package com.linkwee.xoss.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedJdbcTypes({JdbcType.DECIMAL})
@MappedTypes({Date.class})
public class DateTypeHandler extends BaseTypeHandler<Date>
{
  public Date getNullableResult(ResultSet rs, String key)
    throws SQLException
  {
    Long dateL = Long.valueOf(rs.getLong(key));
    if ((null != dateL) && (dateL.longValue() != 0L)) {
      return new Date(dateL.longValue());
    }
    return null;
  }

  public Date getNullableResult(ResultSet rs, int key) throws SQLException
  {
    Long dateL = Long.valueOf(rs.getLong(key));
    if ((null != dateL) && (dateL.longValue() != 0L)) {
      return new Date(dateL.longValue());
    }
    return null;
  }

  public Date getNullableResult(CallableStatement cs, int key) throws SQLException
  {
    return cs.getDate(key);
  }

  public void setNonNullParameter(PreparedStatement ps, int key, Date date, JdbcType type) throws SQLException
  {
    if (JdbcType.DECIMAL.equals(type))
      ps.setLong(key, date.getTime());
  }
}
