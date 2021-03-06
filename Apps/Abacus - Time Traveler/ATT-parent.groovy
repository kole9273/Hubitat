/**
 *  ****************  Abacus - Time Traveler Parent ****************
 *
 *  Design Usage:
 *  Track how long a Device has been active. Displays Daily, Weekly, Monthly and Yearly Timers!
 *
 *  Copyright 2018 Bryan Turcotte (@bptworld)
 *
 *  Special thanks to (@Cobra) for use of his Parent/Child code and various other bits and pieces.
 *  Thanks to Stephan Hackett (@stephack) for the idea to change up the colors.
 *
 *  This App is free.  If you like and use this app, please be sure to give a shout out on the Hubitat forums to let
 *  people know that it exists!  Thanks.
 *
 *  Remember...I am not a programmer, everything I do takes a lot of time and research!
 *  Donations are never necessary but always appreciated.  Donations to support development efforts are accepted via: 
 *
 *  Paypal at: https://paypal.me/bptworld
 *
 *-------------------------------------------------------------------------------------------------------------------
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 * ------------------------------------------------------------------------------------------------------------------------------
 *
 *  If modifying this project, please keep the above header intact and add your comments/credits below - Thank you! -  @BPTWorld
 *
 *  App and Driver updates can be found at https://github.com/bptworld/Hubitat
 *
 * ------------------------------------------------------------------------------------------------------------------------------
 *
 *  Changes:
 *
 *  V1.0.4 - 02/16/19 - Big maintenance release. Reworked a lot of code as I continue to learn new things.
 *  V1.0.3 - 01/15/19 - Updated footer with update check and links
 *  V1.0.2 - 01/06/19 - Squashed a bug in the Weekly count reset. Also added in a way to delete a single line from the reports.
 *						This is needed to get rid of the orphans created from the Weekly Count bug.
 *  V1.0.1 - 01/04/19 - Major logic change to calculate how long a device was active.
 *  V1.0.0 - 01/03/19 - Initial release.
 *
 */

def setVersion() {
	state.version = "v1.0.4"
}

definition(
    name:"Abacus - Time Traveler",
    namespace: "BPTWorld",
    author: "Bryan Turcotte",
    description: "Track how long a Device has been active. Displays Daily, Weekly, Monthly and Yearly Timers!",
    category: "Convenience",
    iconUrl: "",
    iconX2Url: "",
    iconX3Url: ""
    )

preferences {
     page name: "mainPage", title: "", install: true, uninstall: true
} 

def installed() {
    log.debug "Installed with settings: ${settings}"
    initialize()
}

def updated() {
    log.debug "Updated with settings: ${settings}"
    unsubscribe()
    initialize()
}

def initialize() {
    log.info "There are ${childApps.size()} child apps"
    childApps.each {child ->
    log.info "Child app: ${child.label}"
    }
}

def mainPage() {
    dynamicPage(name: "mainPage") {
    	installCheck()
		if(state.appInstalled == 'COMPLETE'){
			section(getFormat("title", "${app.label}")) {
				paragraph "<div style='color:#1A77C9'>Track how long a Device has been active. Displays Daily, Weekly, Monthly and Yearly Timers!</div>"
				paragraph getFormat("line")
			}
			section("Instructions:", hideable: true, hidden: true) {
				paragraph "<b>Information</b>"
				paragraph "Daily timers are reset each morning.<br>Weekly timers are reset each Sunday.<br>Monthly timers are reset at on the 1st of each month.<br>Yearly timers get reset on Jan 1st.<br>All timers resets happen between 12:05am and 12:10am"	
				paragraph "Also, times are not added into totals until the device turns off (off/inactive/closed/idle)"
			}
			section(getFormat("header-green", "${getImage("Blank")}"+" Child Apps")) {
				app(name: "anyOpenApp", appName: "Abacus - Time Traveler Child", namespace: "BPTWorld", title: "<b>Add a new 'Abacus - Time Traveler' child</b>", multiple: true)
			}
			section(getFormat("header-green", "${getImage("Blank")}"+" General")) {
       			label title: "Enter a name for parent app (optional)", required: false
 			}
			display2()
		}
	}
}

def installCheck(){         
	state.appInstalled = app.getInstallationState() 
	if(state.appInstalled != 'COMPLETE'){
		section{paragraph "Please hit 'Done' to install '${app.label}' parent app "}
  	}
  	else{
    	log.info "Parent Installed OK"
  	}
}

def getImage(type) {
    def loc = "<img src=https://raw.githubusercontent.com/bptworld/Hubitat/master/resources/images/"
    if(type == "Blank") return "${loc}blank.png height=40 width=5}>"
}

def getFormat(type, myText=""){
	if(type == "header-green") return "<div style='color:#ffffff;font-weight: bold;background-color:#81BC00;border: 1px solid;box-shadow: 2px 3px #A9A9A9'>${myText}</div>"
    if(type == "line") return "\n<hr style='background-color:#1A77C9; height: 1px; border: 0;'></hr>"
	if(type == "title") return "<h2 style='color:#1A77C9;font-weight: bold'>${myText}</h2>"
	if(type == "wording") return "<div style='color:#1A77C9'>${myText}</div>"
}

def display2(){
	section() {
		setVersion()
		paragraph getFormat("line")
		paragraph "<div style='color:#1A77C9;text-align:center'>Abacus - Time Traveler - @BPTWorld<br><a href='https://github.com/bptworld/Hubitat' target='_blank'>Find more apps on my Github, just click here!</a><br>Get app update notifications and more with <a href='https://github.com/bptworld/Hubitat/tree/master/Apps/App%20Watchdog' target='_blank'>App Watchdog</a><br>${state.version}</div>"
	}       
}     
