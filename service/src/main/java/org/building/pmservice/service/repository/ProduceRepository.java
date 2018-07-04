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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangh on 2018/5/15.
 */
@Repository
public class ProduceRepository {
    private JdbcTemplate template;
    private NamedParameterJdbcTemplate nameTemplate;

    @Autowired
    public void setTest1DataSource(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.nameTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Transactional
    public List<Map<String, Object>> getList() {
        StringBuffer sql = new StringBuffer();
        sql.append("select *from t_user where account=:account");


        Map<String, Object> query = new HashMap<String, Object>();
        query.put("account", "admin");

        List<Map<String, Object>> list = nameTemplate.queryForList(sql.toString(), query);
        return list;

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
}
