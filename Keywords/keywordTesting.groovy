import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import groovy.json.JsonSlurper

import internal.GlobalVariable

public class keywordTesting {

	@Keyword
	def addUser (ResponseObject response){
		KeywordUtil.logInfo("HEADER\n"+response.getHeaderFields()+"\n\nBODY\n"+response.getResponseBodyContent())
		JsonSlurper jsonSluper = new JsonSlurper()
		def jsonResp = jsonSluper.parseText(response.getResponseText())
		KeywordUtil.logInfo("Berikut ID User: " + jsonResp.id)
		if (jsonResp.id != null) {
			KeywordUtil.logInfo("API Pass Berhasil Menambah User")
		}
		else {
			KeywordUtil.markFailed("API Failed 400")
		}
	}

	@Keyword
	def getUser (ResponseObject response){
		KeywordUtil.logInfo("HEADER\n"+response.getHeaderFields()+"\n\nBODY\n"+response.getResponseBodyContent())
		JsonSlurper jsonSluper = new JsonSlurper()
		def jsonResp = jsonSluper.parseText(response.getResponseText())
		KeywordUtil.logInfo("Berikut ID User: " + jsonResp.id)
		if (jsonResp.id != null) {
			KeywordUtil.logInfo("API Pass Berhasil Menemukan ID User")
		}
		else {
			KeywordUtil.markFailed("API Failed 400")
		}
	}

	@Keyword
	def editUser (ResponseObject response){
		KeywordUtil.logInfo("HEADER\n"+response.getHeaderFields()+"\n\nBODY\n"+response.getResponseBodyContent())
		JsonSlurper jsonSluper = new JsonSlurper()
		def jsonResp = jsonSluper.parseText(response.getResponseText())
		if (jsonResp.id != null) {
			KeywordUtil.logInfo("API Pass Berhasil Edit User")
		}
		else {
			KeywordUtil.markFailed("API Failed 400")
		}
	}

	@Keyword
	def deleteUser (ResponseObject response){
		KeywordUtil.logInfo("HEADER\n"+response.getHeaderFields()+"\n\nBODY\n"+response.getResponseBodyContent())
		JsonSlurper jsonSluper = new JsonSlurper()
		def jsonResp = jsonSluper.parseText(response.getResponseText())
		if (jsonResp.message == 'Resource not found') {
			KeywordUtil.markFailed("API Pass Berhasil Menghapus User")
		}
	}
}
