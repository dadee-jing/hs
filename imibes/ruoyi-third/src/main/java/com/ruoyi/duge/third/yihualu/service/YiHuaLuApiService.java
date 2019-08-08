package com.ruoyi.duge.third.yihualu.service;

import com.ruoyi.duge.domain.DeviceHealthState;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class YiHuaLuApiService {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    private final String driver = "oracle.jdbc.driver.OracleDriver";
    private final String url = "jdbc:oracle:thin:@19.201.248.51:1521:ORCL";
    private final String user= "tocc_zhichao";
    private final String password= "tocc_zhichao";

    // 插入数据
    public void InsertData(List<DeviceHealthState> params)throws Exception{
        try {
            //加载驱动
            Class.forName(driver);
            // 获得数据库连接对象
            conn = DriverManager.getConnection(url, user, password);
            //设置手动提交
            conn.setAutoCommit(false);
            // 预编译SQL语句
            pstmt = conn.prepareStatement("INSERT INTO device_health_state VALUES(?,?,?,?,?,?,?,?,?,?)");
            // 遍历执行传入参数
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(1,params.get(i).getId());
                pstmt.setObject(2,params.get(i).getStation_code());
                pstmt.setObject(3,params.get(i).getStation_name());
                pstmt.setObject(4,params.get(i).getStation_status());
                pstmt.setObject(5,params.get(i).getDevice_id());
                pstmt.setObject(6,params.get(i).getDevice_name());
                pstmt.setObject(7,params.get(i).getDevice_status());
                pstmt.setObject(8,params.get(i).getError_code());
                pstmt.setObject(9,params.get(i).getError_msg());
                pstmt.setObject(10,params.get(i).getReport_date());
                pstmt.addBatch();
            }
            int[] arr = pstmt.executeBatch();
            conn.commit();
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
}
