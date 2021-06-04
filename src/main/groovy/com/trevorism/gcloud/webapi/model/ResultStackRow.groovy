package com.trevorism.gcloud.webapi.model

import groovy.transform.ToString

@ToString
class ResultStackRow {
    String username
    String avatarUrl
    String location
    String realName
    String email
    int countOfPublicRepos
    Date creationDate
    Date lastUpdateDate
}
