package com.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.CodeContant;

public class CodeResult<E> {

	private boolean success;
	private String msg;
	private String code;
	private List<E> data;
	
	public CodeResult() {
		this.success = true;
		this.msg = CodeContant.AJAX_SUCCESS.getMsg() ;
		this.code = CodeContant.AJAX_SUCCESS.getCode();
		this.data = new ArrayList<E>();
	}
	
	public CodeResult(List<E> data) {
		this.success = true;
		this.msg = CodeContant.AJAX_SUCCESS.getMsg() ;
		this.code = CodeContant.AJAX_SUCCESS.getCode();
		this.data = data;
	}
	
	public CodeResult(String msg) {
		this.success = true;
		this.msg = msg;
		this.code = CodeContant.AJAX_SUCCESS.getCode();
		this.data = new ArrayList<E>();
	}
	
	public CodeResult(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
		this.code = CodeContant.AJAX_SUCCESS.getCode();
		this.data = new ArrayList<E>();
	}
	
	public CodeResult(boolean success, String msg, String code) {
		this.success = success;
		this.msg = msg;
		this.code = code;
		this.data = new ArrayList<E>();
	}
	
	public CodeResult(boolean success, CodeContant codeContant) {
		this.success = success;
		this.msg = codeContant.getMsg();
		this.code = codeContant.getCode();
		this.data = new ArrayList<E>();
	}
	
	public CodeResult(boolean success, String msg, String code, List<E> data) {
		this.success = success;
		this.msg = msg;
		this.code = code;
		this.data = data;
	}
	
	public CodeResult(boolean success, CodeContant codeContant,List<E> data ) {
		this.success = success;
		this.msg = codeContant.getMsg();
		this.code = codeContant.getCode();
		this.data = data;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}	
	
	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public Map<String,Object> toMap(){
		Map<String,Object> res = new HashMap<>();
		res.put("success", isSuccess());
		res.put("msg",  getMsg());
		res.put("code", getCode());
		res.put("data", getData());
		return res;
	}
}
