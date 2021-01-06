Inside the file
Server.java Client.java Readme.pdf index.html random.txt

Execution Domain
Operating System – MAC OS
Text Editor - Sublime Text
Execution - Terminal Execution
Web Browser - Safari/Chrome Web Browser

NOTE : 8080 has been used as the default port declared as, int port = 8080;

Primarily the environment variables, java has to be set and installed in the computer or else it may not receive the instructions for execution.

The filepath has been mentioned in Client.java default as,
String filedetails = "index.html"; So to change the filename for checking the txt file output, it can be changed here as String filedetails = “random.txt” which is present inside the folder.

While executing the program, all the files must in same folder. Or else it may result in incorrect outputs and look for the filenames which are case-sensitive.
     Make sure all files are in the same folder, i.e Server.java, Client.java, index.html and random.txt as the result may not appear in the WEBPAGE because of the path difference.
-----------------------------------------------------------------------------------------------------
 
TERMINAL EXECUTION

OPEN TWO TERMINALS FOR THE EXECUTION – one for client and one for server. Make sure that, first SERVER program has to be executed before client so that server will be ready to connect with client or else the communication won’t be established resulting in error.

To go to a specific folder,
cd [directory] or cd [filename]. Example cd Desktop
cd [filename/directory]
To see the list of files, type ls

After going to the file path, give these commands –
Server Terminal
Client Terminal

Example - java Client localhost 8080 index.html

Displaying the output in Web Browser
http://localhost:8080//[filepath]/../index.html
Type the URL correctly, after successfully executing the programs successfully in terminal.
If the path or filename is given wrong the result won’t be displayed in the web browser.