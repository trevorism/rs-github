package com.trevorism.gcloud

/**
 * @author tbrooks
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

def contextRootContent

Given(/the application is alive/) {  ->
    try{
        new URL("https://rs-github-dot-trevorism-pushbutton.ue.r.appspot.com/api/ping").text
    }
    catch (Exception ignored){
        Thread.sleep(10000)
        new URL("https://rs-github-dot-trevorism-pushbutton.ue.r.appspot.com/api/ping").text
    }
}

When(/I navigate to {string}/) { String string ->
    contextRootContent = new URL(string).text
}

Then(/then a link to the help page is displayed/) {  ->
    assert contextRootContent
    assert contextRootContent.contains("/api/help")
}