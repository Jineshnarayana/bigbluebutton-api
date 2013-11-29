/*
	BigBlueButton - http://www.bigbluebutton.org

	Copyright (c) 2008-2012 by respective authors (see below). All rights reserved.

	BigBlueButton is free software; you can redistribute it and/or modify it under the
	terms of the GNU Lesser General Public License as published by the Free Software
	Foundation; either version 2 of the License, or (at your option) any later
	version.

	BigBlueButton is distributed in the hope that it will be useful, but WITHOUT ANY
	WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
	PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.

	You should have received a copy of the GNU Lesser General Public License along
	with BigBlueButton; if not, If not, see <http://www.gnu.org/licenses/>.

	Author: Jesus Federico <jesus@blindsidenetworks.com>
*/ 
package org.bigbluebutton.impl;

import org.bigbluebutton.api.BBBProxy;

import java.net.URLEncoder;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class BBBProxyImpl implements BBBProxy{

	// API Server Path
	protected final static String API_SERVERPATH = "/api/";

	// API Calls
	protected final static String APICALL_CREATE 			= "create";
	protected final static String APICALL_JOIN 				= "join";
	protected final static String APICALL_ISMEETINGRUNNING 	= "isMeetingRunning";
	protected final static String APICALL_END 				= "end";
	protected final static String APICALL_GETMEETINGINFO 	= "getMeetingInfo";
	protected final static String APICALL_GETMEETINGS 		= "getMeetings";
	protected final static String APICALL_GETRECORDINGS 	= "getRecordings";
	protected final static String APICALL_PUBLISHRECORDINGS = "publishRecordings";
	protected final static String APICALL_DELETERECORDINGS 	= "deleteRecordings";

	// API Response Codes
	protected final static String APIRESPONSE_SUCCESS = "SUCCESS";
	protected final static String APIRESPONSE_FAILED = "FAILED";

	// API MesageKey Codes
	protected final static String MESSAGEKEY_IDNOTUNIQUE = "idNotUnique";
	protected final static String MESSAGEKEY_DUPLICATEWARNING = "duplicateWarning";

	protected final static String PARAMETERENCODING = "UTF-8";

	String endpoint;
	String secret;

	BBBProxyImpl() {
	    this.endpoint = "http://test-install.blindsidenetworks.com/bigbluebutton/";
	    this.secret = "8cd8ef52e8e101574e400365b55e11a6";
	}

	BBBProxyImpl(String endpoint, String secret) {
	    this.endpoint = endpoint;
	    this.secret = secret;
	}

	public void setUrl(String endpoint){
	    this.endpoint = endpoint;
	}

	public void setSalt(String secret){
	    this.secret = secret;
	}

	public String getCreateURL(String name, String meetingID, String attendeePW, String moderatorPW, String welcome, String dialNumber, String voiceBridge, String webVoice, String logoutURL, String maxParticipants, String record, String duration, String meta ) {

	    String endpoint;
	    endpoint = "name=" + getStringEncoded(name);
	    endpoint += "&meetingID=" + meetingID;
	    endpoint += "&moderatorPW=" + moderatorPW;
	    endpoint += "&attendeePW=" + attendeePW;
	    endpoint += "&welcome=" + getStringEncoded(welcome);
	    endpoint += "&logoutURL=" + getStringEncoded(logoutURL);
	    endpoint += "&maxParticipants=" + maxParticipants;
	    endpoint += "&voiceBridge=" + voiceBridge;
	    endpoint += "&dialNumber=" + dialNumber;
	    endpoint += "&webVoice=" + webVoice;
	    endpoint += "&record=" + record;
	    endpoint += "&duration=" + duration;
	    endpoint += "&" + meta;

	    endpoint += getCheckSumParameterForQuery(APICALL_CREATE, endpoint);
	    
	    return this.endpoint + API_SERVERPATH + APICALL_CREATE + "?" + endpoint;
	}

	public String getJoinURL(String fullName, String meetingID, String password, String createTime, String userID) {
	    String endpoint = getJoinURL(fullName, meetingID, password, createTime, userID, "" );
	    return endpoint;
	    
	}

	public String getJoinURL(String fullName, String meetingID, String password, String createTime, String userID, String webVoiceConf ) {

	    String endpoint;
	    endpoint = "fullName=" + getStringEncoded(fullName);
	    endpoint += "&meetingID=" + meetingID;
	    endpoint += "&password=" + password;
	    endpoint += "".equals(createTime)? "": "&createTime=" + createTime;
	    endpoint += "&userID=" + userID;
	    endpoint += "&webVoiceConf=" + webVoiceConf;

	    endpoint += getCheckSumParameterForQuery(APICALL_JOIN, endpoint);
	    
	    return this.endpoint + API_SERVERPATH + APICALL_JOIN + "?" + endpoint;
	}

	public String getIsMeetingRunningURL(String meetingID) {

	    String endpoint = "meetingID=" + meetingID;
	    endpoint += getCheckSumParameterForQuery(APICALL_ISMEETINGRUNNING, endpoint);
	    
	    return this.endpoint + API_SERVERPATH + APICALL_ISMEETINGRUNNING + "?" + endpoint;
	}

	public String getEndURL(String meetingID, String password) {
	    
	    String endpoint = "meetingID=" + meetingID;
	    endpoint += "&password=" + password;
	    endpoint += getCheckSumParameterForQuery(APICALL_END, endpoint);
	    
	    return this.endpoint + API_SERVERPATH + APICALL_END + "?" + endpoint;
	}

	public String getGetMeetingInfoURL(String meetingID, String password) {
	    
	    String endpoint = "meetingID=" + meetingID;
	    endpoint += "&password=" + password;
	    endpoint += getCheckSumParameterForQuery(APICALL_GETMEETINGINFO, endpoint);
	    
	    return this.endpoint + API_SERVERPATH + APICALL_GETMEETINGINFO + "?" + endpoint;
	}

	public String getGetMeetingsURL(String meetingID, String password) {
	    
	    String endpoint = getCheckSumParameterForQuery(APICALL_END, "");
	    
	    return this.endpoint + API_SERVERPATH + APICALL_END + "?" + endpoint;
	}

	public String getStringEncoded(String string){
	    String stringEncoded = "";
	    
	    try {
	        stringEncoded = URLEncoder.encode(string, PARAMETERENCODING); 
	    } catch(Exception e){}
	    
	    return stringEncoded;
	}

	/** Creates the checksum parameter to be included as part of the endpoint */
	protected String getCheckSumParameterForQuery(String apiCall, String queryString) {
	    if (this.secret != null)
	        return "&checksum=" + DigestUtils.shaHex(apiCall + queryString + this.secret);
	    else
	        return "";
	}

}
