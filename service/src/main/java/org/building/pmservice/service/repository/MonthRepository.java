package org.building.pmservice.service.repository;

import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.building.pmservice.service.utils.ProcedureUtils.ResultHash;

/**
 * Created by hrb on 2018/5/15.
 */
@Repository
public class MonthRepository {

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

    //month download
    public Map OutMonthData(String DATE_START,String DATE_END,String V_ORG_CODE,String V_DEPT_CODE){
        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call PRO_MONTH_DATA_DOWNLOAD(:DATE_START,:DATE_END,:V_ORG_CODE,:V_DEPT_CODE,:RET)}";

                CallableStatement statement = con.prepareCall(sql);

                statement.setString("DATE_START", DATE_START);
                statement.setString("DATE_END", DATE_END);
                statement.setString("V_ORG_CODE", V_ORG_CODE);
                statement.setString("V_DEPT_CODE", V_DEPT_CODE);

                statement.registerOutParameter("RET", OracleTypes.CURSOR);
                return statement;
            }
        }, new CallableStatementCallback<Map>() {
            @Override
            public Map doInCallableStatement(CallableStatement callableStatement) throws SQLException, DataAccessException {
                callableStatement.execute();
                Map result=new HashMap();
                result.put("list",ResultHash((ResultSet) callableStatement.getObject("V_CURSOR")));
                result.put("RET",(String) callableStatement.getString("RET"));
                return result;
            }
        });
    }
}
