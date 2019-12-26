package com.hs.platform.station.util;

import com.hs.platform.station.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbUtil.class);




    public static void insertExceptionData(WeightAndLwhEntity weight) {

        String sql = "insert into exception_data(limit_mode, vehicle_type, weighting_id, weighting_date, lane, direction, "
                + "truck_number, truck_corlor, speed, axle_count, axle_type, weight, limit_weight, over_weight, axle_weight1, "
                + "axle_weight2, axle_weight3, axle_weight4, axle_weight5, axle_weight6, axle_weight7, axle_weight8,sequence_tag," +
                "ftp_head,ftp_axle,ftp_tail,ftp_prior_head,ftp_plate,ftp_full_view,device_code,lwh_date,plate,width,height," +
                "length,lane_mid,lane_min,lane_max,pass_time,remark_info,create_time) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtil.getDataSource().getConnection();
            statement = connection.prepareStatement(sql);
            //System.out.println("^^^^^^^^^^^^^^^^^1");
            statement.setInt(1, weight.getLimitMode());
            statement.setString(2, weight.getVehicleType());
            statement.setInt(3, weight.getWeightingId());
            statement.setTimestamp(4, weight.getWeightingDate());
            statement.setInt(5, weight.getLane());
            statement.setString(6, weight.getDirection());
            statement.setString(7, weight.getTruckNumber());
            //System.out.println("^^^^^^^^^^^^^^^^^2");
            statement.setString(8, weight.getTruckCorlor());
            statement.setBigDecimal(9, weight.getSpeed());
            statement.setInt(10, weight.getAxleCount());
            statement.setString(11, weight.getAxleType());
            statement.setBigDecimal(12, weight.getWeight());
            statement.setBigDecimal(13, weight.getLimitWeight());
            statement.setBigDecimal(14, weight.getOverWeight());
            //System.out.println("^^^^^^^^^^^^^^^^^3");
            statement.setBigDecimal(15, weight.getAxleWeight1());
            statement.setBigDecimal(16, weight.getAxleWeight2());
            statement.setBigDecimal(17, weight.getAxleWeight3());
            statement.setBigDecimal(18, weight.getAxleWeight4());
            statement.setBigDecimal(19, weight.getAxleWeight5());
            statement.setBigDecimal(20, weight.getAxleWeight6());
            statement.setBigDecimal(21, weight.getAxleWeight7());
            statement.setBigDecimal(22, weight.getAxleWeight8());
            String sequenceTag = weight.getSequenceTag();
            //System.out.println("^^^^^^^^^^^^^^^^^4");
            statement.setString(23, sequenceTag);
            statement.setString(24, weight.getFtpHead());
            statement.setString(25, weight.getFtpAxle());
            statement.setString(26, weight.getFtpTail());
            statement.setString(27, weight.getFtpPriorHead());
            statement.setString(28, weight.getFtpPlate());
            statement.setString(29, weight.getFtpFullView());
            String deviceCode = "";
            if (null != sequenceTag && !"".equals(sequenceTag)) {
                deviceCode = sequenceTag.substring(0, 8);
            }
            statement.setString(30, deviceCode);
            //System.out.println("^^^^^^^^^^^^^^^^^5");
            statement.setTimestamp(31, weight.getLwhDate());
            statement.setString(32, weight.getPlate());
            statement.setString(33, weight.getWidth());
            statement.setString(34, weight.getHeight());
            statement.setString(35, weight.getLength());
            statement.setString(36, weight.getLaneMid());
            statement.setString(37, weight.getLaneMin());
            statement.setString(38, weight.getLaneMax());
            statement.setString(39, weight.getPassTime());
            statement.setString(40, weight.getRemarkInfo());
            statement.setTimestamp(41, new Timestamp(System.currentTimeMillis()));
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    public static void insertWeightAndLWH(WeightAndLwhEntity weight) {

        String sql = "insert into weight_data(limit_mode, vehicle_type, weighting_id, weighting_date, lane, direction, "
                + "truck_number, truck_corlor, speed, axle_count, axle_type, weight, limit_weight, over_weight, axle_weight1, "
                + "axle_weight2, axle_weight3, axle_weight4, axle_weight5, axle_weight6, axle_weight7, axle_weight8,sequence_tag," +
                "ftp_head,ftp_axle,ftp_tail,ftp_prior_head,ftp_plate,ftp_full_view,device_code,lwh_date,plate,width,height,length," +
                "lane_mid,lane_min,lane_max,pass_time,station_id,upload_tag,path_tag,left_side_path,right_side_path,create_time," +
                "remark,lbh,lwh_scene_path) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtil.getDataSource().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, weight.getLimitMode());
            statement.setString(2, weight.getVehicleType());
            statement.setInt(3, weight.getWeightingId());
            statement.setTimestamp(4, weight.getWeightingDate());
            statement.setInt(5, weight.getLane());
            statement.setString(6, weight.getDirection());
            statement.setString(7, weight.getTruckNumber());
            statement.setString(8, weight.getTruckCorlor());
            statement.setBigDecimal(9, weight.getSpeed());
            statement.setInt(10, weight.getAxleCount());
            statement.setString(11, weight.getAxleType());
            statement.setBigDecimal(12, weight.getWeight());
            statement.setBigDecimal(13, weight.getLimitWeight());
            statement.setBigDecimal(14, weight.getOverWeight());
            statement.setBigDecimal(15, weight.getAxleWeight1());
            statement.setBigDecimal(16, weight.getAxleWeight2());
            statement.setBigDecimal(17, weight.getAxleWeight3());
            statement.setBigDecimal(18, weight.getAxleWeight4());
            statement.setBigDecimal(19, weight.getAxleWeight5());
            statement.setBigDecimal(20, weight.getAxleWeight6());
            statement.setBigDecimal(21, weight.getAxleWeight7());
            statement.setBigDecimal(22, weight.getAxleWeight8());
            String sequenceTag = weight.getSequenceTag();
            statement.setString(23, sequenceTag);
            statement.setString(24, weight.getFtpHead());
            statement.setString(25, weight.getFtpAxle());
            statement.setString(26, weight.getFtpTail());
            statement.setString(27, weight.getFtpPriorHead());
            statement.setString(28, weight.getFtpPlate());
            statement.setString(29, weight.getFtpFullView());
            statement.setString(30, sequenceTag.substring(0, 8));

            statement.setTimestamp(31, weight.getLwhDate());
            statement.setString(32, weight.getPlate());
            statement.setString(33, weight.getWidth());
            statement.setString(34, weight.getHeight());
            statement.setString(35, weight.getLength());
            statement.setString(36, weight.getLaneMid());
            statement.setString(37, weight.getLaneMin());
            statement.setString(38, weight.getLaneMax());
            statement.setString(39, weight.getPassTime());
            statement.setInt(40, weight.getStationId());
            statement.setInt(41, weight.getUploadTag());
            statement.setInt(42, weight.getPathTag());
            statement.setString(43, weight.getLeftSidePath());
            statement.setString(44, weight.getRightSidePath());
            statement.setTimestamp(45, new Timestamp(System.currentTimeMillis()));
            statement.setString(46, weight.getRemarkInfo());
            statement.setString(47, weight.getLbh());
            statement.setString(48, weight.getLwhScenePath());

            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    public static Map<Integer, Long> getWeightLimitMap() {
        //List<VehicleWeightLimit> vehicleWeightLimitList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Map<Integer, Long> result = new HashMap<>();
        try {
            connection = DataSourceUtil.getDataSource().getConnection();
            statement = connection.prepareStatement("select id,alex_count,weight_limit from vehicle_weight_limit");
            rs = statement.executeQuery();
            while (rs.next()) {
                //int id = rs.getInt("id");
                int alexCount = rs.getInt("alex_count");
                long weightLimit = rs.getLong("weight_limit");
                result.put(alexCount, weightLimit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getConfigValue(String key) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String value = "";
        try {
            connection = DataSourceUtil.getDataSource().getConnection();
            statement = connection.prepareStatement("select value from config_data a where a.key = ?");
            statement.setString(1, key);
            rs = statement.executeQuery();
            rs.next();
            value = rs.getString("value") == null ? "" : rs.getString("value");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static List<LedTableInfo> getLedInfoListByTime(String sql, Timestamp queryTime) {

        List<LedTableInfo> resultList = new ArrayList<LedTableInfo>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtil.getDataSource().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, queryTime);
            statement.setTimestamp(2, queryTime);
            rs = statement.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt("led_id");
                String IpAddress = rs.getString("ip_address");
                String Color = rs.getString("led_color");
//				try {
//					Color = new String(Color.getBytes("UTF-8"),"GBK");
//				} catch (UnsupportedEncodingException e) {
//					LOGGER.error(e.getMessage(), e);
//				}
                System.out.println("Color:" + Color);
                String Content = rs.getString("led_content");
//				try {
//					Content = new String(Content.getBytes("UTF-8"),"GBK");
//				} catch (UnsupportedEncodingException e) {
//					LOGGER.error(e.getMessage(), e);
//				}
                System.out.println("Content:" + Content);
                int Index = rs.getInt("led_index");
                Timestamp StartTime = rs.getTimestamp("led_start_time");
                Timestamp EndTime = rs.getTimestamp("led_end_time");
                LedTableInfo tableInfo = new LedTableInfo();
                tableInfo.setId(Id);
                tableInfo.setIpAddress(IpAddress);
                tableInfo.setContent(Content);
                tableInfo.setIndex(Index);
                tableInfo.setColor(Color);
                tableInfo.setStartTime(DateUtil.timestamp2TimeString(StartTime));
                tableInfo.setEndTime(DateUtil.timestamp2TimeString(EndTime));
                resultList.add(tableInfo);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return resultList;
    }

    public static List<WeightTypeTableInfo> getWeightTypeListByTime(String sql, Timestamp queryTime) {

        List<WeightTypeTableInfo> resultList = new ArrayList<WeightTypeTableInfo>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtil.getDataSource().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, queryTime);
            rs = statement.executeQuery();
            while (rs.next()) {
                int Result = rs.getInt("weight_type");
                WeightTypeTableInfo tableInfo = new WeightTypeTableInfo();
                tableInfo.setResult(Result);
                resultList.add(tableInfo);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return resultList;
    }

    public static List<VehicleTableInfo> getVehicleListByTime(String sql, Timestamp queryTime) {
        List<VehicleTableInfo> resultList = new ArrayList<VehicleTableInfo>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtil.getDataSource().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, queryTime);
            statement.setTimestamp(2, queryTime);
            rs = statement.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt("vehicle_id");
                String VehicleType = rs.getString("vehicle_type");
                int AlexNum = rs.getInt("alex_num");
                BigDecimal Limit = rs.getBigDecimal("vehicle_limit");
                Timestamp StartTime = rs.getTimestamp("vehicle_starttime");
                Timestamp EndTime = rs.getTimestamp("vehicle_endtime");
                VehicleTableInfo tableInfo = new VehicleTableInfo();
                tableInfo.setId(Id);
                tableInfo.setVehicleType(VehicleType);
                tableInfo.setAlexNum(AlexNum);
                tableInfo.setLimit(Limit);
                tableInfo.setStartTime(DateUtil.timestamp2DateString(StartTime));
                tableInfo.setEndTime(DateUtil.timestamp2DateString(EndTime));
                resultList.add(tableInfo);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return resultList;
    }

    public static List<AlexTableInfo> getAlexListByTime(String sql, Timestamp queryTime) {
        List<AlexTableInfo> resultList = new ArrayList<AlexTableInfo>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtil.getDataSource().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, queryTime);
            statement.setTimestamp(2, queryTime);
            rs = statement.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt("alex_id");
                int AlexNum = rs.getInt("alex_num");
                BigDecimal Limit = rs.getBigDecimal("alex_limit");
                Timestamp StartTime = rs.getTimestamp("alex_start_time");
                Timestamp EndTime = rs.getTimestamp("alex_end_time");
                AlexTableInfo tableInfo = new AlexTableInfo();
                tableInfo.setId(Id);
                tableInfo.setAlexNum(AlexNum);
                tableInfo.setLimit(Limit);
                tableInfo.setStartTime(DateUtil.timestamp2DateString(StartTime));
                tableInfo.setEndTime(DateUtil.timestamp2DateString(EndTime));
                resultList.add(tableInfo);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return resultList;
    }

    public static List<BasicTableInfo> getTableListByTime(String sql, Timestamp queryTime) {

        List<BasicTableInfo> resultList = new ArrayList<BasicTableInfo>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtil.getDataSource().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, queryTime);
            rs = statement.executeQuery();
            while (rs.next()) {
                int Version = rs.getInt("table_version");
                int TableType = rs.getInt("table_type");
                BasicTableInfo tableInfo = new BasicTableInfo();
                tableInfo.setTableType(TableType);
                tableInfo.setVersion(Version);
                resultList.add(tableInfo);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }

        return resultList;
    }

    public static void insertDevice(String sql, DeviceEntity device, String sequenceTag) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtil.getDataSource().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, device.getDate());
            statement.setString(2, device.getDeviceType());
            statement.setInt(3, device.getId());
            statement.setString(4, device.getIpAddress());
            statement.setInt(5, device.getState());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }

    }

    public static void insertWeight(String sql, WeightEntity weight, String sequenceTag) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtil.getDataSource().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, weight.getLimitMode());
            statement.setString(2, weight.getVehicleType());
            statement.setInt(3, weight.getWeightingId());
            statement.setTimestamp(4, weight.getWeightingDate());
            statement.setInt(5, weight.getLane());
            statement.setString(6, weight.getDirection());
            statement.setString(7, weight.getTruckNumber());
            statement.setString(8, weight.getTruckCorlor());
            statement.setBigDecimal(9, weight.getSpeed());
            statement.setInt(10, weight.getAxleCount());
            statement.setString(11, weight.getAxleType());
            statement.setBigDecimal(12, weight.getWeight());
            statement.setBigDecimal(13, weight.getLimitWeight());
            statement.setBigDecimal(14, weight.getOverWeight());
            statement.setBigDecimal(15, weight.getAxleWeight1());
            statement.setBigDecimal(16, weight.getAxleWeight2());
            statement.setBigDecimal(17, weight.getAxleWeight3());
            statement.setBigDecimal(18, weight.getAxleWeight4());
            statement.setBigDecimal(19, weight.getAxleWeight5());
            statement.setBigDecimal(20, weight.getAxleWeight6());
            statement.setBigDecimal(21, weight.getAxleWeight7());
            statement.setBigDecimal(22, weight.getAxleWeight8());
            statement.setString(23, sequenceTag);
            statement.setString(24, weight.getFtpHead());
            statement.setString(25, weight.getFtpAxle());
            statement.setString(26, weight.getFtpTail());
            statement.setString(27, weight.getFtpPriorHead());
            statement.setString(28, weight.getFtpPlate());
            statement.setString(29, weight.getFtpFullView());
            statement.setString(30, sequenceTag.substring(0, 8));
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    public static List<DeviceInfo> getEquipmentList(String sql, Integer stationId){
        List<DeviceInfo> resultList = new ArrayList<DeviceInfo>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        DeviceInfo equipment = null;
        try {
            connection = DataSourceUtil.getDataSource().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,stationId);
            rs = statement.executeQuery();
            while (rs.next()) {
                equipment = new DeviceInfo();
                equipment.setId(rs.getInt("id"));
                equipment.setStationId(rs.getInt("station_id"));
                equipment.setStationCode(rs.getString("station_code"));
                equipment.setStationName(rs.getString("station_name"));
                equipment.setDeviceName(rs.getString("device_name"));
                equipment.setIpAddress(rs.getString("ipAddress"));
                equipment.setPort(rs.getString("port"));
                resultList.add(equipment);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return resultList;
    }

    public static String getAddress(int station_id) {
        //select address from duge_station_info where id = 1;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String value = "";
        try {
            connection = DataSourceUtil.getDataSource().getConnection();
            statement = connection.prepareStatement("select address from duge_station_info where id = ?");
            statement.setInt(1, station_id);
            rs = statement.executeQuery();
            rs.next();
            value = rs.getString("address") == null ? "" : rs.getString("address");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
