package org.building.pmservice.service.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangh on 2018/5/17.
 */
public class ProcedureUtils {
    public static List<Map<String, Object>> ResultHash(ResultSet rs) throws SQLException {

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        ResultSetMetaData rsm = rs.getMetaData();

        while (rs.next()) {
            Map<String, Object> model = new HashMap<String, Object>();
            for (int i = 1; i <= rsm.getColumnCount(); i++) {
                switch (rsm.getColumnType(i)) {
                    case Types.ROWID:
                        model.put(rsm.getColumnName(i),
                                rs.getString(i) == null ? "" : rs.getString(i));
                        break;
                    case Types.DATE:
                        model.put(rsm.getColumnName(i),
                                rs.getString(i) == null ? "" : rs.getString(i)
                                        .split("\\.")[0]);
                        break;
                    case Types.TIMESTAMP:
                        model.put(rsm.getColumnName(i),
                                rs.getString(i) == null ? "" : rs.getString(i));
                        break;
                    case Types.DOUBLE:
                        model.put(rsm.getColumnName(i),
                                (rs.getString(i) == null ? "" : rs.getDouble(i)));
                        break;
                    case Types.BLOB:
                        model.put(
                                rsm.getColumnName(i),
                                (rs.getObject(i) == null ? "" : new String(rs
                                        .getBytes(i))));
                        break;
                    case Types.CLOB:
                        model.put(
                                rsm.getColumnName(i),
                                (rs.getObject(i) == null ? "" : rs.getString(i)));
                        break;
                    default:
                        model.put(rsm.getColumnName(i),
                                rs.getObject(i) == null ? "" : rs.getObject(i));
                }
            }
            result.add(model);
        }
        rs.close();

        return result;
    }
}
