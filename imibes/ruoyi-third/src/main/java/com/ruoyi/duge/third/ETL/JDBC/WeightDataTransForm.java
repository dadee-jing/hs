package com.ruoyi.duge.third.ETL.JDBC;

import com.ruoyi.duge.third.ETL.JDBC.utils.ConfigManager;

import java.sql.*;
import java.util.*;

public class WeightDataTransForm {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private List<Map<String,Object>> params = new ArrayList<>();
    private Map<String,Object> paramMap;
    private final String QUERIES = "SELECT "+
    "wd.id,"+
    "wd.station_id,"+
    "wd.truck_number AS plate_no,"+
    "IF(wd.truck_corlor='蓝',1,2) AS plate_color,"+
    "UPPER(MD5(UUID())) AS record_id,"+
    "wd.lane_mid AS lane_no,"+
    "wd.axle_count AS axle_num,"+
    "wd.speed AS vehicle_speed,"+
    "wd.weight AS vehicle_weight,"+
    "wd.height/1000 AS vehicle_height,"+
    "wd.width/1000 AS vehicle_width,"+
    "wd.length/1000 AS vehicle_length,"+
    "IF(wd.speed > dsi.`speed_limit`, 1, 0) AS over_speed,"+
    "IF(wd.over_weight > 0, 1, 0) AS over_weight,"+
    "IF(wd.height > 4000, 1, 0) AS over_height,"+
    "IF(wd.width > 2550, 1, 0) AS over_width,"+
    "IF(wd.length > 18100, 1, 0) AS over_length,"+
    "IF(wd.lane_min < wd.lane_max, 1, 0) AS over_line,"+
    "IF(wd.speed > dsi.speed_limit,"+
    "ROUND((wd.speed-dsi.speed_limit)*100/dsi.speed_limit,2),0) AS over_speed_radio,"+
    "IF(wd.over_weight > 0, ROUND((wd.over_weight/wd.limit_weight)*100,2), 0) AS over_weight_radio,"+
    "IF(wd.height > 4000, ROUND((wd.height-4000)*100/4000,2), 0) AS over_height_radio,"+
    "IF(wd.width > 2550, ROUND((wd.width-2550)*100/2550,2), 0) AS over_width_radio,"+
    "IF(wd.length > 18100, ROUND((wd.length-18100)*100/18100,2), 0) AS over_length_radio,"+
    "wd.weighting_date AS check_date,"+
    "wd.create_time AS create_date,"+
    "wd.update_time AS modify_date"+
    " FROM weight_data AS wd,duge_station_info AS dsi WHERE wd.`station_id` = dsi.`id`;";

    // 数据库配置信息
    private String originalDriver;
    private String originalUrl;
    private String originalUser;
    private String originalPassword;
    private String targetDriver;
    private String targetUrl;
    private String targetUser;
    private String targetPassword;

    public WeightDataTransForm(){
        ConfigManager configManager = new ConfigManager("weightDataDataBase");
        this.originalDriver = configManager.getProperty("original.driver");
        this.originalUrl = configManager.getProperty("original.url");
        this.originalUser = configManager.getProperty("original.user");
        this.originalPassword = configManager.getProperty("original.password");
        this.targetDriver = configManager.getProperty("target.driver");
        this.targetUrl = configManager.getProperty("target.url");
        this.targetUser = configManager.getProperty("target.user");
        this.targetPassword = configManager.getProperty("target.password");
    }

    // Mysql获取数据
    private void MysqlAcceptdata() {
        try {
            //加载驱动
            Class.forName(originalDriver);
            // 获得数据库连接对象
            conn = DriverManager.getConnection(originalUrl,originalUser,originalPassword);
            // 预编译SQL语句
            pstmt = conn.prepareStatement(QUERIES);
            // 执行语句并接收结果集对象
            rs = pstmt.executeQuery();
            // 获得列集
            ResultSetMetaData rsm = rs.getMetaData();
            //获得列的个数
            int col = rsm.getColumnCount();
            while (rs.next()) {
                // 封装属性
                paramMap = new HashMap<>();
                for (int i = 0; i < col; i++) {
                    String colAsName = rsm.getColumnLabel(i + 1);
                    String colType = rsm.getColumnTypeName(i + 1);
                    // 日期类型处理
                    if(colType.equalsIgnoreCase("date")||colType.equalsIgnoreCase("datetime")){
                        paramMap.put(colAsName, rs.getTimestamp(i + 1));
                    }else{
                        paramMap.put(colAsName, rs.getString(i + 1));
                    }
                }
                params.add(paramMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource();
        }
    }

    // Oracle添加数据
    private void OracleInsertData() throws SQLException{
        try {
            //加载驱动
            Class.forName(targetDriver);
            // 获得数据库连接对象
            conn = DriverManager.getConnection(targetUrl, targetUser, targetPassword);
            //设置手动提交
            conn.setAutoCommit(false);
            // 自动拼接INSERT语句
            List<String> keys = new ArrayList<>();
            StringBuilder columnSql = new StringBuilder();
            StringBuilder unknownMarkSql = new StringBuilder();
            Set<String> keySet = params.get(0).keySet();
            int index = 0;
            for (String key : keySet) {
                keys.add(key);
                columnSql.append( index == 0 ? "" : "," );
                columnSql.append(key);
                unknownMarkSql.append(index == 0 ? "" : "," );
                unknownMarkSql.append("?");
                index++;
            }
            String INSERTS = "INSERT INTO VEHICLE_RECORD("+columnSql.toString()+") VALUES("+unknownMarkSql.toString()+")";
            // 预编译SQL语句
            pstmt = conn.prepareStatement(INSERTS);
            // 遍历执行传入参数
            for (int j = 0; j < params.size(); j++) {
                for (int k = 0; k < params.get(j).size(); k++) {
                    pstmt.setObject(k + 1, params.get(j).get(keys.get(k)));
                }
                pstmt.addBatch();
            }
            int[] arr = pstmt.executeBatch();
            conn.commit();
            System.out.println("成功了插入了" + arr.length + "行");
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
        } finally {
            closeResource();
        }
    }

    /**
     * 关闭资源
     */
    private void closeResource() {
        try {
            // 若结果集对象不为空，则关闭
            if (rs != null) {
                rs.close();
            }
            // 若PreparedStatement对象不为空，则关闭
            if (pstmt != null) {
                pstmt.close();
            }
            // 若Statement对象不为空，则关闭
            if (stmt != null) {
                stmt.close();
            }
            // 若数据库连接对象不为空，则关闭
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception{
        WeightDataTransForm test = new WeightDataTransForm();
        test.MysqlAcceptdata();
        test.OracleInsertData();
    }

}
