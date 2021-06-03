package com.trevorism.gcloud.webapi.controller

import com.trevorism.gcloud.webapi.model.SearchRequest
import com.trevorism.gcloud.webapi.model.SearchResult
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Api("Search Operations")
@Path("result")
class SearchController {

    @ApiOperation(value = "Creates a list of search results")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    List<SearchResult> search(SearchRequest request){
        [new SearchResult(username: "trevorism", avatarUrl: "https://avatars.githubusercontent.com/u/1159177?v=4", email: "trevorism@gmail.com", countOfPublicRepos: 66, location: "VA, USA", realName: "Trevor Brooks", creationDate: new Date())]
    }

}
