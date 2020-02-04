package com.yoohoo.en.service;

import com.yoohoo.en.session.MySession;

public interface SessionSerivce {
	
	MySession getSession(String sessionId);

	MySession createSession(String sessionId);
}
