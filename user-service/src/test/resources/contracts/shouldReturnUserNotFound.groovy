import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Should return user not found"
        request {
            url '/users/2'
            method 'GET'
        }
        response {
            status 404
        }
}