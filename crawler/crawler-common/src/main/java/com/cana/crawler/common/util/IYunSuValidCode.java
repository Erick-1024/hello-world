package com.cana.crawler.common.util;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface IYunSuValidCode {

	public String getCheckCodeByWebElement(String typeId, WebElement element, WebDriver driver) throws Exception;
	
	public String createByPost(String typeid, File imageFile);
	
	public String createByPostWithURL(String typeid,String valiCodeUrl);
	
	public String createByPost(String typeid, String imagePath);
	
}
