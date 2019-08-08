package com.hs.platform.station;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

/**
 * 配置文件常量
 */
public class Constants {

    public static Integer station_id;
    public static Integer newlx_tcp_server_port;
    public static String newlx_ftp_server_host;
    public static Integer newlx_ftp_server_port;
    public static String newlx_ftp_user;
    public static String newlx_ftp_passwd;
    public static String lwh_server_host;
    public static Integer lwh_server_port;
    public static String shunde_ftp_server_host;
    public static Integer shunde_ftp_server_port;
    public static String shunde_ftp_user;
    public static String shunde_ftp_passwd;
    public static String driver_class_name;
    public static String db_username;
    public static String db_password;
    public static String db_url;
    public static String upload_equipment_state_url;
    public static String led_ip;
    public static Integer led_port;
    public static String led_idcode;
    public static Double led_overWeight_percentage;
    public static Integer led_text_color;
    public static Integer led_text_fontSize;
    public static Integer led_text_speed;
    public static Integer led_text_stayTime;
    public static Integer led_text_alignmentHori;
    public static Integer led_text_alignmentVert;

    /**
     * 加载配置文件
     */
    static {
        try {
            Configurations configs = new Configurations();
            Configuration config = configs.properties(new File("config.properties"));
            station_id = config.getInt("station_id");
            newlx_tcp_server_port = config.getInt("newlx_tcp_server_port");
            newlx_ftp_server_host = config.getString("newlx_ftp_server_host");
            newlx_ftp_server_port = config.getInt("newlx_ftp_server_port");
            newlx_ftp_user = config.getString("newlx_ftp_user");
            newlx_ftp_passwd = config.getString("newlx_ftp_passwd");
            lwh_server_host = config.getString("lwh_server_host");
            lwh_server_port = config.getInt("lwh_server_port");
            shunde_ftp_server_host = config.getString("shunde_ftp_server_host");
            shunde_ftp_server_port = config.getInt("shunde_ftp_server_port");
            shunde_ftp_user = config.getString("shunde_ftp_user");
            shunde_ftp_passwd = config.getString("shunde_ftp_passwd");
            driver_class_name = config.getString("driver_class_name");
            db_username = config.getString("db_username");
            db_password = config.getString("db_password");
            db_url = config.getString("db_url");
            upload_equipment_state_url = config.getString("upload_equipment_state_url");
            led_ip = config.getString("led_ip");
            led_port = config.getInt("led_port");
            led_idcode = config.getString("led_idcode");
            led_overWeight_percentage = config.getDouble("led_overWeight_percentage");
            led_text_color = config.getInt("led_text_color");
            led_text_fontSize = config.getInt("led_text_fontSize");
            led_text_speed = config.getInt("led_text_speed");
            led_text_stayTime = config.getInt("led_text_stayTime");
            led_text_alignmentHori = config.getInt("led_text_alignmentHori");
            led_text_alignmentVert = config.getInt("led_text_alignmentVert");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
