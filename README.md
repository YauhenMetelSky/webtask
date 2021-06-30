# Cosmetology clinic "DoctorPro"
The web-system is an online representation of cosmethology clinic. The system contains catalog of procedures. 
The user can choose procedure,doctor,date,time and make an appointment. 
The doctor can add schedule and view appointments to him.  The administrator manages catalog, appointments and users.
To make the appointment user needs to sign in.
### User roles and functions available to them:
|function|	ADMIN| 	GUEST| 	USER| 	DOCTOR |
|---------|-------|-------|------|----------|
|change language|	* |	* |	* |	*|
|change user role(admin,doctor,user)|	*|	|		|  |
view all users and their information	|*||||
find user by name|*||||
find user by surname |*||||
bloc, unblock users	|*||||			
view all actual procedures	|*|	*|	*|	*|
view all procedures 	|*||||			
change information and image procedure|*||||		
change procedure status(active, not active)	|*||||		
add procedure	|*||||		
add appointment		|||*||	
view all your appointments		|||*||
view all new appointments(status claimed)|*||||	
view all finished appointments(status finished)|*||||
view all to you appointments			||||*|
change appointment	|*||*||
confirm appointment	|*||||
finish appointment(change status on finished)	|*||||		
cancel appointment	|*||*||
add schedule		||||*|
change schedule|*||||
cancel schedule|*|||*|
view all doctor's schedule |*|||*|
view all active schedules ||||*|
view account information	|*||*|*|
change account information	|*||*|*|	
send email to user |*||||
logout	|*||*|*|
singing in		||*|||
create new account	||*|||	
activate account by activation link	|*||*|*| <br/>	
# Appointment lifecycle
1. Appointment creating.<br/>
  User can create appointment, he must choose doctor,date and time. After creating appointment has status "CLAIMED".
2. Appointment confirmation.<br/>
   Administrator can see all created appointments.Administrator can see all information about appointment and client. Administrator call client and then can confirm appointment(change status on "CONFIRMED"), can change appointment or can cancel appointment (change status on "CANCELED"). Also administrator can see all other appointments to the choosed doctor, for double check.
3. After procedure.<br/> Administrator must finish appointment (change appointment status to "FINISHED").
4. Every morning before start of working day. All appointments with status "CLAIMED" and "CONFIRMED" change the status on "ENDED" (special event in database).
# Database tables
![](https://github.com/YauhenMetelSky/webtask/blob/master/database/tables.jpg)