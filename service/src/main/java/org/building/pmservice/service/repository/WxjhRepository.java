package org.building.pmservice.service.repository;

import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangh on 2018/5/15.
 */
@Repository
public class WxjhRepository {
    private JdbcTemplate template;

    @Autowired
    public void setTest1DataSource(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Transactional
    public String PRO_PM_PLAN_SC_DATA_SET(String V_V_CKCODE, String V_V_CKNAME, String V_V_ZYQCODE, String V_V_ZYQNAME, String V_V_EQUCODE,
                                          String V_V_EQUNAME, String V_V_PLAN_TIME_B, String V_V_PLAN_TIME_E, String V_V_FACT_TIME_B, String V_V_FACT_TIME_E,
                                          String V_V_REASON, String V_V_TYPE) {
        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call PRO_PM_PLAN_SC_DATA_SET(:V_V_CKCODE,:V_V_CKNAME,:V_V_ZYQCODE,:V_V_ZYQNAME,:V_V_EQUCODE,:V_V_EQUNAME,:V_V_PLAN_TIME_B,:V_V_PLAN_TIME_E,:V_V_FACT_TIME_B,:V_V_FACT_TIME_E,:V_V_REASON,:V_V_TYPE,:V_INFO)}";

                CallableStatement statement = con.prepareCall(sql);

                statement.setString("V_V_CKCODE", V_V_CKCODE);
                statement.setString("V_V_CKNAME", V_V_CKNAME);
                statement.setString("V_V_ZYQCODE", V_V_ZYQCODE);
                statement.setString("V_V_ZYQNAME", V_V_ZYQNAME);
                statement.setString("V_V_EQUCODE", V_V_EQUCODE);

                statement.setString("V_V_EQUNAME", V_V_EQUNAME);
                statement.setString("V_V_PLAN_TIME_B", V_V_PLAN_TIME_B);
                statement.setString("V_V_PLAN_TIME_E", V_V_PLAN_TIME_E);
                statement.setString("V_V_FACT_TIME_B", V_V_FACT_TIME_B);
                statement.setString("V_V_FACT_TIME_E", V_V_FACT_TIME_E);
                statement.setString("V_V_REASON", V_V_REASON);
                statement.setString("V_V_TYPE", V_V_TYPE);
                statement.registerOutParameter("V_INFO", OracleTypes.VARCHAR);
                return statement;
            }
        }, new CallableStatementCallback<String>() {
            public String doInCallableStatement(CallableStatement cs)
                    throws SQLException, DataAccessException {
                cs.execute();
                String result = cs.getObject("V_INFO").toString();
                return result;
            }
        });
    }

    public String InsertWxProject(String V_V_SYSTEM, String V_V_GUID, String V_V_DEFECT_GUID, String V_V_YEAR, String V_V_MONTH, String V_V_ORGCODE, String V_V_DEPTCODE, String V_V_PROJECT_CODE, String V_V_PROJECT_NAME,
                                  String V_V_WBS_CODE, String V_V_WBS_NAME, String V_V_CONTENT, String V_V_BUDGET_MONEY, String V_V_BILL_CODE, String V_V_PROJECT_STATUS, String V_V_DEFECT_STATUS, String V_V_REPAIR_DEPT,
                                  String V_V_REPAIR_DEPT_TXT, String V_V_FZR, String V_V_DATE_B, String V_V_DATE_E, String V_V_INPER, String V_V_INTIEM, String V_V_PORJECT_GUID) {
        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call PRO_FXJH_PROJECT_IMPORT(:V_V_SYSTEM,:V_V_GUID,:V_V_DEFECT_GUID,:V_V_YEAR,:V_V_MONTH,:V_V_ORGCODE,:V_V_DEPTCODE,:V_V_PROJECT_CODE,:V_V_PROJECT_NAME," +
                        ":V_V_WBS_CODE,:V_V_WBS_NAME,:V_V_CONTENT,:V_V_BUDGET_MONEY,:V_V_BILL_CODE,:V_V_PROJECT_STATUS,:V_V_DEFECT_STATUS,:V_V_REPAIR_DEPT,:V_V_REPAIR_DEPT_TXT,:V_V_FZR,:V_V_DATE_B" +
                        ",:V_V_DATE_E,:V_V_INPER,:V_V_INTIEM,:V_V_PORJECT_GUID,:V_INFO)}";

                CallableStatement statement = con.prepareCall(sql);

                statement.setString("V_V_SYSTEM", V_V_SYSTEM);
                statement.setString("V_V_GUID", V_V_GUID);
                statement.setString("V_V_DEFECT_GUID", V_V_DEFECT_GUID);
                statement.setString("V_V_YEAR", V_V_YEAR);
                statement.setString("V_V_MONTH", V_V_MONTH);
                statement.setString("V_V_ORGCODE", V_V_ORGCODE);
                statement.setString("V_V_DEPTCODE", V_V_DEPTCODE);
                statement.setString("V_V_PROJECT_CODE", V_V_PROJECT_CODE);
                statement.setString("V_V_PROJECT_NAME", V_V_PROJECT_NAME);
                statement.setString("V_V_WBS_CODE", V_V_WBS_CODE);
                statement.setString("V_V_WBS_NAME", V_V_WBS_NAME);
                statement.setString("V_V_CONTENT", V_V_CONTENT);
                statement.setString("V_V_BUDGET_MONEY", V_V_BUDGET_MONEY);
                statement.setString("V_V_BILL_CODE", V_V_BILL_CODE);
                statement.setString("V_V_PROJECT_STATUS", V_V_PROJECT_STATUS);
                statement.setString("V_V_DEFECT_STATUS", V_V_DEFECT_STATUS);
                statement.setString("V_V_REPAIR_DEPT", V_V_REPAIR_DEPT);
                statement.setString("V_V_REPAIR_DEPT_TXT", V_V_REPAIR_DEPT_TXT);
                statement.setString("V_V_FZR", V_V_FZR);
                statement.setString("V_V_DATE_B", V_V_DATE_B);
                statement.setString("V_V_DATE_E", V_V_DATE_E);
                statement.setString("V_V_INPER", V_V_INPER);
                statement.setString("V_V_INTIEM", V_V_INTIEM);
                statement.setString("V_V_PORJECT_GUID", V_V_PORJECT_GUID);
                statement.registerOutParameter("V_INFO", OracleTypes.VARCHAR);
                return statement;
            }
        }, new CallableStatementCallback<String>() {
            public String doInCallableStatement(CallableStatement cs)
                    throws SQLException, DataAccessException {
                cs.execute();
                String result = cs.getObject("V_INFO").toString();
                return result;
            }
        });
    }

    public String DefectBack(String V_V_DEFECT_GUID, String V_V_BILL_CODE, String V_V_DEFECT_TYPE, String V_V_GUID,String V_V_STR01) {
        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call PM_DEFECT_PRODECT_DEL(:V_V_DEFECTGUID,:V_V_BILL_CODE,:V_V_DEFECTTYPE,:V_V_GUID,:V_V_STR01,:V_INFO)}";

                CallableStatement statement = con.prepareCall(sql);

                statement.setString("V_V_DEFECT_GUID", V_V_DEFECT_GUID);
                statement.setString("V_V_BILL_CODE", V_V_BILL_CODE);
                statement.setString("V_V_DEFECT_TYPE", V_V_DEFECT_TYPE);
                statement.setString("V_V_GUID", V_V_GUID);
                statement.setString("V_V_STR01", V_V_STR01);
                statement.registerOutParameter("V_INFO", OracleTypes.VARCHAR);
                return statement;
            }
        }, new CallableStatementCallback<String>() {
            public String doInCallableStatement(CallableStatement cs)
                    throws SQLException, DataAccessException {
                cs.execute();
                String result = cs.getObject("V_INFO").toString();
                return result;
            }
        });
    }

    public String WebServiceLog(String V_V_SYSTEM, String V_V_GUID, String V_V_STATE, String V_V_MENO) {
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
        }, new CallableStatementCallback<String>() {
            public String doInCallableStatement(CallableStatement cs)
                    throws SQLException, DataAccessException {
                cs.execute();
                String result = cs.getObject("V_INFO").toString();
                return result;
            }
        });
    }
}
