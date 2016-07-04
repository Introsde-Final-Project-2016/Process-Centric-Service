# Process-Centric-Service

This service only works when there is update operation. When request comes from the user service it takes the information by using REST technologies and translate it to JSON type messages and sends it to the Storage layer which will send it to the database. When user trigger life status update operation, it calls the compare operation inside the Business-Logic layer. According to new weight or height,  after the return of Business layer, the BMI value of the person is automatically  updated. After the message from Business layer, this service sets a new goal with 3 possible options. Losing weight, gaining weight or keeping that weight.  

For more information check wiki: https://github.com/Introsde-Final-Project-2016/Process-Centric-Service/wiki

Heroku address: https://secure-refuge-96261.herokuapp.com/ 
