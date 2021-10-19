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
        http://localhost:8080/api/bill/{bill}/{minimumcoins}
        
        bill param : Bill amount
        minimumcoins param : true or fale to return minimum coins or maximum coins for a bill amount
 
 # TESTS COVERED :
 # 1.
 # REQUEST :
        http://localhost:8080/api/bill/10/true
 # RESPONSE :
{
    "coins": [
        {
            "coin": 0.25,
            "noOfCoins": 40
        }
     ]
} 
 
# 2. 
# REQUEST :    
       http://localhost:8080/api/bill/10/false
# RESPONSE :       
{
    "coins": [
        {
            "coin": 0.01,
            "noOfCoins": 100
        },
        {
            "coin": 0.05,
            "noOfCoins": 100
        },
        {
            "coin": 0.1,
            "noOfCoins": 40
        }
    ]
}

# 3. 
# REQUEST :
       http://localhost:8080/api/bill/100/false
# RESPONSE :
{
    "message": "Not enough coins available for 100.0 's  bill ",
    "details": [
        "Not enough coins available for 100.0 's  bill "
    ]
}

# 4. 
# REQUEST :
      http://localhost:8080/api/bill/55/false
# RESPONSE :
{
    "message": "Bill of 55.0 is not in available Bills ",
    "details": [
        "Bill of 55.0 is not in available Bills "
    ]
}

![image](https://user-images.githubusercontent.com/43265292/137948157-5f9f835d-c6c0-4fb3-afe1-8000f99f5b9a.png)


# TO DO : TEST CASES FOR CODE COVERAGE
# TO DO : LOGGING
# TO DO : SECURITY
