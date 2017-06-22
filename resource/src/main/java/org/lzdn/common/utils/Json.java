package org.lzdn.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Json {

	public static final String header = "header";
	public static final String body = "body";

	public static JSONObject createResult() {
		JSONObject result = new JSONObject();
		result.put(header, new JSONObject());
		result.put(body, new JSONObject());
		return result;
	}

	public static void setHeader(JSONObject jSONObject, String key, Object obj) {
		JSONObject Jheader = (JSONObject) jSONObject.get(header);
		Jheader.put(key, obj);
	}

	public static void setBody(JSONObject jSONObject, String key, Object obj) {
		JSONObject Jbody = (JSONObject) jSONObject.get(body);
		Jbody.put(key, obj);
	}

	/**
	 * @Title: simpleJsonToDto
	 * @Description: Json字符串转对象
	 * @param @param
	 *            json @param @param obj @param @return 设定文件 @return Object
	 *            返回类型 @throws
	 */
	public static Object simpleJsonToDto(String context, Object model) {

		try {
			if (StringUtils.isEmpty(context) || model == null)
				return null;
			JSONObject jsonObject = JSONObject.parseObject(context);
			if (jsonObject == null)
				return null;
			Class<?> modelClass = model.getClass();
			Field[] field = modelClass.getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
			if (field == null || field.length == 0)
				modelClass = modelClass.getSuperclass();
			field = modelClass.getDeclaredFields();
			for (Field fd : field) {
				String name = fd.getName(); // 获取属性的名字
				name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造
				String type = fd.getGenericType().toString(); // 获取属性的类型

				if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class
																// "，后面跟类名
					Method m = modelClass.getMethod("set" + name, String.class);
					m.invoke(model, jsonObject.getString(fd.getName()));
					/*
					 * String value = (String) m.invoke(model); //
					 * 调用getter方法获取属性值 if (value != null) {
					 * System.out.println("attribute value:" + value); }
					 */
				}
				if (type.equals("class java.lang.Integer")) {
					Method m = modelClass.getMethod("set" + name, Integer.class);
					m.invoke(model, jsonObject.getInteger(fd.getName()));
					/*
					 * Integer value = (Integer) m.invoke(model); if (value !=
					 * null) { System.out.println("attribute value:" + value); }
					 */
				}
				if (type.equals("class java.lang.Short")) {
					Method m = modelClass.getMethod("set" + name, Short.class);
					m.invoke(model, jsonObject.getShort(fd.getName()));
					/*
					 * Short value = (Short) m.invoke(model); if (value != null)
					 * { System.out.println("attribute value:" + value); }
					 */
				}
				if (type.equals("class java.lang.Double")) {
					Method m = modelClass.getMethod("set" + name, Double.class);
					m.invoke(model, jsonObject.getDouble(fd.getName()));
					/*
					 * Double value = (Double) m.invoke(model); if (value !=
					 * null) { System.out.println("attribute value:" + value); }
					 */
				}
				if (type.equals("class java.lang.Boolean")) {
					Method m = modelClass.getMethod("set" + name, Boolean.class);
					m.invoke(model, jsonObject.getBoolean(fd.getName()));
					/*
					 * Boolean value = (Boolean) m.invoke(model); if (value !=
					 * null) { System.out.println("attribute value:" + value); }
					 */
				}
				if (type.equals("class java.util.Date")) {
					Method m = modelClass.getMethod("set" + name, Date.class);
					m.invoke(model, jsonObject.getDate(fd.getName()));
					/*
					 * Date value = (Date) m.invoke(model); if (value != null) {
					 * System.out.println("attribute value:" +
					 * value.toLocaleString()); }
					 */
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;
	}

	/**
	 * 
	 * @Title: listToJsonArray @Description: List to jsonArray @param @param
	 *         list @param @return 设定文件 @return JSONArray 返回类型 @throws
	 */
	public static JSONArray listToJsonArray(List<Object> list) {
		JSONArray jsonArray = new JSONArray();
		try {
			Class<?> modelClass = null;
			Field[] field = null;
			if (list == null || list.size() == 0)
				return null;

			for (Object object : list) {
				JSONObject jsonObject = new JSONObject();
				modelClass = object.getClass();
				field = modelClass.getDeclaredFields();
				if (field == null || field.length == 0)
					modelClass = modelClass.getSuperclass();
				for (Field fd : field) {
					String name = fd.getName(); // 获取属性的名字
					name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造
					String type = fd.getGenericType().toString(); // 获取属性的类型
					Method m = null;
					if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class
																	// //
																	// "，后面跟类名
						m = modelClass.getMethod("get" + name, String.class);
					}
					if (type.equals("class java.lang.Integer")) {
						m = modelClass.getMethod("get" + name, Integer.class);
					}
					if (type.equals("class java.lang.Short")) {
						m = modelClass.getMethod("get" + name, Short.class);
					}
					if (type.equals("class java.lang.Double")) {
						m = modelClass.getMethod("get" + name, Double.class);
					}
					if (type.equals("class java.lang.Boolean")) {
						m = modelClass.getMethod("get" + name, Boolean.class);
					}
					if (type.equals("class java.util.Date")) {
						m = modelClass.getMethod("get" + name, Date.class);
					}
					String value = (String) m.invoke(object);
					jsonObject.put(fd.getName(), value);
				}
				jsonArray.add(jsonObject);
			}

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;

	}
}
