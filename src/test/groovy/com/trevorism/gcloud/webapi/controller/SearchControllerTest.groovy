package com.trevorism.gcloud.webapi.controller

import com.trevorism.gcloud.webapi.model.SearchRequest
import org.junit.Test

class SearchControllerTest {

    @Test
    void testSearch() {
        SearchController sc = new SearchController()
        assert sc.search(new SearchRequest(value: "asdf"))

    }
}
