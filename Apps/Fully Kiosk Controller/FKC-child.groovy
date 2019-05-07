/**
 *  ****************  Fully Kiosk Control App  ****************
 *  Design Usage:
 *  Take control of a Fully Kiosk device!
 *
 *  Copyright 2019 Bryan Turcotte (@bptworld)
 * 
 *  This App is free.  If you like and use this app, please be sure to give a shout out on the Hubitat forums to let
 *  people know that it exists!  Thanks.
 *
 *  Remember...I am not a programmer, everything I do takes a lot of time and research!
 *  Donations are never necessary but always appreciated.  Donations to support development efforts are accepted via: 
 *
 *  Paypal at: https://paypal.me/bptworld
 * 
 *  Unless noted in the code, ALL code contained within this app is mine. You are free to change, ripout, copy, modify or
 *  otherwise use the code in anyway you want. This is a hobby, I'm more than happy to share what I have learned and help
 *  the community grow. Have FUN with it!
 * 
 *-------------------------------------------------------------------------------------------------------------------
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 * ------------------------------------------------------------------------------------------------------------------------------
 *
 *  If modifying this project, please keep the above header intact and add your comments/credits below - Thank you! -  @BPTWorld
 *  App and Driver updates can be found at https://github.com/bptworld/Hubitat/
 *
 * ------------------------------------------------------------------------------------------------------------------------------
 *
 *  Changes:
 *
 *  V1.0.0 - 05/07/19 - Initial release.
 *
 */

def setVersion() {
	state.version = "v1.0.0"
}

definition(
    name: "Fully Kiosk Control Child",
    namespace: "BPTWorld",
    author: "Bryan Turcotte",
    description: "Take control of a Fully Kiosk device!",
    category: "",
	parent: "BPTWorld:Fully Kiosk Control",
    iconUrl: "",
    iconX2Url: "",
    iconX3Url: "",
	importUrl: "https://raw.githubusercontent.com/bptworld/Hubitat/master/Apps/Fully%20Kiosk%20Controller/FKC-child.groovy",
)

preferences {
    page(name: "pageConfig")
	page name: "pushOptions", title: "", install: false, uninstall: true, nextPage: "pageConfig"
}

def pageConfig() {
    dynamicPage(name: "", title: "<h2 style='color:#1A77C9;font-weight: bold'>Fully Kiosk Control</h2>", install: true, uninstall: true, refreshInterval:0) {
		display() 
        section("Instructions:", hideable: true, hidden: true) {
			paragraph "<b>Notes:</b>"
			paragraph "* Vitual Device must use the <a href='https://community.hubitat.com/t/release-fully-kiosk-browser-controller/12223' target='_blank'>Fully Kiosk Browser Controller</a> driver by @GavinCampbell"
			paragraph "To be honest, I don't use many of the features available here. But they are in the driver, so I made them available.  Please visit the driver post listed above for more details on what each option does and how to use them.  Thanks!"
		}
		section(getFormat("header-green", "${getImage("Blank")}"+" Fully Kiosk Device")) {
			input "fullyDevice", "capability.actuator", title: "Virtual Device created with Fully Kiosk Browser Controller", required: true, multiple: true
		}
		section(getFormat("header-green", "${getImage("Blank")}"+" Triggers")) {
			input "myContact", "capability.contactSensor", title: "Select the contact sensor to activate the event", required: false, multiple: true
			input "myMotion", "capability.motionSensor", title: "Select the motion sensor to activate the event", required: false, multiple: true
			input "mySwitch", "capability.switch", title: "Select the switch to activate the event", required: false, multiple: true
		}
		section(getFormat("header-green", "${getImage("Blank")}"+" BEFORE Display Options")) {
			input(name: "optBringFullyToFront1", type: "bool", defaultValue: "false", title: "Bring Fully to Front?", description: "bring to front", submitOnChange: "true", width: 6)
			input(name: "optLoadStartURL1", type: "bool", defaultValue: "false", title: "Load Start URL?", description: "start URL", submitOnChange: "true", width: 6)
			input(name: "optStartScreensaver1", type: "bool", defaultValue: "false", title: "Start Screensaver?", description: "start screensaver", submitOnChange: "true", width: 6)
			input(name: "optStopScreensaver1", type: "bool", defaultValue: "false", title: "Stop Screensaver?", description: "stop screensaver", submitOnChange: "true", width: 6)
			input(name: "optScreenOn1", type: "bool", defaultValue: "false", title: "Screen On?", description: "screen on", submitOnChange: "true", width: 6)
			input(name: "optScreenOff1", type: "bool", defaultValue: "false", title: "Screen Off?", description: "screen off", submitOnChange: "true", width: 6)
			input "optSetVolume1", "number", title: "Set Volume", required: false, width: 12
			input(name: "optVolumeUp1", type: "bool", defaultValue: "false", title: "Volume Up?", description: "volume up", submitOnChange: "true", width: 6)
			input(name: "optVolumeDown1", type: "bool", defaultValue: "false", title: "Volume Down?", description: "volume down", submitOnChange: "true", width: 6)
			input(name: "optMute1", type: "bool", defaultValue: "false", title: "Mute?", description: "mute", submitOnChange: "true", width: 6)
			input(name: "optUnmute1", type: "bool", defaultValue: "false", title: "Unmute?", description: "unmute", submitOnChange: "true", width: 6)
			input "optPlaySound1", "text", title: "Play Sound", required: false, width: 6
			input(name: "optStopSound1", type: "bool", defaultValue: "false", title: "Stop Sound?", description: "stop sound", submitOnChange: "true", width: 6)
			input "optSpeak1", "text", title: "Speak", required: false, width: 12
			input(name: "optSiren1", type: "bool", defaultValue: "false", title: "Siren?", description: "siren", submitOnChange: "true", width: 6)
			input(name: "optStrobe1", type: "bool", defaultValue: "false", title: "Strobe?", description: "strobe", submitOnChange: "true", width: 6)
			input "optSetScreenBrightness1", "number", title: "Set Screen Brightness", required: false, width: 6
			input(name: "optTriggerMotion1", type: "bool", defaultValue: "false", title: "Trigger Motion?", description: "trigger motion", submitOnChange: "true", width: 6)
		}
		section(getFormat("header-green", "${getImage("Blank")}"+" Display Options")) {
			paragraph "<b>Please, only select ONE of the following options</b>"
			input "optLaunchAppPackage", "text", title: "option: Launch App Package", required: false, submitOnChange: true
			input "optLoadURL", "text", title: "option: Load URL", required: false, submitOnChange: true
			if(optLaunchAppPackage && optLoadURL) {
				paragraph "<b>ONLY SELECT ONE OPTION!</b>"
			} else {
				paragraph ""
			}
			if(optLaunchAppPackage || optLoadURL) {
				paragraph "Go back to Fully after a certain amount of time or when trigger ends?"
				input(name: "triggerORTime", type: "bool", defaultValue: "false", title: "Trigger (off) or Time (on)", description: "trigger OR Time", submitOnChange: "true")
				if(triggerORTime) input "pTime", "number", title: "Wait how long before automaticaly going back to Fully? (in seconds)", required: true
			}
		}
		
		section(getFormat("header-green", "${getImage("Blank")}"+" AFTER Display Options")) {
			input(name: "optBringFullyToFront2", type: "bool", defaultValue: "false", title: "Bring Fully to Front?", description: "bring to front", submitOnChange: "true", width: 6)
			input(name: "optLoadStartURL2", type: "bool", defaultValue: "false", title: "Load Start URL?", description: "start URL", submitOnChange: "true", width: 6)
			input(name: "optStartScreensaver2", type: "bool", defaultValue: "false", title: "Start Screensaver?", description: "start screensaver", submitOnChange: "true", width: 6)
			input(name: "optStopScreensaver2", type: "bool", defaultValue: "false", title: "Stop Screensaver?", description: "stop screensaver", submitOnChange: "true", width: 6)
			input(name: "optScreenOn2", type: "bool", defaultValue: "false", title: "Screen On?", description: "screen on", submitOnChange: "true", width: 6)
			input(name: "optScreenOff2", type: "bool", defaultValue: "false", title: "Screen Off?", description: "screen off", submitOnChange: "true", width: 6)
			input "optSetVolume2", "number", title: "Set Volume", required: false, width: 12
			input(name: "optVolumeUp2", type: "bool", defaultValue: "false", title: "Volume Up?", description: "volume up", submitOnChange: "true", width: 6)
			input(name: "optVolumeDown2", type: "bool", defaultValue: "false", title: "Volume Down?", description: "volume down", submitOnChange: "true", width: 6)
			input(name: "optMute2", type: "bool", defaultValue: "false", title: "Mute?", description: "mute", submitOnChange: "true", width: 6)
			input(name: "optUnmute2", type: "bool", defaultValue: "false", title: "Unmute?", description: "unmute", submitOnChange: "true", width: 6)
			input "optPlaySound2", "text", title: "Play Sound", required: false, width: 6
			input(name: "optStopSound2", type: "bool", defaultValue: "false", title: "Stop Sound?", description: "stop sound", submitOnChange: "true", width: 6)
			input "optSpeak2", "text", title: "Speak", required: false, width: 12
			input(name: "optSiren2", type: "bool", defaultValue: "false", title: "Siren?", description: "siren", submitOnChange: "true", width: 6)
			input(name: "optStrobe2", type: "bool", defaultValue: "false", title: "Strobe?", description: "strobe", submitOnChange: "true", width: 6)
			input "optSetScreenBrightness2", "number", title: "Set Screen Brightness", required: false, width: 6
			input(name: "optTriggerMotion2", type: "bool", defaultValue: "false", title: "Trigger Motion?", description: "trigger motion", submitOnChange: "true", width: 6)
		}
		section(getFormat("header-green", "${getImage("Blank")}"+" Allow to run between what times? (Optional)")) {
        	input "fromTime", "time", title: "From", required: false
        	input "toTime", "time", title: "To", required: false
		}
		section(getFormat("header-green", "${getImage("Blank")}"+" General")) {label title: "Enter a name for this automation", required: false}
        section() {
            input(name: "logEnable", type: "bool", defaultValue: "false", title: "Enable Debug Logging", description: "Enable extra logging for debugging.")
		}
		display2()
	}
}
			
def installed() {
    log.debug "Installed with settings: ${settings}"
	initialize()
}

def updated() {	
    if(logEnable) log.debug "Updated with settings: ${settings}"
    unsubscribe()
	initialize()
}

def initialize() {
    setDefaults()
	if(myContact) subscribe(myContact, "contact", contactSensorHandler)
	if(myMotion) subscribe(myMotion, "motion", motionSensorHandler)
	if(mySwitch) subscribe(mySwitch, "switch", switchHandler)
}

def contactSensorHandler(evt) {
	if(logEnable) log.debug "In contactSensorHandler..."
	if(pauseApp == true){log.warn "${app.label} - App paused"}
    if(pauseApp == false){
		state.contactStatus = evt.value
		if(state.contactStatus == "open") {
			if(logEnable) log.debug "In contactSensorHandler - open"
			beginHandler()
			if(triggerORTime) runIn(pTime,endHandler)
		} else {
			if(logEnable) log.debug "In contactSensorHandler - closed"
			if(!triggerORTime) endHandler()
		}
	}
}

def motionSensorHandler(evt) {
	if(logEnable) log.debug "In motionSensorHandler..."
	if(pauseApp == true){log.warn "${app.label} - App paused"}
    if(pauseApp == false){
		state.motionStatus = evt.value
		if(state.motionStatus == "active") {
			if(logEnable) log.debug "In motionSensorHandler - active"
			beginHandler()
			if(triggerORTime) runIn(pTime,endHandler)
		} else {
			if(logEnable) log.debug "In motionSensorHandler - Not active"
			if(!triggerORTime) endHandler()
		}
	}
}

def switchHandler(evt) {
	if(logEnable) log.debug "In switchHandler..."
	if(pauseApp == true){log.warn "${app.label} - App paused"}
    if(pauseApp == false){
		state.switchStatus = evt.value
		if(state.switchStatus == "on") {
			if(logEnable) log.debug "In switchHandler - on"
			beginHandler()
			if(triggerORTime) runIn(pTime,endHandler)
		} else {
			if(logEnable) log.debug "In switchHandler - off"
			if(!triggerORTime) endHandler()
		}
	}
}
						  					  
def beginHandler() {
	checkTime()
	if(state.timeBetween == true) {
		if(logEnable) log.debug "In launchAppHandler..."
		if(pauseApp == true){log.warn "${app.label} - App paused"}
		if(pauseApp == false){
			if(optBringFullyToFront1) fullyDevice.bringFullyToFront()
			if(optLoadStartURL1) fullyDevice.loadStartURL(optLoadStartURL1)
			if(optStartScreensaver1) fullyDevice.startScreensaver()
			if(optStopScreensaver1) fullyDevice.stopScreensaver()
			if(optScreenOn1) fullyDevice.screenOn()
			if(optScreenOff1) fullyDevice.screenOff()
			if(optSetVolume1) fullyDevice.setVolume(optSetVolume1)
			if(optVolumeUp1) fullyDevice.volumeUp()
			if(optVolumeDown1) fullyDevice.volumeDown()
			if(optMute1) fullyDevice.mute()
			if(optUnmute1) fullyDevice.unmute()
			if(optPlaySound1) fullyDevice.playSound(optPlaySound1)
			if(optStopSound1) fullyDevice.stopSound()
			if(optSpeak1) fullyDevice.speak(optSpeak1)
			if(optSiren1) fullyDevice.siren()
			if(optStrobe1) fullyDevice.strobe()
			if(optSetScreenBrightness1) fullyDevice.setScreenBrightness(optSetScreenBrightness1)
			if(optTriggerMotion1) fullyDevice.triggerMotion()
			
			if(optLaunchAppPackage) fullyDevice.launchAppPackage(optLaunchAppPackage)
			if(optLoadURL) fullyDevice.loadURL(optLoadURL)
		}
	} else {
		if(logEnable) log.debug "In launchAppHandler - Not allowed at this time"
	}	
}

def endHandler() {
	checkTime()
	if(state.timeBetween == true) {
		if(logEnable) log.debug "In bringFullyToFrontHandler..."
		if(pauseApp == true){log.warn "${app.label} - App paused"}
		if(pauseApp == false){
			if(optBringFullyToFront2) fullyDevice.bringFullyToFront()
			if(optLoadStartURL2) fullyDevice.loadStartURL(optLoadStartURL2)
			if(optStartScreensaver2) fullyDevice.startScreensaver()
			if(optStopScreensaver2) fullyDevice.stopScreensaver()
			if(optScreenOn2) fullyDevice.screenOn()
			if(optScreenOff2) fullyDevice.screenOff()
			if(optSetVolume2) fullyDevice.setVolume(optSetVolume2)
			if(optVolumeUp2) fullyDevice.volumeUp()
			if(optVolumeDown2) fullyDevice.volumeDown()
			if(optMute2) fullyDevice.mute()
			if(optUnmute2) fullyDevice.unmute()
			if(optPlaySound2) fullyDevice.playSound(optPlaySound2)
			if(optStopSound2) fullyDevice.stopSound()
			if(optSpeak2) fullyDevice.speak(optSpeak2)
			if(optSiren2) fullyDevice.siren()
			if(optStrobe2) fullyDevice.strobe()
			if(optSetScreenBrightness2) fullyDevice.setScreenBrightness(optSetScreenBrightness2)
			if(optTriggerMotion2) fullyDevice.triggerMotion()
		}
	} else {
		if(logEnable) log.debug "In bringFullyToFrontHandler - Not allowed at this time"
	}	
}

def checkTime() {
	if(logEnable) log.debug "In checkTime - ${fromTime} - ${toTime}"
	if(fromTime) {
		state.betweenTime = timeOfDayIsBetween(toDateTime(fromTime), toDateTime(toTime), new Date(), location.timeZone)
		if(state.betweenTime) {
			state.timeBetween = true
		} else {
			state.timeBetween = false
		}
  	} else {  
		state.timeBetween = true
  	}
	if(logEnable) log.debug "In checkTime - timeBetween: ${state.timeBetween}"
}

// ********** Normal Stuff **********

def setDefaults(){
	if(logEnable) log.debug "In setDefaults..."
    if(pauseApp == null){pauseApp = false}
	if(logEnable == null){logEnable = false}
}

def getImage(type){						// Modified from @Stephack
    def loc = "<img src=https://raw.githubusercontent.com/bptworld/Hubitat/master/resources/images/"
    if(type == "Blank") return "${loc}blank.png height=40 width=5}>"
}

def getFormat(type, myText=""){			// Modified from @Stephack
	if(type == "header-green") return "<div style='color:#ffffff;font-weight: bold;background-color:#81BC00;border: 1px solid;box-shadow: 2px 3px #A9A9A9'>${myText}</div>"
    if(type == "line") return "\n<hr style='background-color:#1A77C9; height: 1px; border: 0;'></hr>"
	if(type == "title") return "<div style='color:blue;font-weight: bold'>${myText}</div>"
}

def display() {
	section() {
		paragraph getFormat("line")
		input "pauseApp", "bool", title: "Pause App", required: true, submitOnChange: true, defaultValue: false
		if(pauseApp) {paragraph "<font color='red'>App is Paused</font>"}
		if(!pauseApp) {paragraph "App is not Paused"}
	}
}

def display2(){
	setVersion()
	section() {
		paragraph getFormat("line")
		paragraph "<div style='color:#1A77C9;text-align:center'>Fully Kiosk Control - @BPTWorld<br><a href='https://github.com/bptworld/Hubitat' target='_blank'>Find more apps on my Github, just click here!</a><br>Get app update notifications and more with <a href='https://github.com/bptworld/Hubitat/tree/master/Apps/App%20Watchdog' target='_blank'>App Watchdog</a><br>${state.version}</div>"
	}       
}  