import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Should return offer not found"
        request {
            url '/offers/2'
            method 'GET'
        }
        response {
            status 404
        }
}