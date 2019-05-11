package org.building.pmservice.service.repository;


import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;


@Repository
public class PmRepository {
    private JdbcTemplate template;

    @Autowired
    public void setTest1DataSource(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private List<HashMap> ResultHash(ResultSet rs) throws SQLException {

        List<HashMap> result = new ArrayList<HashMap>();

        ResultSetMetaData rsm = rs.getMetaData();

        int colNum = 0;

        colNum = rsm.getColumnCount();

        while (rs.next()) {
            HashMap model = new HashMap();
            for (int i = 1; i <= rsm.getColumnCount(); i++) {
                if (rsm.getColumnType(i) == 91) {
                    model.put(rsm.getColumnName(i),
                            rs.getString(i) == null ? "" : rs.getString(i)
                                    .split("\\.")[0]);
                } else {
                    if (rsm.getColumnType(i) == 2) {
                        if (rs.getString(i) == null) {
                            model.put(rsm.getColumnName(i), "");
                        } else {
                            model.put(rsm.getColumnName(i), rs.getDouble(i));
                        }
                    } else {
                        model.put(rsm.getColumnName(i),
                                rs.getString(i) == null ? "" : rs.getString(i)
                                        .toString().replaceAll("\\n", ""));
                    }
                }
            }
            result.add(model);
        }
        rs.close();

        return result;
    }

    @Transactional
    public Map PM_DEFECT_LOG_FROMPRO_DEFECT_S(String Xml) {
        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call PM_DEFECT_LOG_FROMPRO_DEFECT_S(:V_V_PROJCET_GUID,:V_CURSOR)}";

                CallableStatement statement = con.prepareCall(sql);

                statement.setString("V_V_PROJCET_GUID", Xml);
                statement.registerOutParameter("V_CURSOR", OracleTypes.CURSOR);
                return statement;
            }
        }, new CallableStatementCallback<Map>() {
            public Map doInCallableStatement(CallableStatement cs)
                    throws SQLException, DataAccessException {
                cs.execute();
                Map result = new HashMap();
                result.put("list", ResultHash((ResultSet) cs.getObject("V_CURSOR")));
                return result;
            }
        });
    }

    @Transactional
    public Map WebServiceLog(String V_V_SYSTEM, String V_V_GUID, String V_V_STATE, String V_V_MENO) {
        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call BASE_WEBSERVICE_LOG_INERT(:V_V_SYSTEM,:V_V_GUID,:V_V_STATE,:V_V_MENO,:V_INFO)}";

                CallableStatement statement = con.prepareCall(sql);

                statement.setString("V_V_SYSTEM", V_V_SYSTEM);
                statement.setString("V_V_GUID", V_V_GUID);
                statement.setString("V_V_STATE", V_V_STATE);
                statement.setString("V_V_MENO", V_V_MENO);
                statement.registerOutParameter("V_INFO", OracleTypes.VARCHAR);
                return statement;
            }
        }, new CallableStatementCallback<Map>() {
            public Map doInCallableStatement(CallableStatement cs)
                    throws SQLException, DataAccessException {
                cs.execute();
                Map result = new HashMap();
                result.put("V_INFO", cs.getObject("V_INFO").toString());
                return result;
            }
        });
    }
}
