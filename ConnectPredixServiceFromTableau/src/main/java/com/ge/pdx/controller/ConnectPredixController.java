package com.ge.pdx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ge.pdx.bean.Alarms;
import com.ge.pdx.bean.Component;
import com.ge.pdx.service.ConnectPredixService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @author T S Karthikeyan
 *
 */


@RestController
@RequestMapping(value = "/apm")
@Api(value = "/apmService", description = "Services for APM MnD")
@Scope("request")
public class ConnectPredixController {
	
	@Autowired
	private ConnectPredixService pdxService;

	@CrossOrigin(origins = {"http://localhost:8888", "http://3.96.201.77:8888", "http://3.96.190.173:8888", "http://G358ZGC2E.logon.ds.ge.com:8888"}, maxAge=4800, allowCredentials="false")
	@ApiOperation(value = "Retrieve the Asset Details")
	@RequestMapping(value = "/assets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getComponents() {
		
		ErrorMessage err = new ErrorMessage();

		List<String> errLst = new ArrayList<String>();
		
		Component[] components = null;
		
		try {
			components = pdxService.getAssetComponents();
		} catch (Exception e) {
			errLst.add("get Asset Components Failed. " + e.getMessage());
			err.setErrors(errLst);
			return new ResponseEntity<>(err,HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(components, HttpStatus.OK);
		
	}
	
	@CrossOrigin(origins = {"http://localhost:8888", "http://3.96.201.77:8888", "http://3.96.190.173:8888", "http://G358ZGC2E.logon.ds.ge.com:8888"}, maxAge=4800, allowCredentials="false")
	@ApiOperation(value = "Retrieve the Alarm Details")
	@RequestMapping(value = "/alarms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getAlarms() {
		
		ErrorMessage err = new ErrorMessage();

		List<String> errLst = new ArrayList<String>();
		
		Alarms alarm = null;
		
		try {
			alarm = pdxService.getAlarmContents();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			errLst.add("get Alarm Contents Failed. " + e.getMessage());
			err.setErrors(errLst);
			return new ResponseEntity<>(err,HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(alarm, HttpStatus.OK);
		
	}

}
