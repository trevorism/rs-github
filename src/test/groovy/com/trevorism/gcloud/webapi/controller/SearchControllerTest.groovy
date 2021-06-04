package com.trevorism.gcloud.webapi.controller

import com.google.gson.Gson
import com.trevorism.gcloud.webapi.model.GithubResponse
import com.trevorism.gcloud.webapi.model.GithubUser
import com.trevorism.gcloud.webapi.model.ResultStackResponse
import com.trevorism.gcloud.webapi.model.ResultStackRow
import com.trevorism.gcloud.webapi.model.SearchRequest
import com.trevorism.http.headers.HeadersHttpClient
import org.apache.http.HttpEntity
import org.apache.http.StatusLine
import org.apache.http.client.methods.CloseableHttpResponse
import org.junit.Test

class SearchControllerTest {

    private Gson gson = new Gson()

    @Test
    void testSearch() {
        SearchController sc = new SearchController()
        GithubResponse githubResponse = new GithubResponse(total_count: 1, items: [createMockGithubUser()])
        String json = gson.toJson(githubResponse)
        sc.httpClient = [get: { url, headers -> createCloseableHttpResponse(json) }] as HeadersHttpClient
        ResultStackResponse response = sc.search(new SearchRequest(value: "test", page: 1))

        assert response
        assert response.totalCount == 1
        assert response.records
    }

    @Test
    void testUser() {
        SearchController sc = new SearchController()
        GithubUser githubUser = createMockGithubUser()
        String json = gson.toJson(githubUser)

        sc.httpClient = [get: { url, headers -> createCloseableHttpResponse(json) }] as HeadersHttpClient
        ResultStackRow result = sc.getUser("testUsername")
        assert result
        assert result.username == "testUsername"
        assert result.email == "testEmail"
        assert result.avatarUrl == "https://avatarurl"
    }

    private GithubUser createMockGithubUser() {
        GithubUser githubUser = new GithubUser(login: "testUsername", email: "testEmail", avatar_url: "https://avatarurl")
        githubUser
    }

    @Test
    void testGetAuthHeader (){
        SearchController sc = new SearchController()

        String authHeader = sc.createAuthHeader().toString()
        assert authHeader.startsWith("[Authorization:token ")
        assert authHeader.length() > "Authorization:token ".length() + 3
    }

    private static CloseableHttpResponse createCloseableHttpResponse(String responseString, int statusCode = 200) {
        Closure getContentClosure = { -> new ByteArrayInputStream(responseString.getBytes()) }
        Closure getContentLengthClosure = { -> Long.valueOf(responseString.size()) }
        Closure getStatusLineClosure = { -> return [getStatusCode: { -> return statusCode }] as StatusLine }
        HttpEntity entity = [getContentLength: getContentLengthClosure,
                             getContentType  : { return null },
                             getContent      : getContentClosure,
                             isStreaming     : { -> true }] as HttpEntity
        CloseableHttpResponse response = [getEntity: { -> entity }, getStatusLine: getStatusLineClosure] as CloseableHttpResponse
        return response
    }
}
