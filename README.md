# changeRequestor

# Challenge: Create a Spring Boot service that exposes a REST API that allows a user to request change for a given bill. The service should assume there are a finite number of coins. 

Requirements:
• Available bills are (1, 2, 5, 10, 20, 50, 100)

• Available coins are (0.01, 0.05, 0.10, 0.25)

• Start with 100 coins of each type

• Change should be made by utilizing the least amount of coins

• API should validate bad input and respond accordingly

• Service should respond with an appropriate message if it does not have enough coins to make change

• The service should maintain the state of the coins throughout the transactions

• Deliver the code with test cases

• Upload your code to Github and come to interview prepared to walk through code

Bonus:

• Allow for number of coins to be configurable and easily changed

• Allow for the user to request for the most amount of coins to make change


# SOLUTION :

 # API : 
        http://localhost:8080/api/bill/50
 
 # RESPONSE : {
              "coins": [
                 {
                  "coin": 0.25,
                  "noOfCoins": 200
                 }
              ]
            }
            
            
            http://localhost:8080/api/bill/3
            
            {
              "message": "Bill of 3.0 is not in available Bills ",
              "details": [
                  "Bill of 3.0 is not in available Bills "
              ]
            }



# TO DO : TEST CASES FOR CODE COVERAGE
# TO DO : LOGGING
# TO DO : SECURITY
