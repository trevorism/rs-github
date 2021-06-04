package com.trevorism.gcloud.webapi.model

class GithubResponse {
    long total_count
    String incomplete_results
    List<GithubUser> items = []

}
