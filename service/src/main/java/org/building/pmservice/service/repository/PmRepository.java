package org.building.pmservice.service.repository;

import oracle.jdbc.OracleTypes;
import org.building.pmservice.service.enity.LoginInforData;
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
import java.util.*;


@Repository
public class PmRepository {
    @Autowired
    @Qualifier("oracleJdbcTemplate")
    private NamedParameterJdbcTemplate oracleTemplate;

    @Autowired
    @Qualifier("sqlServiceJdbcTemplate")
    private NamedParameterJdbcTemplate sqlServerTemplate;

    private JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Transactional(value = "sqlServerTransactionManager")
    public List<Map<String, Object>> getYearPlanList(String V_GUID) {

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT P.* ,O.V_WBS_NUM,R.V_WXJH_ORGGUID" +
                " FROM pm_03_plan_project P " +
                " LEFT JOIN BASE_DEPT O ON P.V_ORGCODE=O.V_DEPTCODE" +
                " LEFT JOIN PM_03_PLAN_REPAIR_DEPT R ON P.V_GUID=R.V_GUID where P.V_GUID =:v_guid");

        Map<String, Object> query = new HashMap<String, Object>();
        query.put("v_guid", V_GUID);

        List<Map<String, Object>> list = oracleTemplate.queryForList(sql.toString(), query);

        String v_year = "";
        String v_orgname = "";
        String v_project_code = "";
        String v_project_name = "";
        UUID v_specialty = null;
        String v_specialtyman = "";
        UUID v_wxtypecode = null;
        UUID v_jhlb = null;
        UUID v_sclb = null;
        UUID v_cpzl = null;
        UUID v_cpgx = null;
        String v_sgfs = "";
        String v_sfxj = "";
        String v_content = "";
        String v_moneybudget = "";
        String v_bdate = "";
        String v_edate = "";
        String v_inman = "";
        String v_indate = "";
        String v_sumtime = "";
        String V_WBS_NUM = "";
        UUID V_WXJH_ORGGUID = null;

        if (list != null && list.size() > 0) {
            try {

                if (list.get(0).get("V_YEAR") != null && !"".equals(list.get(0).get("V_YEAR"))) {
                    v_year = list.get(0).get("V_YEAR").toString();//年份
                }

                if (list.get(0).get("V_ORGNAME") != null && !"".equals(list.get(0).get("V_ORGNAME"))) {
                    v_orgname = list.get(0).get("V_ORGNAME").toString();//申请厂矿名称
                }
                if (list.get(0).get("V_PORJECT_CODE") != null && !"".equals(list.get(0).get("V_PORJECT_CODE"))) {
                    v_project_code = list.get(0).get("V_PORJECT_CODE").toString();//工程项目编码
                }
                if (list.get(0).get("V_PORJECT_NAME") != null && !"".equals(list.get(0).get("V_PORJECT_NAME"))) {
                    v_project_name = list.get(0).get("V_PORJECT_NAME").toString();//工程项目名称
                }
                if (list.get(0).get("V_SPECIALTY") != null && !"".equals(list.get(0).get("V_SPECIALTY"))) {

                    v_specialty = UUID.fromString(list.get(0).get("V_SPECIALTY").toString());//专业编码
                }
                if (list.get(0).get("V_SPECIALTYMAN") != null && !"".equals(list.get(0).get("V_SPECIALTYMAN"))) {
                    v_specialtyman = list.get(0).get("V_SPECIALTYMAN").toString();//专业负责人姓名
                }
                if (list.get(0).get("V_WXTYPECODE") != null && !"".equals(list.get(0).get("V_WXTYPECODE"))) {
                    v_wxtypecode = UUID.fromString(list.get(0).get("V_WXTYPECODE").toString());//维修类型编码
                }
                if (list.get(0).get("V_JHLB") != null && !"".equals(list.get(0).get("V_JHLB"))) {
                    v_jhlb = UUID.fromString(list.get(0).get("V_JHLB").toString());//计划类别
                }
                if (list.get(0).get("V_SCLB") != null && !"".equals(list.get(0).get("V_SCLB"))) {
                    v_sclb = UUID.fromString(list.get(0).get("V_SCLB").toString());//生产类别
                }
                if (list.get(0).get("V_CPZL") != null && !"".equals(list.get(0).get("V_CPZL"))) {
                    v_cpzl = UUID.fromString(list.get(0).get("V_CPZL").toString());//产品种类
                }
                if (list.get(0).get("V_CPGX") != null && !"".equals(list.get(0).get("V_CPGX"))) {
                    v_cpgx = UUID.fromString(list.get(0).get("V_CPGX").toString());//产品工序
                }
                if (list.get(0).get("V_SGFS") != null && !"".equals(list.get(0).get("V_SGFS"))) {
                    v_sgfs = list.get(0).get("V_SGFS").toString();//施工方式
                }
                if (list.get(0).get("V_SFXJ") != null && !"".equals(list.get(0).get("V_SFXJ"))) {
                    v_sfxj = list.get(0).get("V_SFXJ").toString();//是否修旧
                }
                if (list.get(0).get("V_CONTENT") != null && !"".equals(list.get(0).get("V_CONTENT"))) {
                    v_content = list.get(0).get("V_CONTENT").toString();//维修内容
                }
                if (list.get(0).get("V_MONEYBUDGET") != null && !"".equals(list.get(0).get("V_MONEYBUDGET"))) {
                    v_moneybudget = list.get(0).get("V_MONEYBUDGET").toString();//工程总预算(万元)(Number)
                }
                if (list.get(0).get("V_BDATE") != null && !"".equals(list.get(0).get("V_BDATE"))) {
                    v_bdate = list.get(0).get("V_BDATE").toString();//预计开工时间（DATE）
                }
                if (list.get(0).get("V_EDATE") != null && !"".equals(list.get(0).get("V_EDATE"))) {
                    v_edate = list.get(0).get("V_EDATE").toString();//预计竣工时间（DATE）
                }
                if (list.get(0).get("V_INMAN") != null && !"".equals(list.get(0).get("V_INMAN"))) {
                    v_inman = list.get(0).get("V_INMAN").toString();//录入人姓名
                }
                if (list.get(0).get("V_INDATE") != null && !"".equals(list.get(0).get("V_INDATE"))) {
                    v_indate = list.get(0).get("V_INDATE").toString();//系统时间(Date)
                }
                if (list.get(0).get("V_SUMTIME") != null && !"".equals(list.get(0).get("V_SUMTIME"))) {
                    v_sumtime = list.get(0).get("V_SUMTIME").toString();//合计工时
                }
                if (list.get(0).get("V_WBS_NUM") != null && !"".equals(list.get(0).get("V_WBS_NUM"))) {
                    V_WBS_NUM = list.get(0).get("V_WBS_NUM").toString();//单位序号
                }

                if (list.get(0).get("V_WXJH_ORGGUID") != null && !"".equals(list.get(0).get("V_WXJH_ORGGUID"))) {
                    V_WXJH_ORGGUID = UUID.fromString(list.get(0).get("V_WXJH_ORGGUID").toString());//检修单位
                }

                //插入sqlserver数据库
                String insertSql = "insert into dbo.akwx_njh(ckmc,ckxh,jhnf,gcmc,gcbh,zybm,gcnr,tzzj," +
                        "sgfsbm,sfxj,wxlxbm,jhlbbm,gxbm,sclbbm,cpzlbm,yjkgsj,yjjgsj," +
                        "yjgq,fzr,qcrxm,qcrq,xmzt,yjhsy,jhxz,zcyz,qcrbm,qcrjs,dqhj,bjf,clf,cxf,wwf,xjxf,sjjlf,qtf) " +
                        "values(:v_orgname,:v_orgcode,:v_year,:v_project_name,:v_project_code,:v_specialty,:v_content,:v_moneybudget," +
                        ":v_sgfs,:v_sfxj,:v_wxtypecode,:v_jhlb,:v_cpgx,:v_sclb,:v_cpzl,:v_bdate,:v_edate," +
                        ":v_sumtime,:v_specialtyman,:v_inman,:v_indate,:v_xmzt,:v_yjhsy,:v_jhxz,:v_zcyz,:v_qcrbm,:v_qcrjs,:v_dqhj,:v_bjf,:v_clf,:v_cxf,:v_wwf,:v_xjxf,:v_sjjlf,:v_qtf)";
                Map<String, Object> insertMap = new HashMap<String, Object>();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                insertMap.put("v_orgname", v_orgname);
                insertMap.put("v_orgcode", V_WBS_NUM);
                insertMap.put("v_year", v_year);
                insertMap.put("v_project_name", v_project_name);
                insertMap.put("v_project_code", v_project_code);
                insertMap.put("v_specialty", v_specialty);
                insertMap.put("v_content", v_content);
                insertMap.put("v_moneybudget", v_moneybudget);

                insertMap.put("v_sgfs", v_sgfs);
                insertMap.put("v_sfxj", v_sfxj);
                insertMap.put("v_wxtypecode", v_wxtypecode);
                insertMap.put("v_jhlb", v_jhlb);
                insertMap.put("v_cpgx", v_cpgx);
                insertMap.put("v_sclb", v_sclb);
                insertMap.put("v_cpzl", v_cpzl);

                insertMap.put("v_bjf", 0);
                insertMap.put("v_clf", 0);
                insertMap.put("v_cxf", 0);
                insertMap.put("v_wwf", v_moneybudget);
                insertMap.put("v_xjxf", 0);
                insertMap.put("v_sjjlf", 0);
                insertMap.put("v_qtf", 0);

                if (v_bdate != null && !"".equals(v_bdate)) {
                    v_bdate = v_bdate.substring(0, 10);
                }
                if (v_edate != null && !"".equals(v_edate)) {
                    v_edate = v_bdate.substring(0, 10);
                }
                insertMap.put("v_bdate", v_bdate);
                insertMap.put("v_edate", v_edate);


                insertMap.put("v_sumtime", v_sumtime);
                insertMap.put("v_specialtyman", "厂矿计划员");
                insertMap.put("v_inman", "厂矿计划员");
                insertMap.put("v_indate", v_indate);
                insertMap.put("v_xmzt", 1);
                insertMap.put("v_yjhsy", 0);

                insertMap.put("v_jhxz", 0);
                insertMap.put("v_zcyz", 2000);
                //insertMap.put("v_tjdw1",V_WXJH_ORGGUID);
                UUID v_qcrbm = UUID.fromString("A0C4A846-5FF9-4609-8173-87328978AEC4");
                insertMap.put("v_qcrbm", v_qcrbm);
                insertMap.put("v_qcrjs", "厂矿计划员");
                insertMap.put("v_dqhj", 0);


                sqlServerTemplate.update(insertSql, insertMap);

            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
            }


        }
        return list;

    }

    @Transactional(value = "sqlServerTransactionManager")
    public List<Map<String, Object>> SetMonthPlanList(String V_GUID) {

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT P.* ,O.V_WBS_NUM,R.V_WXJH_ORGGUID" +
                " FROM pm_03_plan_project P " +
                " LEFT JOIN BASE_DEPT O ON P.V_ORGCODE=O.V_DEPTCODE" +
                " LEFT JOIN PM_03_PLAN_REPAIR_DEPT R ON P.V_GUID=R.V_GUID where P.V_GUID =:v_guid");

        Map<String, Object> query = new HashMap<String, Object>();
        query.put("v_guid", V_GUID);

        List<Map<String, Object>> list = oracleTemplate.queryForList(sql.toString(), query);

        String v_year = "";
        UUID v_guid = null;
        String v_orgname = "";
        String v_project_code = "";
        String v_project_name = "";
        UUID v_specialty = null;
        String v_specialtyman = "";
        UUID v_wxtypecode = null;
        UUID v_jhlb = null;
        UUID v_sclb = null;
        UUID v_cpzl = null;
        UUID v_cpgx = null;
        String v_sgfs = "";
        String v_sfxj = "";
        String v_content = "";
        String v_moneybudget = "";
        String v_bdate = "";
        String v_edate = "";
        String v_inman = "";
        String v_indate = "";
        String v_sumtime = "";
        String V_WBS_NUM = "";
        UUID V_WXJH_ORGGUID = null;

        if (list != null && list.size() > 0) {
            try {

                if (list.get(0).get("V_GUID") != null && !"".equals(list.get(0).get("V_GUID"))) {
                    v_guid = UUID.fromString(list.get(0).get("V_GUID").toString());//年份
                }
                if (list.get(0).get("V_YEAR") != null && !"".equals(list.get(0).get("V_YEAR"))) {
                    v_year = list.get(0).get("V_YEAR").toString();//年份
                }
                if (list.get(0).get("V_ORGNAME") != null && !"".equals(list.get(0).get("V_ORGNAME"))) {
                    v_orgname = list.get(0).get("V_ORGNAME").toString();//申请厂矿名称
                }
                if (list.get(0).get("V_PORJECT_CODE") != null && !"".equals(list.get(0).get("V_PORJECT_CODE"))) {
                    v_project_code = list.get(0).get("V_PORJECT_CODE").toString();//工程项目编码
                }
                if (list.get(0).get("V_PORJECT_NAME") != null && !"".equals(list.get(0).get("V_PORJECT_NAME"))) {
                    v_project_name = list.get(0).get("V_PORJECT_NAME").toString();//工程项目名称
                }
                if (list.get(0).get("V_SPECIALTY") != null && !"".equals(list.get(0).get("V_SPECIALTY"))) {

                    v_specialty = UUID.fromString(list.get(0).get("V_SPECIALTY").toString());//专业编码
                }
                if (list.get(0).get("V_SPECIALTYMAN") != null && !"".equals(list.get(0).get("V_SPECIALTYMAN"))) {
                    v_specialtyman = list.get(0).get("V_SPECIALTYMAN").toString();//专业负责人姓名
                }
                if (list.get(0).get("V_WXTYPECODE") != null && !"".equals(list.get(0).get("V_WXTYPECODE"))) {
                    v_wxtypecode = UUID.fromString(list.get(0).get("V_WXTYPECODE").toString());//维修类型编码
                }
                if (list.get(0).get("V_JHLB") != null && !"".equals(list.get(0).get("V_JHLB"))) {
                    v_jhlb = UUID.fromString(list.get(0).get("V_JHLB").toString());//计划类别
                }
                if (list.get(0).get("V_SCLB") != null && !"".equals(list.get(0).get("V_SCLB"))) {
                    v_sclb = UUID.fromString(list.get(0).get("V_SCLB").toString());//生产类别
                }
                if (list.get(0).get("V_CPZL") != null && !"".equals(list.get(0).get("V_CPZL"))) {
                    v_cpzl = UUID.fromString(list.get(0).get("V_CPZL").toString());//产品种类
                }
                if (list.get(0).get("V_CPGX") != null && !"".equals(list.get(0).get("V_CPGX"))) {
                    v_cpgx = UUID.fromString(list.get(0).get("V_CPGX").toString());//产品工序
                }
                if (list.get(0).get("V_SGFS") != null && !"".equals(list.get(0).get("V_SGFS"))) {
                    v_sgfs = list.get(0).get("V_SGFS").toString();//施工方式
                }
                if (list.get(0).get("V_SFXJ") != null && !"".equals(list.get(0).get("V_SFXJ"))) {
                    v_sfxj = list.get(0).get("V_SFXJ").toString();//是否修旧
                }
                if (list.get(0).get("V_CONTENT") != null && !"".equals(list.get(0).get("V_CONTENT"))) {
                    v_content = list.get(0).get("V_CONTENT").toString();//维修内容
                }
                if (list.get(0).get("V_MONEYBUDGET") != null && !"".equals(list.get(0).get("V_MONEYBUDGET"))) {
                    v_moneybudget = list.get(0).get("V_MONEYBUDGET").toString();//工程总预算(万元)(Number)
                }
                if (list.get(0).get("V_BDATE") != null && !"".equals(list.get(0).get("V_BDATE"))) {
                    v_bdate = list.get(0).get("V_BDATE").toString();//预计开工时间（DATE）
                }
                if (list.get(0).get("V_EDATE") != null && !"".equals(list.get(0).get("V_EDATE"))) {
                    v_edate = list.get(0).get("V_EDATE").toString();//预计竣工时间（DATE）
                }
                if (list.get(0).get("V_INMAN") != null && !"".equals(list.get(0).get("V_INMAN"))) {
                    v_inman = list.get(0).get("V_INMAN").toString();//录入人姓名
                }
                if (list.get(0).get("V_INDATE") != null && !"".equals(list.get(0).get("V_INDATE"))) {
                    v_indate = list.get(0).get("V_INDATE").toString();//系统时间(Date)
                }
                if (list.get(0).get("V_SUMTIME") != null && !"".equals(list.get(0).get("V_SUMTIME"))) {
                    v_sumtime = list.get(0).get("V_SUMTIME").toString();//合计工时
                }
                if (list.get(0).get("V_WBS_NUM") != null && !"".equals(list.get(0).get("V_WBS_NUM"))) {
                    V_WBS_NUM = list.get(0).get("V_WBS_NUM").toString();//单位序号
                }

                if (list.get(0).get("V_WXJH_ORGGUID") != null && !"".equals(list.get(0).get("V_WXJH_ORGGUID"))) {
                    V_WXJH_ORGGUID = UUID.fromString(list.get(0).get("V_WXJH_ORGGUID").toString());//检修单位
                }

                //插入sqlserver数据库
                String insertSql = "insert into dbo.akwx_yjh(uid,ckmc,ckxh,jhnf,gcmc,gcbh,zybm,gcnr,tzzj," +
                        "sgfsbm,sfxj,wxlxbm,jhlbbm,gxbm,sclbbm,cpzlbm,yjkgsj,yjjgsj," +
                        "yjgq,fzr,qcrxm,qcrq,xmzt,yjhsy,jhxz,zcyz,qcrbm,qcrjs,dqhj,bjf,clf,cxf,wwf,xjxf,sjjlf,qtf) " +
                        "values(:v_guid:v_orgname,:v_orgcode,:v_year,:v_project_name,:v_project_code,:v_specialty,:v_content,:v_moneybudget," +
                        ":v_sgfs,:v_sfxj,:v_wxtypecode,:v_jhlb,:v_cpgx,:v_sclb,:v_cpzl,:v_bdate,:v_edate," +
                        ":v_sumtime,:v_specialtyman,:v_inman,:v_indate,:v_xmzt,:v_yjhsy,:v_jhxz,:v_zcyz,:v_qcrbm,:v_qcrjs,:v_dqhj,:v_bjf,:v_clf,:v_cxf,:v_wwf,:v_xjxf,:v_sjjlf,:v_qtf)";
                Map<String, Object> insertMap = new HashMap<String, Object>();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                insertMap.put("v_guid", v_guid);
                insertMap.put("v_orgname", v_orgname);
                insertMap.put("v_orgcode", V_WBS_NUM);
                insertMap.put("v_year", v_year);
                insertMap.put("v_project_name", v_project_name);
                insertMap.put("v_project_code", v_project_code);
                insertMap.put("v_specialty", v_specialty);
                insertMap.put("v_content", v_content);
                insertMap.put("v_moneybudget", v_moneybudget);

                insertMap.put("v_sgfs", v_sgfs);
                insertMap.put("v_sfxj", v_sfxj);
                insertMap.put("v_wxtypecode", v_wxtypecode);
                insertMap.put("v_jhlb", v_jhlb);
                insertMap.put("v_cpgx", v_cpgx);
                insertMap.put("v_sclb", v_sclb);
                insertMap.put("v_cpzl", v_cpzl);

                insertMap.put("v_bjf", 0);
                insertMap.put("v_clf", 0);
                insertMap.put("v_cxf", 0);
                insertMap.put("v_wwf", v_moneybudget);
                insertMap.put("v_xjxf", 0);
                insertMap.put("v_sjjlf", 0);
                insertMap.put("v_qtf", 0);

                if (v_bdate != null && !"".equals(v_bdate)) {
                    v_bdate = v_bdate.substring(0, 10);
                }
                if (v_edate != null && !"".equals(v_edate)) {
                    v_edate = v_bdate.substring(0, 10);
                }
                insertMap.put("v_bdate", v_bdate);
                insertMap.put("v_edate", v_edate);


                insertMap.put("v_sumtime", v_sumtime);
                insertMap.put("v_specialtyman", "厂矿计划员");
                insertMap.put("v_inman", "厂矿计划员");
                insertMap.put("v_indate", v_indate);
                insertMap.put("v_xmzt", 1);
                insertMap.put("v_yjhsy", 0);

                insertMap.put("v_jhxz", 0);
                insertMap.put("v_zcyz", 2000);
                //insertMap.put("v_tjdw1",V_WXJH_ORGGUID);
                UUID v_qcrbm = UUID.fromString("A0C4A846-5FF9-4609-8173-87328978AEC4");
                insertMap.put("v_qcrbm", v_qcrbm);
                insertMap.put("v_qcrjs", "厂矿计划员");
                insertMap.put("v_dqhj", 0);


                sqlServerTemplate.update(insertSql, insertMap);

            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
            }


        }
        return list;

    }


    @Transactional
    public String LoginInforXMLData(LoginInforData loginInforData) {
        return template.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con)
                    throws SQLException {
                String sql = "{call PRO_LOG_TEXT_GET(:V_USERNAME,:V_PSD,:V_BEGINTIME,:V_ENDTIME,:RET)}";

                CallableStatement statement = con.prepareCall(sql);

                statement.setString("V_USERNAME", loginInforData.getUSERNAME());
                statement.setString("V_PSD", loginInforData.getPASSWORD());
                statement.setString("V_BEGINTIME", loginInforData.getBEGINTIME());
                statement.setString("V_ENDTIME", loginInforData.getENDTIME());
                statement.registerOutParameter("RET", OracleTypes.CURSOR);
                return statement;
            }
        }, new CallableStatementCallback<String>() {
            public String doInCallableStatement(CallableStatement cs)
                    throws SQLException, DataAccessException {
                cs.execute();
                ResultSet rs = (ResultSet) cs.getObject("RET");

                Document root= DocumentHelper.createDocument();

                Element WriteDataRequest=root.addElement("LOGINLOG");


                while (rs.next()) {// 转换每行的返回值到Map中
                    Element WriteDataRecord = WriteDataRequest.addElement("Item");
                    WriteDataRecord.addElement("SYSCODE").setText(rs.getString("SYSCODE"));
                    WriteDataRecord.addElement("PROCODE").setText(rs.getString("PROCODE"));
                    WriteDataRecord.addElement("LOGTYPE").setText(rs.getString("LOGTYPE"));
                    WriteDataRecord.addElement("OPERTIME").setText(rs.getString("OPERTIME"));
                    WriteDataRecord.addElement("CLIENT_HOST").setText(rs.getString("CLIENT_HOST"));
                    WriteDataRecord.addElement("CLIENT_IP").setText(rs.getString("CLIENT_IP"));
                    WriteDataRecord.addElement("CLIENT_BROWSER").setText(rs.getString("CLIENT_BROWSER"));
                    WriteDataRecord.addElement("COMPUTER_TYPE").setText(rs.getString("COMPUTER_TYPE"));
                    WriteDataRecord.addElement("OPERUSER").setText(rs.getString("OPERUSER"));
                    WriteDataRecord.addElement("USER_ACCOUNT").setText(rs.getString("USER_ACCOUNT"));
                    WriteDataRecord.addElement("USER_CODE").setText(rs.getString("USER_CODE"));
                    WriteDataRecord.addElement("ROLE").setText(rs.getString("ROLENAME"));
                    WriteDataRecord.addElement("OPERTYPE").setText(rs.getString("OPERTYPE"));
                    WriteDataRecord.addElement("ATTTYPE").setText(rs.getString("ATTTYPE"));
                    WriteDataRecord.addElement("OPERRESULT").setText(rs.getString("OPERRESULT"));
                    WriteDataRecord.addElement("LOGDES").setText(rs.getString("LOGDES"));
                    WriteDataRecord.addElement("CLIENT_SYS").setText(rs.getString("CLIENT_SYS"));
                }
                return root.asXML();
            }
        });
    }

}
