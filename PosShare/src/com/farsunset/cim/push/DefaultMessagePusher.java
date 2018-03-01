/**
 * Copyright 2013-2023 Xia Jun(3979434@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ***************************************************************************************
 *                                                                                     *
 *                        Website : http://www.farsunset.com                           *
 *                                                                                     *
 ***************************************************************************************
 */
package com.farsunset.cim.push;

import com.farsunset.cim.sdk.server.model.Message;
import com.farsunset.cim.sdk.server.session.CIMSession;
import com.farsunset.cim.sdk.server.session.DefaultSessionManager;

/**
 * 娑堟伅鍙戦�佸疄鐜扮被
 * 
 */
public class DefaultMessagePusher implements  CIMMessagePusher {


	private DefaultSessionManager sessionManager;
	
 
    public void setSessionManager(DefaultSessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}
    public boolean isOnLine(String receiver){
    	CIMSession session = sessionManager.get(receiver);
        if (session != null && session.isConnected()) {
			return true;
		}
        return false;
    }
	/**
     * 鍚戠敤鎴峰彂閫佹秷鎭�
     * @param msg
     */
	public void push(Message msg) {
		CIMSession session = sessionManager.get(msg.getReceiver());
		
		/*
		 * 鏈嶅姟鍣ㄩ泦缇ゆ椂锛屽彲浠ュ湪姝�
		 * 鍒ゆ柇褰撳墠session鏄惁杩炴帴浜庢湰鍙版湇鍔″櫒锛屽鏋滄槸锛岀户缁線涓嬭蛋锛屽鏋滀笉鏄紝灏嗘娑堟伅鍙戝線褰撳墠session杩炴帴鐨勬湇鍔″櫒骞� return
		 * if(session!=null&&!session.isLocalhost()){//鍒ゆ柇褰撳墠session鏄惁杩炴帴浜庢湰鍙版湇鍔″櫒锛屽涓嶆槸
		 * //鍙戝線鐩爣鏈嶅姟鍣ㄥ鐞�
		 * MessageDispatcher.execute(MessageUtil.transform(msg),session.getHost()); 
		 * return; 
		 * }
		 */
         
		if (session != null && session.isConnected()) {
			System.out.println("发送:"+msg.getContent());
			session.write(msg);
			return;
		}
		
		// 濡傛灉鐢ㄦ埛鏍囩ず浜咥PNS ON 璇存槑杩欐槸ios璁惧闇�瑕佷娇鐢╝pns鍙戦��
		if (session != null && session.getApnsAble() == CIMSession.APNS_ON) {
						
			apnsPush(1,msg.getContent(),session.getDeviceId());
		}
		 
	}

	/**
	 * 寮曞叆javaapns鐩稿叧jar
	 * @param badge
	 * @param content
	 * @param token
	 */
	private void apnsPush(int badge,String content,String token){
		/*String password = "password";
		String keystore = "p12 鏂囦欢 缁濆璺緞";
		boolean isDebug = true;

		PushNotificationPayload payload = PushNotificationPayload.complex();
		try {
			payload.addAlert(content);
			payload.addBadge(1);
			payload.addSound("default");
			Push.payload(payload, keystore, password, isDebug, token);

		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
 
}
