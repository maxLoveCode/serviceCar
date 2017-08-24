package serviceCar.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class GPSService {

	private final static String APPKey = "d0c0b86b-c847-49d3-8d83-f3e84cb54104";
	private final static String appsecret = "e94c7665-1035-4f70-b7b4-0702007470bd";
	public Double getWayBillInfo(String carNumber, Date btime, Date etime) throws Exception
	{
		String url = "http://api.e6gps.com/public/v3/StatisticsReport/Call";
		url += "?appkey=";
		url += APPKey;
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd%20HH:mm:ss");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ds = df.format(date);
		String ds2 = df2.format(date);
		String btimeString = df.format(btime);
		String etimeString = df.format(etime);
		url +="&timestamp="+ds;
		url += "&method=" + "GetVehicleOdometer";
		url += "&format=json";
		url += "&vehicles=" + carNumber;
		url += "&btime="+btimeString;
		url += "&etime="+etimeString;
		url += "&isDetails="+"1";
		String sign = appsecret+"appkey"+APPKey+"btime"+df2.format(btime)+"etime"+df2.format(etime)
				+"formatjson"+"isDetails1"+"method"+"GetVehicleOdometer"+"timestamp"+ds2
		+"vehicles"+carNumber+appsecret;
		String md5sign = GPSService.encryp(sign).toUpperCase();
		System.out.println(sign);
		url += "&sign="+md5sign;
		System.out.println(url);
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		int responseCode = con.getResponseCode();
		BufferedReader br;
		StringBuilder sb = new StringBuilder();
		if (200 <= responseCode && responseCode <= 299) {
		    br = new BufferedReader(new InputStreamReader((con.getInputStream())));
		} else {
		    br = new BufferedReader(new InputStreamReader((con.getErrorStream())));
		    return 0.0;
		}
	    String line;
		while ((line = br.readLine()) != null) {
	        sb.append(line);
	    }
		Map<String, Object> retMap = new Gson().fromJson(
				sb.toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
			);
		@SuppressWarnings("unchecked")
		ArrayList<Map<String, Object>> data = (ArrayList<Map<String, Object>>) ((Map<String, Object>) retMap.get("result")).get("data");
		if (data.size() ==0)
				return 0.0;
		Double odometer = (Double) data.get(0).get("DiffOdometer");
		return odometer;
	}
	
	public static String encryp(String context){
		return DigestUtils.md5Hex(context);
	}
}
