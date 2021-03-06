package com.ruoyi.web.controller.duge;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ExcelExportUtil;
import com.ruoyi.common.utils.ExcelUtil;
import com.ruoyi.duge.domain.CarOut;
import com.ruoyi.duge.domain.StationStatistics;
import com.ruoyi.duge.domain.WeightData;
import com.ruoyi.duge.mapper.WeightDataMapper;
import com.ruoyi.duge.service.IConfigDataService;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.page.TableSupport;
import com.ruoyi.web.core.base.BaseController;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;
import static com.ruoyi.common.utils.DateUtils.YYYY_MM_DD_HH_MM_SS;
import static com.ruoyi.common.utils.ExcelExportUtil.exportExcel;

/**
 * 重量数据 信息操作处理
 *
 * @author ruoyi
 * @date 2018-11-20
 */
@Controller
@RequestMapping("/duge/weightData")
public class WeightDataController extends BaseController {
	private String prefix = "/duge/weightData";
	private static Logger LOGGER = LoggerFactory.getLogger(WeightDataController.class);

	@Autowired
	private com.ruoyi.duge.service.IWeightDataMapperService dataService;

	@Autowired
	private WeightDataMapper weightDataMapper;

	@Autowired
	private IConfigDataService configDataService;

	@Autowired
	private RedisTemplate redisTemplate;

	// @RequiresPermissions("duge:weightData:view")
	@GetMapping()
	public String data(@RequestParam(value="id",required=false) Integer id, ModelMap mmap) {
		mmap.put("overWeightMin", -1);
		mmap.put("stationId", id == null ? 0:id);
		return "/weightData/weight_data";
	}

	@GetMapping("/over")
	public String overData(ModelMap mmap) {
		mmap.put("overWeightMin", 0);
		return "/weightData/weight_data";
	}

	@GetMapping("/lwh")
	public String lwhData(ModelMap mmap) {
		mmap.put("overWeightMin", -1);
		return "/weightData/weight_data_lwh";
	}

	@GetMapping("/carout")
	public String carOutData(ModelMap mmap) {
		mmap.put("overWeightMin", 0);
		return "/weightData/weight_data_carout";
	}

	@GetMapping("/daily")
	public String daily(ModelMap mmap) {
		return "/weightData/weight_data_daily";
	}

	/**
	 * 查询重量数据列表
	 */
	@RequiresPermissions("duge:weightData:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(com.ruoyi.duge.domain.WeightData data) {
		startPage();
		List<com.ruoyi.duge.domain.WeightData> list = dataService.selectDataList(data);
		return getDataTable(list);
	}

	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(com.ruoyi.duge.domain.WeightData data) throws FileNotFoundException {
		List<com.ruoyi.duge.domain.WeightData> weightDataList = dataService.selectDataList(data);
		ExcelUtil<com.ruoyi.duge.domain.WeightData> util = new ExcelUtil<>(com.ruoyi.duge.domain.WeightData.class);
		String title = "车辆行驶数据";
		String filename = util.encodingFilename(title);
		FileOutputStream out = new FileOutputStream(util.getAbsoluteFile(filename));
		List<ExcelExportUtil.Header> cloums = new ArrayList<>();
		cloums.add(new ExcelExportUtil.Header("序号", 5));
		cloums.add(new ExcelExportUtil.Header("站点名", 25));
		cloums.add(new ExcelExportUtil.Header("车牌号码", 15));
		cloums.add(new ExcelExportUtil.Header("车牌颜色", 5));
		cloums.add(new ExcelExportUtil.Header("轴数", 5));
		cloums.add(new ExcelExportUtil.Header("总重", 8));
		cloums.add(new ExcelExportUtil.Header("限重", 8));
		cloums.add(new ExcelExportUtil.Header("超重比率", 8));
		cloums.add(new ExcelExportUtil.Header("车速", 8));
		cloums.add(new ExcelExportUtil.Header("车长", 8));
		cloums.add(new ExcelExportUtil.Header("车宽", 8));
		cloums.add(new ExcelExportUtil.Header("车高", 8));
		cloums.add(new ExcelExportUtil.Header("采集时间", 20));

		List<List> dataset = new ArrayList<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < weightDataList.size(); i++) {
			WeightData weightData = weightDataList.get(i);
			List<Object> list = new ArrayList<>();
			list.add(i + 1);
			list.add(weightData.getStationName());
			list.add(weightData.getTruckNumber());
			list.add(weightData.getTruckCorlor());
			list.add(weightData.getAxleCount());
			list.add(weightData.getWeight());
			list.add(weightData.getLimitWeight());
			list.add(String.format("%.2f",
					weightData.getOverWeight().doubleValue() / weightData.getLimitWeight().doubleValue() * 100) + "%");
			list.add(weightData.getSpeed());
			list.add(NumberUtils.toDouble(weightData.getLength(), 0) / 1000);
			list.add(NumberUtils.toDouble(weightData.getWidth(), 0) / 1000);
			list.add(NumberUtils.toDouble(weightData.getHeight(), 0) / 1000);
			list.add(format.format(weightData.getWeightingDate()));
			dataset.add(list);
		}
		exportExcel(title, cloums, dataset, out);
		return AjaxResult.success(filename);
	}

	/**
	 * 查看重量数据
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, ModelMap mmap, HttpServletRequest request) {
		com.ruoyi.duge.domain.WeightData data = dataService.selectDataById(id);
		mmap.put("data", data);
		mmap.put("overRate",
				String.format("%.2f", data.getOverWeight().doubleValue() / data.getLimitWeight().doubleValue() * 100)
						+ "%");
		// mmap.put("ctxPath", request.getContextPath() + "\\");
		mmap.put("ctxPath", configDataService.getConfigValue("nginx_url") + data.getStationId() + "/"
				+ DateFormatUtils.format(data.getWeightingDate(), "yyyyMMdd") + "/");
		return "weightData/weight_data_detail";
	}

	@GetMapping("/realtime")
	public String realtime(ModelMap mmap, HttpServletRequest request) {
		com.ruoyi.duge.domain.WeightData tmp = new WeightData();
		tmp.setStationId(2L);
		com.ruoyi.duge.domain.WeightData data = dataService.selectLast(tmp);
		mmap.put("data", data);
		mmap.put("overRate",
				String.format("%.2f", data.getOverWeight().doubleValue() / data.getLimitWeight().doubleValue() * 100)
						+ "%");
		// mmap.put("ctxPath", request.getContextPath() + "\\");
		mmap.put("ctxPath", configDataService.getConfigValue("nginx_url") + data.getStationId() + "/"
				+ DateFormatUtils.format(data.getWeightingDate(), "yyyyMMdd") + "/");
		return "weightData/weight_data_realtime";
	}

	@GetMapping("/last")
	@ResponseBody
	public WeightData refreshLast() {
		com.ruoyi.duge.domain.WeightData tmp = new WeightData();
		tmp.setStationId(2L);
		com.ruoyi.duge.domain.WeightData data = dataService.selectLast(tmp);
		data.getParams().put("overRate",
				String.format("%.2f", data.getOverWeight().doubleValue() / data.getLimitWeight().doubleValue() * 100)
						+ "%");
		data.getParams().put("weightDate", DateFormatUtils.format(data.getWeightingDate(), "yyyy-MM-dd HH:mm:ss"));
		data.getParams().put("ctxPath", configDataService.getConfigValue("nginx_url") + data.getStationId() + "/"
				+ DateFormatUtils.format(data.getWeightingDate(), "yyyyMMdd") + "/");
		return data;
	}

	/**
	 * 新增重量数据
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存重量数据
	 */
	@RequiresPermissions("duge:weightData:add")
	@Log(title = "重量数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(com.ruoyi.duge.domain.WeightData data) {
		return toAjax(dataService.insertData(data));
	}

	/**
	 * 修改重量数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		com.ruoyi.duge.domain.WeightData data = dataService.selectDataById(id);
		mmap.put("data", data);
		return prefix + "/edit";
	}

	/**
	 * 修改保存重量数据
	 */
	@RequiresPermissions("duge:weightData:edit")
	@Log(title = "重量数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(com.ruoyi.duge.domain.WeightData data) {
		return toAjax(dataService.updateData(data));
	}

	/**
	 * 删除重量数据
	 */
	@RequiresPermissions("duge:weightData:remove")
	@Log(title = "重量数据", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(dataService.deleteDataByIds(ids));
	}

	@PostMapping("/stationData/{id}")
	@ResponseBody
	public Map getStationData(@PathVariable("id") Long stationId) {
		Date xxDate = new Date();
		List<StationStatistics> stationStatisticsList = new ArrayList<>();
		for (int h = 1; h <= 24; h++) {
			Date beginDate = DateUtils.addHours(xxDate, -h);
			Instant instant = beginDate.toInstant();
			ZoneId zoneId = ZoneId.systemDefault();
			LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
			Date endDate = DateUtils.addHours(beginDate, 1);
			WeightData weightData = new WeightData();
			weightData.setStationId(stationId);
			Map<String, Object> map = new HashMap<>();
			map.put("beginTime", DateFormatUtils.format(beginDate, "yyyy-MM-dd HH:mm:ss"));
			map.put("endTime", DateFormatUtils.format(endDate, "yyyy-MM-dd HH:mm:ss"));
			weightData.setParams(map);
			Map resultMap = dataService.selectStationStatistics(weightData);
			StationStatistics stationStatistics = StationStatistics.builder().dateTime(beginDate)
					.year(localDateTime.getYear()).month(localDateTime.getMonthValue())
					.day(localDateTime.getDayOfMonth()).stationId(stationId)
					.trafficFlow((long) resultMap.get("trafficFlow"))
					.overCount(
							resultMap.get("overCount") != null ? ((BigDecimal) resultMap.get("overCount")).longValue()
									: 0)
					.build();
			stationStatisticsList.add(stationStatistics);
		}
		WeightData weightData = new WeightData();
		weightData.getParams().put("timeStr", com.ruoyi.common.utils.DateUtils.parseDateToStr(YYYY_MM_DD_HH_MM_SS,
				new Date(new Date().getTime() - 1000 * 60 * 60)));
		Double speedAvg = weightDataMapper.selectSpeedAvg(weightData);
		Map result = new HashMap();
		result.put("speedAvg", speedAvg);
		result.put("dataList", stationStatisticsList);
		return result;
	}

	/**
	 * 过车统计
	 */

	@PostMapping("/selectCarOutNumber/{stationId}/{startDate}/{endDate}")
	@ResponseBody
	public List<CarOut> selectCarOutNumber(@PathVariable("stationId") int stationId,
			@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate
			) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startTime;
		Date endTime;
		List<CarOut> list = null;
		try {
			startTime = sdf.parse(startDate);
			endTime = sdf.parse(endDate);
			list = dataService.selectCarOutNumber(stationId, startTime, endTime);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}


	/**
	 * 过车超重统计
	 */

	@PostMapping("/selectCarOverWeight/{stationId}/{startDate}/{endDate}")
	@ResponseBody
	public List<CarOut> selectCarOverWeight(@PathVariable("stationId") int stationId,
										   @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate
										   ) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startTime;
		Date endTime;
		List<CarOut> list = null;
		try {
			startTime = sdf.parse(startDate);
			endTime = sdf.parse(endDate);
			list = dataService.selectCarOverWeight(stationId, startTime, endTime);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}


	@GetMapping("/carMonitor/{stationId}")
	public String carMonitor(@PathVariable("stationId") int stationId,ModelMap mmap) {
		List<WeightData> weightDataList1 = weightDataMapper.selectByStationIdAndLane(stationId,1);
		List<WeightData> weightDataList2 = weightDataMapper.selectByStationIdAndLane(stationId,2);
		List<WeightData> weightDataList3 = weightDataMapper.selectByStationIdAndLane(stationId,3);
		List<WeightData> weightDataList4 = weightDataMapper.selectByStationIdAndLane(stationId,4);
		List<WeightData> weightDataList5 = weightDataMapper.selectByStationIdAndLane(stationId,5);
		mmap.put("ctxPath", configDataService.getConfigValue("nginx_url") + stationId + "/"
				+ DateFormatUtils.format(weightDataList1.get(0).getCreateTime(), "yyyyMMdd") + "/");
		mmap.put("weightDataList1",weightDataList1);
		mmap.put("weightDataList2",weightDataList2);
		mmap.put("weightDataList3",weightDataList3);
		mmap.put("weightDataList4",weightDataList4);
		mmap.put("weightDataList5",weightDataList5);
		return "/weightData/car_monitor";
	}


	/**
	 * 超限排行页面
	 * @param mmap
	 * @return
	 */
	@GetMapping("/overLoadRanking")
	public String overLoadRanking(ModelMap mmap) {
		return "/statistics/overLoadRanking";
	}

	/**
	 * 查询站点超限记录
	 */
	@PostMapping("/overLoadRecordList")
	@ResponseBody
	public TableDataInfo overLoadRecordList(String plate,String startDay) {
		PageDomain pageDomain = TableSupport.buildPageRequest();
		Integer pageNum = pageDomain.getPageNum();
		Integer pageSize = pageDomain.getPageSize();
		PageHelper.startPage(pageNum,pageSize);
        if(null == startDay || "".equals(startDay) || "0".equals(startDay)){
            startDay = "1";
        }
		String key = plate + "_" + startDay + "_record_" + pageNum;
		ValueOperations<String, PageInfo<WeightData>> operations = redisTemplate.opsForValue();
		boolean hasKey = redisTemplate.hasKey(key);
		PageInfo<WeightData> pageList;
		if (hasKey) {
			pageList = operations.get(key);
		}
		else{
			List<WeightData> list = weightDataMapper.selectOverLoadRecordByPlate(plate,startDay);
			pageList = new PageInfo<WeightData>(list,4);
			operations.set(key, pageList, 5, TimeUnit.HOURS);
		}
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(pageList.getList());
		rspData.setTotal(pageList.getTotal());
		return rspData;
	}

	/**
	 * 查询车辆超限排行
	 */
	@PostMapping("/overLoadCarList")
	@ResponseBody
	public TableDataInfo overLoadStationList(String plate,String startDay) {
		PageDomain pageDomain = TableSupport.buildPageRequest();
		Integer pageNum = pageDomain.getPageNum();
		Integer pageSize = pageDomain.getPageSize();
		if(null == pageNum || null == pageSize){
		    pageNum = 1;
		    pageSize = 18;
        }
		PageHelper.startPage(pageNum,pageSize);

		if(null == startDay || "".equals(startDay) || "0".equals(startDay)){
			startDay = "1";
		}
		String key = plate + "_" + startDay + "_car_" + pageNum;
		ValueOperations<String, PageInfo<HashMap>> operations = redisTemplate.opsForValue();
		//判断redis中是否有键为key的缓存
		boolean hasKey = redisTemplate.hasKey(key);
		PageInfo<HashMap> pageList;
		if (hasKey) {
			pageList = operations.get(key);
			System.out.println("缓存中获得数据：" + key);
		}
		else{
			List<HashMap> carList = weightDataMapper.overLoadCarList(plate,startDay);
			pageList = new PageInfo<HashMap>(carList,4);
			operations.set(key, pageList, 5, TimeUnit.HOURS);
			System.out.println("写入缓存：" + key);
		}
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(pageList.getList());
		rspData.setTotal(pageList.getTotal());
		return rspData;
	}

	/**
	 * 改装车违规排行页面
	 * @param mmap
	 * @return
	 */
	@GetMapping("/overLengthRanking")
	public String overLengthRanking(ModelMap mmap) {
		return "/statistics/overLengthRanking";
	}

	/**
	 * 查询站点改装车违规记录
	 */
	@PostMapping("/overLengthRecordList")
	@ResponseBody
	public TableDataInfo overLengthRecordList(String plate,String startDay) {
		PageDomain pageDomain = TableSupport.buildPageRequest();
		Integer pageNum = pageDomain.getPageNum();
		Integer pageSize = pageDomain.getPageSize();
		PageHelper.startPage(pageNum,pageSize);
        if(null == startDay || "".equals(startDay) || "0".equals(startDay)){
            startDay = "1";
        }
		String key = "overLength_" + plate + "_" + startDay + "_record_" + pageNum;
		//ValueOperations<String, PageInfo<WeightData>> operations = redisTemplate.opsForValue();
		//boolean hasKey = redisTemplate.hasKey(key);
		PageInfo<WeightData> pageList;
/*		if (hasKey) {
			pageList = operations.get(key);
		}
		else{*/
			List<WeightData> list = weightDataMapper.selectOverLengthRecordByPlate(plate,startDay);
			pageList = new PageInfo<WeightData>(list,4);
			//operations.set(key, pageList, 5, TimeUnit.HOURS);
		//}
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(pageList.getList());
		rspData.setTotal(pageList.getTotal());
		return rspData;
	}

	/**
	 * 查询车辆改装车违规排行
	 */
	@PostMapping("/overLengthCarList")
	@ResponseBody
	public TableDataInfo overLengthStationList(String plate,String startDay) {
		PageDomain pageDomain = TableSupport.buildPageRequest();
		Integer pageNum = pageDomain.getPageNum();
		Integer pageSize = pageDomain.getPageSize();
		if(null == pageNum || null == pageSize){
		    pageNum = 1;
		    pageSize = 18;
        }
		PageHelper.startPage(pageNum,pageSize);

		if(null == startDay || "".equals(startDay) || "0".equals(startDay)){
			startDay = "1";
		}
		String key = "overLength_" + plate + "_" + startDay + "_car_" + pageNum;
		//ValueOperations<String, PageInfo<HashMap>> operations = redisTemplate.opsForValue();
		//boolean hasKey = redisTemplate.hasKey(key);
		PageInfo<HashMap> pageList;
/*		if (hasKey) {
			pageList = operations.get(key);
		}
		else{*/
			List<HashMap> carList = weightDataMapper.overLengthCarList(plate,startDay);
			pageList = new PageInfo<HashMap>(carList,4);
			//operations.set(key, pageList, 5, TimeUnit.HOURS);
		//}
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(pageList.getList());
		rspData.setTotal(pageList.getTotal());
		return rspData;
	}


	@PostMapping("/stationDaily/{date}")
	@ResponseBody
	public List<CarOut> stationDaily(@PathVariable("date") String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startTime;
		Date endTime;
		List<CarOut> list = null;
		try {
			startTime = sdf.parse(date);
			endTime = DateUtils.addDays(startTime, 1);
			list = dataService.stationDaily(startTime, endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@PostMapping("/stationDailyOverWeight/{date}")
	@ResponseBody
	public List<CarOut> stationDailyOverWeight(@PathVariable("date") String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime;

		List<CarOut> list = null;
		try {
			startTime = sdf.parse(date);

			list = dataService.stationDailyOverWeight(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@PostMapping("/sendyihualu/{date}")
	@ResponseBody
	public List<CarOut> sendyihualu(@PathVariable("date") String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startTime;
		Date endTime;
		List<CarOut> list = null;
		try {
			startTime = sdf.parse(date);
			endTime = DateUtils.addDays(startTime, 1);
			list = dataService.sendYihualu(startTime, endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
