<div align="center">
  <h1>Java Database Connector SQL Client</h1>

<a href="https://github.com/IRPCode/JDBC-SQL-Client/tree/main/src/main/java/com/irpcode">The source code is available here</a>
  
</div>

<div align="center">
<h2>Getting Started</h2>
</div>

First, make sure that you have the following things installed:

  - <a href="https://www.oracle.com/java/technologies/downloads/">Java</a>
  - <a href="https://maven.apache.org/">Maven</a>
    - <a href="https://www.tutorialspoint.com/maven/maven_environment_setup.htm">Make sure it is properly configured on your system</a>
  - <a href="https://www.mysql.com/downloads/">MySQL</a>
    - This program was tested using MySQL 9.1
    - Ensure your MySQL database is running through Port 3306

<div align="center">
<h2>Getting started</h2>
</div>

<div align="center">
Starting off, make sure you are only using a database that has up to<b> six columns</b>!
</div>

<div align="center">
  
![image](https://github.com/user-attachments/assets/d8d8b090-f3f4-47a4-bd7e-fcfd593b42fd)

</div>

- When the program boots, you will be met with a login panel. Ensure that you enter the database that you want to use
- If your MySQL service is not running, make sure you start the service before attempting to log in, otherwise it cannot connect

<div align="center">
<h2>The main panel</h2>
</div>

<div align="center">
  
![image](https://github.com/user-attachments/assets/356ff91c-0aa4-4608-a0c8-f02beb969059)

</div>

- This is the section that will show you all of your database's entries. There are a few elements at the top to take note of.
  - Enter the table you wish to view that is in the database you entered earlier into the text field on the left.
  - The select 'Select Table/Show All'
  - If you wish to specify what column to base your query off of, you can select that in the drop down menu, and then enter a key term into the text field to the left
  - After doing so, you can hit the search button, and the results will update

<div align="center">
  
![image](https://github.com/user-attachments/assets/0b0afc11-573a-4365-8ea8-92130534ecf3)

</div>

- You can use the left and right buttons to flip view more entries


<div align="center">
<h2>Database Options</h2>
</div>

The options panel on the left side of the main panel are used to do basic modifications to your database. These are the following:
    - Updating, inserting, and deleting data entries
    - Creating, editing, and deleting tables
    - Changing, creating, and deleting databases

<h3>Modifying Entries</h3>

- In order to update an entry in your database, you must enter a table in your database, and then hit the 'get columns button'.

<div align="center">
  
![image](https://github.com/user-attachments/assets/a87f9e27-1940-4638-a0df-ed232e3b1491)

</div>
  
  - You are able to entier the different columns are want to modify, and then there is a specifying statement at the end of the panel where you can tell the program to update entries that are greater than, less than, or equal to the column with a specified value.

<div align="center">
  
![image](https://github.com/user-attachments/assets/578915f1-4903-4040-9f52-af526aa1583a)

<div

  - Inserting new information is as simple as entering your table's name, and then entering your desired information to enter into your database.

<div align="center">

![image](https://github.com/user-attachments/assets/e5f620c2-1de3-441c-bdb3-dace0b2d184f)

</div>

  - Deleting entries is largely the same, just be careful, as deleted information cannot be recovered. You will be required to verify your credentials before deleting anything.

<h3>Modifying The Table</h3>

<div align="center">

![image](https://github.com/user-attachments/assets/aca69f2a-8eb4-448f-a3bf-6974e4e8f722)

</div>

- In order to create a table, you must enter a table name, and then use the drop down boxes to specify the values in the table. The text fields are used for column names. 

<div align="center">

![image](https://github.com/user-attachments/assets/d79e41bf-28ae-47dd-bbe0-3d2ed585e09e)

</div>

- Enter your table name, and then you can change the data type a column uses. Make sure that you do not attempt to convert numeric types to string types, or the database will refuse the transaction.

<div align="center">

![image](https://github.com/user-attachments/assets/bf220fad-d78a-4578-b220-2a12eddfc649)

</div>

- Deleting a table will require you to login to prevent any accidental modifications.

<div align="center">

![image](https://github.com/user-attachments/assets/0004d0a3-fc77-4d8f-8fc0-f48768da871e)|

<h3>Modifying The Database</h3>

</div>

- Changing databases effects all other aspects of the program. Make sure when verifying your credentials that you enter the same table name if you want to continue to modify it.


<div align="center">

![image](https://github.com/user-attachments/assets/17f34e17-beae-4d0d-9cc0-85df24f95e6b)

</div>

  - NOTE: You will have to enter a table name to update the main panel.

<div align="center">

![image](https://github.com/user-attachments/assets/bcfb6cd9-9227-4c2c-8fb3-5c071cb57ccd)

</div>

 - You can directly create a new database, however you must use the previous 'change database' option to use it. You will have to login to verify your credentials. 

<div align="center">

![image](https://github.com/user-attachments/assets/13009ea6-7f66-403b-9af1-843a7e217d79)

</div>

 - When deleting a databse, make sure you swap to another database as to ensure you can safely delete it without causing any issues.
