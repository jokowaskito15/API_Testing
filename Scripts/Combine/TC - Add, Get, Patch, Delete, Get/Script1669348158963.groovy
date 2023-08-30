import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import groovy.json.JsonBuilder as JsonBuilder
import groovy.json.JsonOutput as JsonOutput

TestData excelData = findTestData('Data Files/Data User')
TestData excelDataEdit = findTestData('Data Files/Edit User')

ResponseObject orderId = WebUI.callTestCase(findTestCase('Test Cases/Single/TC - Add User'),
	[
		('token') : excelData.getValue('Token', 1),
		('nama') : excelData.getValue('Nama', 1),
		('gender'): excelData.getValue('Gender', 1),
		('email') : excelData.getValue('Email', 1),
		('status') : excelData.getValue('Status', 1)
	],
	FailureHandling.STOP_ON_FAILURE)

JsonSlurper jsonSlurper = new JsonSlurper()

def jsonResp = jsonSlurper.parseText(orderId.getResponseText())

def getId = jsonResp.id

KeywordUtil.logInfo('ID ' + getId)

WebUI.callTestCase(findTestCase('Test Cases/Single/TC - Get User'),
	[
		('token') : excelData.getValue('Token', 1),
		('id') : getId
	],
	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Single/TC - Edit User'),
	[
		('token') : excelDataEdit.getValue('Token', 1),
		('namaEdit') : excelDataEdit.getValue('Nama', 1),
		('emailEdit') : excelDataEdit.getValue('Email', 1),
		('statusEdit') : excelDataEdit.getValue('Status', 1),
		('id') : getId
	],
	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Single/TC - Delete User'),
	[
		('token') : excelData.getValue('Token', 1),
		('id') : getId
	],
	FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Single/TC - Get User'),
	[
		('token') : excelData.getValue('Token', 1),
		('id') : getId
	],
	FailureHandling.STOP_ON_FAILURE)




