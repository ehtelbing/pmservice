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
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cxy on 2018/6/21.
 */
@Repository
public class WorkOrderUpFalgRepository {
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
    public String PRO_PM_WORKORDER_SAP_UPFALG(String V_V_SAPID, String V_V_SAPFLAG, String V_V_OTHER1, String V_V_OTHER2) {
        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call PRO_PM_WORKORDER_SAP_UPFALG(:V_V_SAPID,:V_V_SAPFLAG,:V_V_OTHER1,:V_V_OTHER2,:V_INFO)}";

                CallableStatement statement = con.prepareCall(sql);
                statement.setString("V_V_SAPID", V_V_SAPID);
                statement.setString("V_V_SAPFLAG", V_V_SAPFLAG);
                statement.setString("V_V_OTHER1", V_V_OTHER1);
                statement.setString("V_V_OTHER2", V_V_OTHER2);
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

    @Transactional
    public String PRO_PM_WORKORDER_SPARE_MM_SET(String v_V_ORDERGUID, String v_V_ORDERID, String billcode, String vch_sparepart_code, String vch_sparepart_name, String vch_type, String vch_unit, String price, String f_number, String billType) throws SQLException {

        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call PRO_PM_WORKORDER_SPARE_MM_SET(:V_V_ORDERGUID,:V_V_ORDERID,:V_BILLCODE,:V_VCH_SPAREPART_CODE,:V_VCH_SPAREPART_NAME,:V_VCH_TYPE,:V_VCH_UNIT,:V_PRICE,:V_F_NUMBER,:V_BILLTYPE,:V_CURSOR)}";

                CallableStatement statement = con.prepareCall(sql);
                statement.setString("V_V_ORDERGUID", v_V_ORDERGUID);
                statement.setString("V_V_ORDERID", v_V_ORDERID);
                statement.setString("V_BILLCODE", billcode);
                statement.setString("V_VCH_SPAREPART_CODE", vch_sparepart_code);
                statement.setString("V_VCH_SPAREPART_NAME", vch_sparepart_name);
                statement.setString("V_VCH_TYPE", vch_type);
                statement.setString("V_VCH_UNIT", vch_unit);
                statement.setDouble("V_PRICE", new Double(price));
                statement.setDouble("V_F_NUMBER", new Double(f_number));
                statement.setString("V_BILLTYPE", billType);
                statement.registerOutParameter("V_CURSOR", OracleTypes.VARCHAR);
                return statement;
            }
        }, new CallableStatementCallback<String>() {
            public String doInCallableStatement(CallableStatement cs)
                    throws SQLException, DataAccessException {
                cs.execute();
                String result = cs.getObject("V_CURSOR").toString();
                return result;
            }
        });
    }

    @Transactional
    public Map PRO_SAP_WORKORDER_GET(String V_SAPID) throws SQLException {

        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call PRO_SAP_WORKORDER_GET(:V_SAPID,:V_INFO,:V_V_ORDERGUID,:V_V_ORDERID)}";

                CallableStatement statement = con.prepareCall(sql);
                statement.setString("V_SAPID", V_SAPID);
                statement.registerOutParameter("V_INFO", OracleTypes.VARCHAR);
                statement.registerOutParameter("V_V_ORDERGUID", OracleTypes.VARCHAR);
                statement.registerOutParameter("V_V_ORDERID", OracleTypes.VARCHAR);
                return statement;
            }
        }, new CallableStatementCallback<Map>() {
            public Map doInCallableStatement(CallableStatement cs)
                    throws SQLException, DataAccessException {
                cs.execute();
                Map result = new HashMap();
                result.put("V_INFO", cs.getString("V_INFO"));
                result.put("V_V_ORDERGUID", cs.getString("V_V_ORDERGUID"));
                result.put("V_V_ORDERID", cs.getString("V_V_ORDERID"));
                return result;
            }
        });
    }

    @Transactional
    public String PRO_PM_WORKORDER_SPARE_UPDATE(String v_v_orderguid) throws SQLException {

        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call PRO_PM_WORKORDER_SPARE_UPDATE(:V_V_ORDERGUID,:V_CURSOR)}";

                CallableStatement statement = con.prepareCall(sql);
                statement.setString("V_V_ORDERGUID", v_v_orderguid);
                statement.registerOutParameter("V_CURSOR", OracleTypes.VARCHAR);
                return statement;
            }
        }, new CallableStatementCallback<String>() {
            public String doInCallableStatement(CallableStatement cs)
                    throws SQLException, DataAccessException {
                cs.execute();
                String ret = "";
                ret = (String) cs.getObject("V_CURSOR");
                return ret;
            }
        });
    }
}
