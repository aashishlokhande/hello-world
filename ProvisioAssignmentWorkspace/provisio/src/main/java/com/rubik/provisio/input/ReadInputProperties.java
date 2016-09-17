package com.rubik.provisio.input;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * Utility class to read input from static properties file and
 * convert it into WelfareUserInfo object to be used for entitlement calculations
 */
public class ReadInputProperties {

	WelfareUserInfo welfareUser;
	InputStream inputStream;
 
	public WelfareUserInfo getWelfareUser(String propFileName) {
		
		try {
			Properties prop = new Properties();
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			DateTimeFormatter df =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			// populate user object with property file values
			welfareUser = new WelfareUserInfo(prop.getProperty("calculateTillYear").isEmpty()?0:Integer.parseInt(prop.getProperty("calculateTillYear")),
												LocalDate.parse(prop.getProperty("startDate"), df),
												Double.parseDouble(prop.getProperty("incomePerYear")),
												Integer.parseInt(prop.getProperty("workTenureRemainingInYears")),
												Double.parseDouble(prop.getProperty("totalAsset")),
												Double.parseDouble(prop.getProperty("assetIncrementPercentage")),
												Boolean.parseBoolean(prop.getProperty("isSingle")));

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return welfareUser;
	}
}
