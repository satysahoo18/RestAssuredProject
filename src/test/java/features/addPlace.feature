Feature: Validating Place API
@AddPlace
Scenario Outline: Verify if the place is being added sucessfully using Add Place API 
#Given Add Place Payload with #This was commented as this can be used furter and for this we have to use scenario
Given Add Place Payload with "<name>" "<phone_number>" "<address>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API call is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP" 
And verify the place_Id created with "<name>" using "GetPlaceAPI" with "get" http request

Examples:
|name           |phone_number      |address                  |
|Frontline house|(+91) 983 893 3937|29, side layout, cohen 09|
#|desertland     |4812148724        |new lander               |
#|winterland     |+542425           |old city                 |


@DeletePlace
Scenario: Verify if the place added is deleted sucessfully using Delete Place API 
#Given Add Place Payload with #This was commented as this can be used furter and for this we have to use scenario
Given Delete Place Payload
When user calls "DeletePlaceAPI" with "delete" http request
Then the API call is success with status code 200
And "status" in response body is "OK"
