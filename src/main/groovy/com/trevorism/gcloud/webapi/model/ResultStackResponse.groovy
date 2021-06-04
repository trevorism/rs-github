package com.trevorism.gcloud.webapi.model

import groovy.transform.ToString

@ToString
class ResultStackResponse {
    long totalCount = 0
    List<ResultStackRow> records = []
}
