# Getting Started with Simple Banking

For Postman Collection:

* [Eteration Postman Collection](https://documenter.getpostman.com/view/31370856/2s9YeBfuGJ#4229281c-590c-419d-8396-1957e85cb2f4)

The following terminal commands can be run to stand up the database with Docker.

`cd \simplebanking\src\main\resources\yaml`

`docker-compose up`


**_About the designed Withdraw and Deposit logic (depending on the Unit Test cases delivered in the project):_**

* debit() : It is designed as a debt payment transaction by withdrawing money from a debit bank account.

* credit() : It is designed as a flow of money loading by deposit money into a credit bank account.


**Note:** 

In current banking flows, the transaction type always represents "credit" transaction, which represents money outflow, and "debit" transaction, which represents money inflow. For this case study, a code logic like the above was designed to comply with the Unit Test scenarios.


