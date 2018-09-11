package org.building.pmservice.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Repository
public class PmRepository {
    @Autowired
    @Qualifier("oracleJdbcTemplate")
    private NamedParameterJdbcTemplate oracleNameTemplate;

    @Autowired
    @Qualifier("secondJdbcTemplate")
    private NamedParameterJdbcTemplate sqlNameTemplate;


    @Transactional
    public List<Map<String, Object>> getYearPlanList(String V_GUID){

        StringBuffer sql = new StringBuffer();
        sql.append("select * from PM_03_PLAN_PROJECT where V_GUID =:v_guid");

        Map<String, Object> query = new HashMap<String, Object>();
        query.put("v_guid", V_GUID);

        List<Map<String, Object>> list = oracleNameTemplate.queryForList(sql.toString(), query);

        String v_year ="";
        String v_orgcode="";
        String v_orgname = "";
        String v_project_code = "";
        String v_project_name = "";
        String v_specialty = "";
        String v_specialtyman = "";
        String v_wxtypecode = "";
        String v_jhlb = "";
        String v_sclb = "";
        String v_cpzl = "";
        String v_cpgx = "";
        String v_sgfs = "";
        String v_sfxj = "";
        String v_content = "";
        String v_moneybudget = "";
        String v_bdate = "";
        String v_edate = "";
        String v_inman = "";
        String v_indate = "";
        String v_sumtime = "";

        if(list!=null&&list.size()>0){
            try{

                if(list.get(0).get("V_YEAR")!=null&&!"".equals(list.get(0).get("V_YEAR"))){
                    v_year = list.get(0).get("V_YEAR").toString();//年份
                }
                if(list.get(0).get("V_ORGCODE")!=null&&!"".equals(list.get(0).get("V_ORGCODE"))){
                    v_orgcode= list.get(0).get("V_ORGCODE").toString();//申请厂矿编码
                }
                if(list.get(0).get("V_ORGNAME")!=null&&!"".equals(list.get(0).get("V_ORGNAME"))){
                    v_orgname= list.get(0).get("V_ORGNAME").toString();//申请厂矿名称
                }
                if(list.get(0).get("V_PORJECT_CODE")!=null&&!"".equals(list.get(0).get("V_PORJECT_CODE"))){
                    v_project_code = list.get(0).get("V_PORJECT_CODE").toString();//工程项目编码
                }
                if(list.get(0).get("V_PORJECT_NAME")!=null&&!"".equals(list.get(0).get("V_PORJECT_NAME"))){
                    v_project_name = list.get(0).get("V_PORJECT_NAME").toString();//工程项目名称
                }
                if(list.get(0).get("V_SPECIALTY")!=null&&!"".equals(list.get(0).get("V_SPECIALTY"))){
                    v_specialty = list.get(0).get("V_SPECIALTY").toString();//专业编码
                }
                if(list.get(0).get("V_SPECIALTYMAN")!=null&&!"".equals(list.get(0).get("V_SPECIALTYMAN"))){
                    v_specialtyman = list.get(0).get("V_SPECIALTYMAN").toString();//专业负责人姓名
                }
                if(list.get(0).get("V_WXTYPECODE")!=null&&!"".equals(list.get(0).get("V_WXTYPECODE"))){
                    v_wxtypecode = list.get(0).get("V_WXTYPECODE").toString();//维修类型编码
                }
                if(list.get(0).get("V_JHLB")!=null&&!"".equals(list.get(0).get("V_JHLB"))){
                    v_jhlb = list.get(0).get("V_JHLB").toString();//计划类别
                }
                if(list.get(0).get("V_SCLB")!=null&&!"".equals(list.get(0).get("V_SCLB"))){
                    v_sclb = list.get(0).get("V_SCLB").toString();//生产类别
                }
                if(list.get(0).get("V_CPZL")!=null&&!"".equals(list.get(0).get("V_CPZL"))){
                    v_cpzl = list.get(0).get("V_CPZL").toString();//产品种类
                }
                if(list.get(0).get("V_CPGX")!=null&&!"".equals(list.get(0).get("V_CPGX"))){
                    v_cpgx = list.get(0).get("V_CPGX").toString();//产品工序
                }
                if(list.get(0).get("V_SGFS")!=null&&!"".equals(list.get(0).get("V_SGFS"))){
                    v_sgfs = list.get(0).get("V_SGFS").toString();//施工方式
                }
                if(list.get(0).get("V_SFXJ")!=null&&!"".equals(list.get(0).get("V_SFXJ"))){
                    v_sfxj = list.get(0).get("V_SFXJ").toString();//是否修旧
                }
                if(list.get(0).get("V_CONTENT")!=null&&!"".equals(list.get(0).get("V_CONTENT"))){
                    v_content = list.get(0).get("V_CONTENT").toString();//维修内容
                }
                if(list.get(0).get("V_MONEYBUDGET")!=null&&!"".equals(list.get(0).get("V_MONEYBUDGET"))){
                    v_moneybudget = list.get(0).get("V_MONEYBUDGET").toString();//工程总预算(万元)(Number)
                }
                if(list.get(0).get("V_BDATE")!=null&&!"".equals(list.get(0).get("V_BDATE"))){
                    v_bdate = list.get(0).get("V_BDATE").toString();//预计开工时间（DATE）
                }
                if(list.get(0).get("V_EDATE")!=null&&!"".equals(list.get(0).get("V_EDATE"))){
                    v_edate = list.get(0).get("V_EDATE").toString();//预计竣工时间（DATE）
                }
                if(list.get(0).get("V_INMAN")!=null&&!"".equals(list.get(0).get("V_INMAN"))){
                    v_inman = list.get(0).get("V_INMAN").toString();//录入人姓名
                }
                if(list.get(0).get("V_INDATE")!=null&&!"".equals(list.get(0).get("V_INDATE"))){
                    v_indate = list.get(0).get("V_INDATE").toString();//系统时间(Date)
                }
                if(list.get(0).get("V_SUMTIME")!=null&&!"".equals(list.get(0).get("V_SUMTIME"))){
                    v_sumtime = list.get(0).get("V_SUMTIME").toString();//合计工时
                }

                //插入sqlserver数据库
                String insertSql = "insert into dbo.akwx_njh(ckmc,ckxh,jhnf,gcmc,gcbh,zybm,gcnr,tzzj," +
                        "sgfsbm,sfxj,wxlxbm,jhlbbm,gxbm,sclbbm,cpzlbm,yjkgsj,yjjgsj," +
                        "yjgq,fzr,qcrxm,qcrq,xmzt,yjhsy) " +
                        "values(:v_orgname,:v_orgcode,:v_year,:v_project_name,:v_project_code,:v_specialty,:v_content,:v_moneybudget," +
                        ":v_sgfs,:v_sfxj,:v_wxtypecode,:v_jhlb,:v_cpgx,:v_sclb,:v_cpzl,:v_bdate,:v_edate," +
                        ":v_sumtime,:v_specialtyman,:v_inman,:v_indate,:v_xmzt,:v_yjhsy)";

                Map<String, Object> insertMap = new HashMap<String, Object>();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                insertMap.put("v_orgname", v_orgname);
                insertMap.put("v_orgcode", v_orgcode);
                insertMap.put("v_year", v_year);
                insertMap.put("v_project_name", v_project_name);
                insertMap.put("v_project_code", v_project_code);
                insertMap.put("v_specialty", v_specialty);
                insertMap.put("v_content", v_content);
                insertMap.put("v_moneybudget", Double.valueOf(v_moneybudget));

                insertMap.put("v_sgfs", v_sgfs);
                insertMap.put("v_sfxj", v_sfxj);
                insertMap.put("v_wxtypecode",v_wxtypecode);
                insertMap.put("v_jhlb",v_jhlb);
                insertMap.put("v_cpgx",v_cpgx);
                insertMap.put("v_sclb",v_sclb);
                insertMap.put("v_cpzl",v_cpzl);
                if(v_bdate!=null&&!"".equals(v_bdate)){
                    v_bdate = v_bdate.substring(0,10);
                }
                if(v_edate!=null&&!"".equals(v_edate)){
                    v_edate = v_bdate.substring(0,10);
                }
                insertMap.put("v_bdate",v_bdate);
                insertMap.put("v_edate",v_edate);


                insertMap.put("v_sumtime",v_sumtime);
                insertMap.put("v_specialtyman",v_specialtyman);
                insertMap.put("v_inman",v_inman);
                insertMap.put("v_indate",v_indate);
                insertMap.put("v_xmzt",0);
                insertMap.put("v_yjhsy",0);


                sqlNameTemplate.update(insertSql,insertMap);

            }catch (Exception e){
                e.printStackTrace();
                e.getMessage();
            }


        }
        return list;

    }


}
