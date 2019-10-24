package com.sunnet.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sunnet.GenericLib.DatabaseFunction;

public class ProjectUtily {
	
	static DatabaseFunction db = new DatabaseFunction();

	public static List<String> getObjProjectData(DatabaseFunction db) {
		return db.getTestDataObject("Select * from objProjectEntry","ObjectRepository");
	}
	
	public static Map<String,List<String>> getMapObjProjectData(List<String> objProjectData) {
		
		Map<String, List<String>> objMapProjectData = new HashMap<String, List<String>>(); 
		
		for(int i = 0; i < objProjectData.size() ;) {
			
			List<String> tempObjProjectData = new ArrayList<String>();
			
			tempObjProjectData.add(0, objProjectData.get(i+1));
			tempObjProjectData.add(1, objProjectData.get(i+2));
			
			objMapProjectData.put(objProjectData.get(i),tempObjProjectData);
			
			i = i + 3;
			
		}
		
		return objMapProjectData;
		
	}

public static List<String> getListByString(String text) {
		
		return Arrays.asList(text.trim().split("\\s*,\\s*"));
		
		
	}
		
		
	}


