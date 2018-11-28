package org.building.pmservice.service.repository;

import oracle.jdbc.OracleTypes;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Repository
public class LoginRepository {

    private JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Transactional
    public String LoginInforXMLData(String USERNAME,String  PASSWORD,String BEGINTIME, String ENDTIME) {
        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call PRO_LOG_TEXT_GET(:V_USERNAME,:V_PSD,:V_BEGINTIME,:V_ENDTIME,:V_INFO,:RET)}";

                CallableStatement statement = con.prepareCall(sql);

                statement.setString("V_USERNAME", USERNAME);
                statement.setString("V_PSD", PASSWORD);
                statement.setString("V_BEGINTIME", BEGINTIME);
                statement.setString("V_ENDTIME", ENDTIME);
                statement.registerOutParameter("V_INFO", OracleTypes.VARCHAR);
                statement.registerOutParameter("RET", OracleTypes.CURSOR);
                return statement;
            }
        }, new CallableStatementCallback<String>() {
            public String doInCallableStatement(CallableStatement cs)
                    throws SQLException, DataAccessException {
                cs.execute();
                ResultSet rs = (ResultSet) cs.getObject("RET");
                String result = cs.getObject("V_INFO").toString();

                Document root= DocumentHelper.createDocument();
                Element WriteDataRequest=root.addElement("LOGINLOG");
                if(result.equals("SUCCESS")){
                    while (rs.next()) {// 转换每行的返回值到Map中
                        Element WriteDataRecord = WriteDataRequest.addElement("Item");
                        WriteDataRecord.addElement("SYSCODE").setText(rs.getString("SYSCODE")==null?"":rs.getString("SYSCODE"));
                        WriteDataRecord.addElement("PROCODE").setText(rs.getString("PROCODE")==null?"":rs.getString("PROCODE"));
                        WriteDataRecord.addElement("LOGTYPE").setText(rs.getString("LOGTYPE")==null?"":rs.getString("LOGTYPE"));
                        WriteDataRecord.addElement("OPERTIME").setText(rs.getString("OPERTIME")==null?"":rs.getString("OPERTIME"));
                        WriteDataRecord.addElement("CLIENT_HOST").setText(rs.getString("CLIENT_HOST")==null?"":rs.getString("CLIENT_HOST"));
                        WriteDataRecord.addElement("CLIENT_IP").setText(rs.getString("CLIENT_IP")==null?"":rs.getString("CLIENT_IP"));
                        WriteDataRecord.addElement("CLIENT_BROWSER").setText(rs.getString("CLIENT_BROWSER")==null?"":rs.getString("CLIENT_BROWSER"));
                        WriteDataRecord.addElement("COMPUTER_TYPE").setText(rs.getString("COMPUTER_TYPE")==null?"":rs.getString("COMPUTER_TYPE"));
                        WriteDataRecord.addElement("OPERUSER").setText(rs.getString("OPERUSER")==null?"":rs.getString("OPERUSER"));
                        WriteDataRecord.addElement("USER_ACCOUNT").setText(rs.getString("USER_ACCOUNT")==null?"":rs.getString("USER_ACCOUNT"));
                        WriteDataRecord.addElement("USER_CODE").setText(rs.getString("USER_CODE")==null?"":rs.getString("USER_CODE"));
                        WriteDataRecord.addElement("ROLE").setText(rs.getString("ROLENAME")==null?"":rs.getString("ROLENAME"));
                        WriteDataRecord.addElement("OPERTYPE").setText(rs.getString("OPERTYPE")==null?"":rs.getString("OPERTYPE"));
                        WriteDataRecord.addElement("ATTTYPE").setText(rs.getString("ATTTYPE")==null?"":rs.getString("ATTTYPE"));
                        WriteDataRecord.addElement("OPERRESULT").setText(rs.getString("OPERRESULT")==null?"":rs.getString("OPERRESULT"));
                        WriteDataRecord.addElement("LOGDES").setText(rs.getString("LOGDES")==null?"":rs.getString("LOGDES"));
                        WriteDataRecord.addElement("CLIENT_SYS").setText(rs.getString("CLIENT_SYS")==null?"":rs.getString("CLIENT_SYS"));
                    }
                }else{
                    WriteDataRequest.addElement("FLAG").setText("1");
                }
                return root.asXML();
            }
        });
    }

}
