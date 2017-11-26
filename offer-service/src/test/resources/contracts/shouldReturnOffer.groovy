import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Should return a offer"
        request {
            url '/offers/1'
            method 'GET'
        }
        response {
            status 200
            body(["id"      : 1,
                  "title"   : "title",
                  "content" : "content",
                  "authorId": 1])
            headers {
                contentType(applicationJson())
            }
        }
}