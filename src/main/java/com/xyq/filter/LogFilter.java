package com.xyq.filter;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.proxy.jdbc.ResultSetProxy;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @Author xyq
 * @create 2019-09-18 18:45
 */
public class LogFilter extends Slf4jLogFilter {
    @Override
    public boolean resultSet_next(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        boolean moreRows = chain.resultSet_next(resultSet);
        if (moreRows && true && this.isResultSetLogEnabled()) {
            try {
                StringBuffer buf = new StringBuffer();
                buf.append(" Result: [");
                ResultSetMetaData meta = resultSet.getMetaData();
                int i = 0;

                for(int size = meta.getColumnCount(); i < size; ++i) {
                    if (i != 0) {
                        buf.append(", ");
                    }

                    int columnIndex = i + 1;
                    int type = meta.getColumnType(columnIndex);
                    Object value;
                    if (type == 93) {
                        value = resultSet.getTimestamp(columnIndex);
                    } else if (type == 2004) {
                        value = "<BLOB>";
                    } else if (type == 2005) {
                        value = "<CLOB>";
                    } else if (type == 2011) {
                        value = "<NCLOB>";
                    } else if (type == -2) {
                        value = "<BINARY>";
                    } else {
                        value = resultSet.getObject(columnIndex);
                    }
                    buf.append(value);
                }

                buf.append("]");
                this.resultSetLog(buf.toString());
            } catch (SQLException var11) {
                this.resultSetLogError("logging error", var11);
            }
        }
        return moreRows;
    }

    @Override
    protected void resultSetOpenAfter(ResultSetProxy resultSet) {
        if (true) {
            try {
                StringBuffer buf = new StringBuffer();
                buf.append(" Header: [");
                ResultSetMetaData meta = resultSet.getMetaData();
                int i = 0;

                for(int size = meta.getColumnCount(); i < size; ++i) {
                    if (i != 0) {
                        buf.append(", ");
                    }
                    buf.append(meta.getColumnName(i + 1));
                }
                buf.append("]");
                this.resultSetLog(buf.toString());
            } catch (SQLException var7) {
                this.resultSetLogError("logging error", var7);
            }
        }
    }
}
