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

public class BBBv0p8p0 extends BBBProxyImpl {

	public BBBv0p8p0(){
		super();
	}
	
	public String getGetRecordingsURL(String meetingID) {
	    
	    String queryString = "meetingID=" + meetingID;
	    queryString += getCheckSumParameterForQuery(APICALL_GETRECORDINGS, queryString);
	    
	    return this.endpoint + API_SERVERPATH + APICALL_GETRECORDINGS + "?" + queryString;
	}

	public String getPublishRecordingsURL(String recordID, String publish) {
	    
	    String endpoint = "recordID=" + recordID;
	    endpoint += "&publish=" + publish;
	    endpoint += getCheckSumParameterForQuery(APICALL_PUBLISHRECORDINGS, endpoint);
	    
	    return this.endpoint + API_SERVERPATH + APICALL_PUBLISHRECORDINGS + "?" + endpoint;
	}

	public String getDeleteRecordingsURL(String recordID) {
	    
	    String endpoint = "recordID=" + recordID;
	    endpoint += getCheckSumParameterForQuery(APICALL_DELETERECORDINGS, endpoint);
	    
	    return this.endpoint + API_SERVERPATH + APICALL_DELETERECORDINGS + "?" + endpoint;
	}

}
