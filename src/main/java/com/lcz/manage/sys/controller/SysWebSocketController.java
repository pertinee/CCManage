package com.lcz.manage.sys.controller;

import com.lcz.manage.util.R;
import com.lcz.manage.util.websocket.WebSocketServer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


/**
 *
 * @author luchunzhou
 */
@Controller
public class SysWebSocketController {

	private static final Logger logger = Logger.getLogger(SysWebSocketController.class);

    /**
     * 群发消息
     * @param userId
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/websocket/sendInfo/{userId}", method = RequestMethod.GET)
	public R sendInfo(@PathVariable("userId") String userId) {
		try {
			logger.info("有新客户呼入,userId:"+ userId);
			WebSocketServer.sendInfo("有新客户呼入,userId:"+ userId);
		}catch (IOException e) {
			R.error("websocket错误");
		}
		return R.ok("websocket成功");
	}
}
