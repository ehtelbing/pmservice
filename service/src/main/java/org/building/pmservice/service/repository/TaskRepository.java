package org.building.pmservice.service.repository;

import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class TaskRepository {

    private JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Transactional
    public List SetWeekAMToMess(String Type) {
        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call AM_TASK_SEND_GET(:V_V_TYPE,:V_CURSOR)}";

                CallableStatement statement = con.prepareCall(sql);
                statement.setString("V_V_TYPE", Type);
                statement.registerOutParameter("V_CURSOR", OracleTypes.CURSOR);
                return statement;
            }
        }, new CallableStatementCallback<List>() {
            public List doInCallableStatement(CallableStatement cs)
                    throws SQLException, DataAccessException {
                List result = new ArrayList();
                cs.execute();
                ResultSet rs = (ResultSet) cs.getObject("V_CURSOR");
                while (rs.next()) {
                    Map map = new HashMap();
                    map.put("NUM", rs.getString("NUM"));
                    map.put("V_SERVERNAME", rs.getString("V_SERVERNAME"));
                    map.put("V_SENDPASSWORD", rs.getString("V_SENDPASSWORD"));
                    map.put("V_SEND_PERSON", rs.getString("V_SEND_PERSON"));
                    map.put("V_LOGINNAME", rs.getString("V_LOGINNAME"));
                    map.put("V_JST", rs.getString("V_JST"));
                    map.put("V_TYPE", rs.getString("V_TYPE"));
                    result.add(map);
                }
                return result;
            }
        });
    }

    @Transactional
    public String UpdateAMToMessState(String Type) {
        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call AM_TASK_SEND_SET(:V_V_TYPE,:V_INFO)}";

                CallableStatement statement = con.prepareCall(sql);
                statement.setString("V_V_TYPE", Type);
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
