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
package com.farsunset.cim.api.action;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.bind.ServletRequestBindingException;

import com.farsunset.cim.push.DefaultMessagePusher;
import com.farsunset.cim.push.SystemMessagePusher;
import com.farsunset.cim.sdk.server.model.Message;
import com.farsunset.cim.util.Constants;
import com.farsunset.cim.util.ContextHolder;
import com.farsunset.cim.util.StringUtil;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/** 
 *
 * @author farsunset (3979434@qq.com)
 */
public class MessageAction  extends  ActionSupport  implements ModelDriven<Message>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Message message = new Message();
 
  
    public String send() throws Exception {
        
    	HashMap<String,Object> datamap = new HashMap<String,Object>();
    	HashMap<String,String> data = new HashMap<String,String>();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		datamap.put("code", 200);
		
		try{
	        
			checkParams();
			System.out.println(message.getContent());
			message.setMid(StringUtil.getUUID());
			if(Constants.MessageType.TYPE_2.equals(message.getAction()))
			{
				  //鍚戝鎴风 鍙戦�佹秷鎭�
		        ContextHolder.getBean(SystemMessagePusher.class).push(message);
			}else
			{
				  //鍚戝鎴风 鍙戦�佹秷鎭�
		        ((DefaultMessagePusher)ContextHolder.getBean("messagePusher")).push(message);
			}
	              
	        data.put("id", message.getMid());
	        data.put("createTime", String.valueOf(message.getTimestamp()));
	        datamap.put("data", data);
		}catch(Exception e){
			
			datamap.put("code", 500);
			e.printStackTrace();
		}
       
		ServletActionContext.getResponse().getWriter().print(new Gson().toJson(datamap));
        return null;
    }
    
  

    
    /**
     * 鏂囦欢鐢卞鎴风鍙戝線闃块噷浜� OSS 瀛樺偍
     * @param messageServiceImpl
     */
   /* private void fileHandler(Message mo, HttpServletRequest request) throws IOException 
    {
    	if(request instanceof MultiPartRequestWrapper){
			MultiPartRequestWrapper pr = (MultiPartRequestWrapper) request;
			if(pr.getFiles("file")!=null)
			{
				File file = pr.getFiles("file")[0];
		         
		        String fileType = request.getParameter("fileType");
		        String dir = dirMap.get(fileType);
		        if(StringUtils.isEmpty(dir))
		        {
		          	  throw new IllegalArgumentException("fileType:" +fileType+" 鏈畾涔�" );
		          	  
		        }
		        	String path = request.getSession().getServletContext().getRealPath(dir);
		        	String uuid=UUID.randomUUID().toString().replaceAll("-", "");
		 		    File des = new File(path+"/"+uuid);
		 		    FileUtil.copyFile(file, des);
		 		    mo.setFile(dir+"/"+uuid);
		 		    mo.setFileType(fileType);
			}
        }
          
    }*/
    
    
    private void checkParams() throws ServletRequestBindingException  
    {
    	   
          if(StringUtils.isEmpty(message.getReceiver()))
          {
        	  throw new IllegalArgumentException("receiver 涓嶈兘涓虹┖!");
        	  
          }
    }
 
	public Message getModel() {
		// TODO Auto-generated method stub
		return message;
	}

 
 
}
