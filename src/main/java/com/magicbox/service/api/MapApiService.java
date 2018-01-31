package com.magicbox.service.api;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.BeanChecker;
import com.magicbox.base.utilities.HttpUtils;
import com.magicbox.base.utilities.JsonUtils;

@Service
public class MapApiService {

	private static final Logger logger = LoggerFactory.getLogger(MapApiService.class);
	
	/**
	 * 根据地址查坐标
	 * 
	 * @param address
	 * @param cityName
	 * @return
	 */
	public ResponseWrapper<Coordinate> findCoordinateByAddress(String address, String cityName) {
		BeanChecker.getInstance().notBlank(address).notBlank(cityName);
		
		String respStr = HttpUtils.get(String.format("http://restapi.amap.com/v3/geocode/geo?key=74988c7728b368be522738a7dc23f664&address=%s&city=%s", address, cityName));
		LocationResponse resp = JsonUtils.parseObject(respStr, LocationResponse.class);
		if (!"1".equals(resp.getStatus())) {
			logger.error("获取地址坐标失败, address={}, cityName={}, respInfo={}", address, cityName, resp.getInfo());
		}
		
		if (Integer.parseInt(resp.getCount()) <= 0) {
			return ResponseWrapper.fail(ErrorCodes.MAP_API_ERROR);
		}
		
		String location = resp.getGeocodes().get(0).getLocation();
		Coordinate coordinate = new Coordinate();
		coordinate.setLongitude(Double.parseDouble(location.split(",")[0]));
		coordinate.setLatitude(Double.parseDouble(location.split(",")[1]));
		
		return ResponseWrapper.succeed(coordinate);
	}
	
	public static class LocationResponse {
		private String status;
		private String info;
		private String infocode;
		private String count;
		private List<LocationInnerResponse> geocodes;
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public String getInfocode() {
			return infocode;
		}
		public void setInfocode(String infocode) {
			this.infocode = infocode;
		}
		public String getCount() {
			return count;
		}
		public void setCount(String count) {
			this.count = count;
		}
		public List<LocationInnerResponse> getGeocodes() {
			return geocodes;
		}
		public void setGeocodes(List<LocationInnerResponse> geocodes) {
			this.geocodes = geocodes;
		}
		
	}
	
	public static class LocationInnerResponse {
		private String location;

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}
	}
	
	public class Coordinate implements Serializable {

		private static final long serialVersionUID = 1L; 
		
		private Double longitude;
		private Double latitude;
		
		public Double getLongitude() {
			return longitude;
		}
		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}
		public Double getLatitude() {
			return latitude;
		}
		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}
	}
}
