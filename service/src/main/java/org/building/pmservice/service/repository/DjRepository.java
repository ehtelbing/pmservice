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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tangh on 2018/5/15.
 */
@Repository
public class DjRepository {
    private JdbcTemplate template;

   /* @Autowired
    public void setTest1DataSource(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }
*/
    public Map ImportDefect(String V_V_SYSTEM,String V_V_I_ID, String V_V_DEFECTLIST, String V_V_DEFTYPE,
                            String V_V_SOURCEID, String V_D_DEFECTDATE, String V_D_INDATE, String V_V_PERCODE, String V_V_PERNAME,
                            String V_V_DEPTCODE, String V_V_EQUCODE, String V_V_IDEA, String V_V_EQUSITE, String V_V_EQUCHILDCODE,
                            String V_V_INPERCODE, String V_V_INPERNAME, String V_V_EQUTYPECODE, String V_V_ORGCODE, String V_V_BZ) {
        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call PRO_DEFECT_IMPORT(:V_V_SYSTEM,:V_V_I_ID,:V_V_DEFECTLIST,:V_V_DEFTYPE,:V_V_SOURCEID,:V_D_DEFECTDATE,:V_D_INDATE,:V_V_PERCODE,:V_v_PERNAME" +
                        ",:V_V_DEPTCODE,:V_V_EQUCODE,:V_V_IDEA,:V_V_EQUSITE,:V_V_EQUCHILDCODE,:V_V_INPERCODE,:V_V_INPERNAME,:V_V_EQUTYPECODE,:V_V_ORGCODE,:V_V_BZ,:V_V_GUID,:V_INFO)}";

                CallableStatement statement = con.prepareCall(sql);
                statement.setString("V_V_SYSTEM", V_V_SYSTEM);
                statement.setString("V_V_I_ID", V_V_I_ID);
                statement.setString("V_V_DEFECTLIST", V_V_DEFECTLIST);
                statement.setString("V_V_DEFTYPE", V_V_DEFTYPE);
                statement.setString("V_V_SOURCEID", V_V_SOURCEID);
                statement.setString("V_D_DEFECTDATE", V_D_DEFECTDATE);
                statement.setString("V_D_INDATE", V_D_INDATE);
                statement.setString("V_V_PERCODE", V_V_PERCODE);
                statement.setString("V_V_PERNAME", V_V_PERNAME);
                statement.setString("V_V_DEPTCODE", V_V_DEPTCODE);
                statement.setString("V_V_EQUCODE", V_V_EQUCODE);
                statement.setString("V_V_IDEA", V_V_IDEA);
                statement.setString("V_V_EQUSITE", V_V_EQUSITE);
                statement.setString("V_V_EQUCHILDCODE", V_V_EQUCHILDCODE);
                statement.setString("V_V_INPERCODE", V_V_INPERCODE);
                statement.setString("V_V_INPERNAME", V_V_INPERNAME);
                statement.setString("V_V_EQUTYPECODE", V_V_EQUTYPECODE);
                statement.setString("V_V_ORGCODE", V_V_ORGCODE);
                statement.setString("V_V_BZ", V_V_BZ);

                statement.registerOutParameter("V_V_GUID", OracleTypes.VARCHAR);
                statement.registerOutParameter("V_INFO", OracleTypes.VARCHAR);
                return statement;
            }
        }, new CallableStatementCallback<Map>() {
            public Map doInCallableStatement(CallableStatement cs)
                    throws SQLException, DataAccessException {
                cs.execute();
                Map result=new HashMap();
                result.put("ret",cs.getObject("V_INFO").toString());
                result.put("V_GUID",cs.getObject("V_V_GUID").toString());
                return result;
            }
        });
    }

    public Map ImportSapWorkOrder(String WorkOrderId,String SapWorkOrderId,String TYPE,String MSG) {
        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call PRO_SAP_WORKORDER_IMPORT(:V_V_WorkOrderId,:V_V_SapWorkOrderId,:V_INFO)}";

                CallableStatement statement = con.prepareCall(sql);
                statement.setString("V_V_WorkOrderId", WorkOrderId);
                statement.setString("V_V_SapWorkOrderId", SapWorkOrderId);
                statement.setString("V_V_TYPE", TYPE);
                statement.setString("V_V_MSG", MSG);
                statement.registerOutParameter("V_INFO", OracleTypes.VARCHAR);
                return statement;
            }
        }, new CallableStatementCallback<Map>() {
            public Map doInCallableStatement(CallableStatement cs)
                    throws SQLException, DataAccessException {
                cs.execute();
                Map result=new HashMap();
                result.put("ret",cs.getObject("V_INFO").toString());
                return result;
            }
        });
    }

}
