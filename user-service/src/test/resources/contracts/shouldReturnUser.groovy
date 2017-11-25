import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Should return an user"
        request {
            url '/users/1'
            method 'GET'
        }
        response {
            status 200
            body(["id": 1, "username": "username"])
            headers {
                contentType(applicationJson())
            }
        }
}