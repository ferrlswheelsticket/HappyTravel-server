package com.solstice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solstice.bean.MapUser;
import com.solstice.bean.Result;
import com.solstice.bean.ResultCode;
import com.solstice.service.MapUserService;

@Controller
@RequestMapping(value = "/map")
public class MapUserController {
	@Autowired
	private MapUserService mapUserService;

	@RequestMapping(value = "/list")
	public String getMapModelList(HttpServletRequest request, String id) {
		Result result = null;
		try {
			List<MapUser> models = (ArrayList<MapUser>) mapUserService
					.getMapUserList(id);
			result = new Result(ResultCode.SUCESS, "获取用户好友列表",
					models);
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "获取失败", null);
			e.printStackTrace();
		}

		request.setAttribute("message", result.toString());
		return "message";
	}

	@RequestMapping(value = "/message")
	public String Contact() {
		return "message";
	}

	@RequestMapping(value = "/add")
	public String addModel(HttpServletRequest request, String id, String ugId,
			String gName) {
		Result result = null;
		try {
			String name = new String(gName.getBytes("gbk"), "utf-8");
			mapUserService.addMapUser(id, ugId, name);
			result = new Result(ResultCode.SUCESS, "添加成功", null);
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "添加失败", null);
			e.printStackTrace();
		}

		request.setAttribute("message", result.toString());
		return "message";
	}

	@RequestMapping(value = "/delete")
	public String delteteMapUser(HttpServletRequest request, String id,
			String ugId) {
		Result result = null;
		try {
			mapUserService.deleteMapUser(id, ugId);
			result = new Result(ResultCode.SUCESS, "删除成功", null);
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "删除失败", null);
			e.printStackTrace();
		}
		request.setAttribute("message", result.toString());
		return "message";
	}

	@RequestMapping(value = "/update")
	public String updateMapUserGroupName(HttpServletRequest request, int id,
			int contactId) {
		Result result = null;
		try {
			result = new Result(ResultCode.SUCESS, "更新成功", null);
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "更新失败", null);
			e.printStackTrace();
		}

		request.setAttribute("message", result.toString());
		return "message";

	}

	@RequestMapping(value = "/move")
	public String moveMapUser(HttpServletRequest request, String id,
			String contactId, String toGroup) {
		Result result = null;
		try {
			mapUserService.moveMapUser(id, contactId, toGroup);
			result = new Result(ResultCode.SUCESS, "移动成功", null);
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "移动失败", null);
			e.printStackTrace();
		}

		request.setAttribute("message", result.toString());
		return "message";
	}
}