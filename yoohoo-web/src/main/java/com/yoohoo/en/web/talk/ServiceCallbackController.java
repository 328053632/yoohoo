package com.yoohoo.en.web.talk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yoohoo.en.service.ITalkCloudService;

@Controller
@RequestMapping("/talk/callback")
public class ServiceCallbackController {

	@Autowired
	private ITalkCloudService talkCloudService;
	
    @RequestMapping(value = "/startLessonCallBack",method = RequestMethod.POST)
    @ResponseBody
    public String startLessonCallBack(@RequestParam(name="serial") String roomId)
    {
    	talkCloudService.startLessonCallBack(roomId);
    	return  "success";
    }

    @RequestMapping(value="/finishLessonCallBack",method = RequestMethod.POST)
    @ResponseBody
    public String finishLessonCallBack(@RequestParam(name="serial") String roomId)
    {
    	talkCloudService.finishLessonCallBack(roomId);
        return  "success";
    }
    
    @RequestMapping("/loginLogoutCallBack")
    @ResponseBody
    public void loginLogoutCallBack(@RequestParam(name="serial") String roomId,
        @RequestParam(name="userid") String userId,
        @RequestParam(name="role") Integer roleType,
        @RequestParam(name="status") Integer status)
        {
            talkCloudService.loginLogoutCallBack(roomId, userId, roleType, status);
        }
}
