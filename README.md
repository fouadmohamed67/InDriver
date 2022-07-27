# Description
# sprint2 Requirments
1. The user should be able to register to the system. The user should provide username, 
mobile number, email (optional), and password. If the user is going to register to the 
system as a driver so the driving license and national id should be provided. The user 
should be able to login into the system once the registration is completed. If the user 
registers as a driver, so the user should be able to login into the system once the admin 
user verify the registration
2. The admin user should be able to verify driver registration. So the admin should be able to 
list all pending driver registrations and verify any pending driver registration.
3. The user should be able to request a ride given a source and a destination. For a simplicity 
user can enter the source area’s name and the destination area’s name.
4. The driver should be able to add some areas to get notification when any ride is requested 
and one of these areas is added as the source area. These areas will be called as “favorite 
areas”. 
5. The driver should be able to list all rides with source area within one of the driver’s favorite 
areas. The driver should be able to suggest a price to this ride and notify the user with this 
price. Each price suggestions is called an “offer”.
6. The admin should be able to suspend any driver/user account. By suspending any account 
the corresponding user shouldn’t be able to login into the application.
7. The user should be able to rate any driver. The user should provide a star rating to the 
driver from 1 to 5 (1 worst, 5 Best)
8. The driver should be able to list user ratings. All user ratings should be visible to the driver.
9. The user should be able to check the average rating for the driver. The driver’s information 
should include the average user rating.
# sprint2 Requirments
1. You need to expose Phase 1 requirements as a web services, and apply design patterns to 
those requirements as needed within sprint 1 requirements.

2. The admin should be able to show the events that happened on a specific rides. Therefore, 
we need to save every action happened on every ride. The possible actions are

      1. Captain put a price to the ride (To show this event, event name, event time, captain 
name and the price should be printed)
     2. User accepts the captain price (To show this event, event name, event time and user 
name should be printed)
     3. Captain arrived to user location (To show this event, event name, event time, captain 
name and user name should be printed)
     4. Captain arrived to user destination (To show this event, event name, event time, 
captain name and user name should be printed).
3. The admin should be able to add discounts on specific areas, so if any ride is going to that 
area, a 10% discount should be applied to the ride price. This discount won’t affect the 
price that the captain suggest. The ride price will be added with no discount to the captain 
balance once completing the ride ,however, the discount will be applied to what the user 
will pay to this ride.
4. Refactor booking ride logic to add a new way to book a ride. You should do two major 
refactoring steps
     1. Only the drivers within the ride source area and available to handle a new request
should be notified. So if a driver is currently handling another ride (the user accept the 
driver offer, so the driver will handle this ride), so this driver shouldn’t be notified.
     2. The user should be able to specify number of passenger in this ride. So the user may be 
wiling to take this ride with another user.
5. Various discounts will be applied to the ride price. So what the user will pay should pass by 
some discounts, if the discount should be applied then the ride price (what the user should 
pay) should be decreased accordingly to the discount. Check the following conditions and 
apply every discount to the ride price if it matches the condition critera.
      1. If the ride is the first ride to the user, apply 10% discount.
      2. If the ride destination area is one of the areas added by the admin to apply discounts 
on, apply 10% discount
      3. If the ride contains two passengers, apply 5% discount.
      4. If the ride date is on a public holiday, apply 5% discount.
      5. If the ride date matches the birth date for the user request the ride. Apply 10% 
discount (as a happy birthday gift :D )

