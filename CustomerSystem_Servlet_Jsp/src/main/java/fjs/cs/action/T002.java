/**
 * @(#)T002.java 16-00 2023/02/22
 *
 * Copyright(C) 2023 by TranVanToi  LTV
 *
 * Last_Update 2023/02/22.
 * Version 1.00.
 */

package fjs.cs.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * T002
 * 
 * @version 1.00
 * @since 1.00
 * @author toi_tv_ttv
 * 
 */
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import fjs.cs.common.Constants;
import fjs.cs.dao.T001Dao;
import fjs.cs.dao.T002Dao;
import fjs.cs.dto.T001Dto;
import fjs.cs.dto.T002Dto;


public class T002 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Init man hinh
	 * 
	 * @param HttpServletRequest  req
	 * @param HttpServletResponse resp
	 * @return RequestDispatcher
	 * @throws ServletException, IOException
	 * @since 1.00
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String indexPage = request.getParameter("index");
		if (indexPage == null) {
			indexPage = "1";
		}
		
		int index = Integer.parseInt(indexPage);
		request.setAttribute("tag", index);
		T002Dao dao = new T002Dao();
		
		
		//get data search
		List<T002Dto> list = dao.getData();
		
		request.setAttribute("listData", list);
		//page 
		int count = dao.getDataPage();
		//trang cuoi cùng
		int endpage = count/15;
		if (count % 15 != 0) {
			endpage++;
		}
		
		List<T002Dto> listPage = dao.pagingData(index);
		request.setAttribute("ListA", listPage);
		request.setAttribute("endP", endpage);
		request.getRequestDispatcher(Constants.T002_SEARCH);
		
		
				//request.getRequestDispatcher("/WEB-INF/jsp/T002.jsp").forward(request, response);
		RequestDispatcher myRD = null;
		// Hien thi man hinh Login
		myRD = request.getRequestDispatcher(Constants.T002_SEARCH);
		myRD.forward(request, response);
	}
	
	/**
	 * Event man hinh
	 * 
	 * @param HttpServletRequest  req
	 * @param HttpServletResponse resp
	 * @return RequestDispatcher
	 * @throws ServletException, IOException
	 * @since 1.00
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try {
			String name = request.getParameter("txtCustomerName");
			String sex = request.getParameter("browser");
			String birthdayFrom = request.getParameter("txtBirthdayFromName");
			String birthdayTo = request.getParameter("txtBirthdayToName");
			T002Dao daoSearch = new T002Dao();
			
			String[] ids = request.getParameterValues("selectedValues");
			if (ids != null && ids.length > 0) {
//				int[] intIds = new int[ids.length];
//			    for (int i = 0; i < ids.length; i++) {
//			        intIds[i] = Integer.parseInt(ids[i]);
//			        S
//			    }
			    // Chuyển mảng ids sang chuỗi ngăn cách bởi dấu phẩy
			    String idList = String.join(",", ids);
			   
			    // Loại bỏ các dấu ngoặc kép trong chuỗi
			    idList = idList.replace("[\"", "").replace("\"]", "");
			    String result = idList.replaceAll("[^\\d,]", "").replace("\"", "");
			    String[] result2 = result.split(",");
			    for (int i = 0;i< result2.length; i++) {	
			    	if (result2[i] == "") {
			    		//result2[i].
			    	}else {			    		
			    		daoSearch.deleteData(result2);
			    	}
			    }
			   
			}
			List<T002Dto> resultSearch = daoSearch.getDataSearch(name, sex, birthdayFrom, birthdayTo);
			request.setAttribute("listDataSearch", resultSearch);
			
			
			//String[] selecValue = request.getParameterValues("selectedValues");
			//daoSearch.deleteData(selecValue);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/T002.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
