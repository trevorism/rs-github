package com.trevorism.gcloud.webapi.controller

import com.google.gson.Gson
import com.trevorism.gcloud.webapi.model.*
import com.trevorism.http.HttpClient
import com.trevorism.http.JsonHttpClient
import com.trevorism.http.headers.HeadersHttpClient
import com.trevorism.http.headers.HeadersJsonHttpClient
import com.trevorism.http.util.ResponseUtils
import com.trevorism.secure.PropertiesProvider
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.apache.http.client.methods.CloseableHttpResponse

import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Api("Search Operations")
@Path("result")
class SearchController {

    private HeadersHttpClient httpClient = new HeadersJsonHttpClient()
    private PropertiesProvider propertiesProvider = new PropertiesProvider()
    private Gson gson = new Gson()

    @ApiOperation(value = "Creates a list of search results")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResultStackResponse search(SearchRequest request) {
        if (!request.value) {
            throw new BadRequestException("Unable to process an empty search")
        }

        CloseableHttpResponse response = httpClient.get("https://api.github.com/search/users?q=${request.value}+in:name+in:email&per_page=10&page=${request.page}", createAuthHeader())
        String json = ResponseUtils.getEntity(response)
        GithubResponse githubResponse = gson.fromJson(json, GithubResponse)

        ResultStackResponse rsr = new ResultStackResponse()
        rsr.totalCount = githubResponse.total_count
        rsr.records = githubResponse.items.collect{
            getUser(it.login)
        }
        return rsr
    }

    @ApiOperation(value = "Gets user details")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{username}")
    ResultStackRow getUser(@PathParam("username") String username) {
        CloseableHttpResponse response = httpClient.get("https://api.github.com/users/${username}", createAuthHeader())
        String responseJson = ResponseUtils.getEntity(response)
        GithubUser githubUser = gson.fromJson(responseJson, GithubUser)
        return new ResultStackRow(username: githubUser.login, avatarUrl: githubUser.avatar_url, location: githubUser.location, realName: githubUser.name, email: githubUser.email, countOfPublicRepos: githubUser.public_repos, creationDate: githubUser.created_at, lastUpdateDate: githubUser.updated_at)
    }

    private def createAuthHeader() {
        ["Authorization": "token ${propertiesProvider.getProperty('accessToken')}".toString()]
    }
}
