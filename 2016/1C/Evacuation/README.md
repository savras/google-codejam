# Evacuation solution analysis

Here's my solution to question 1A of round 1C. Please do let me know if my calculation is off or can be improved.

This solution uses an array of length N, where N represents the number of parties. Each index will hold the number of senators in the party. We will also keep a counter tracking the largest number of senators in a party.

The evacuation plan is created by looping through the array indices 0 to N until largest == 0, every time looking for the party with the largest number of senators.
Everytime the loop iterates completely over N, the largest number is decremented by 1.

An improvement to be made over the current solution is to keep track of the index when we have selected 2 senators in the current loop and continue from there. (Not implemented).

This means that the last evacuation plan for the loop of largest number will have only 1 senator if there are odd number of parties with the number of senators equal to the largest number.

Best case we loop once, having 2 operations for two parties with 1 senator each (as it is guaranteed that no party will have a majority at the beginning).

Worst case we have 26 parties, with 24 parties having exactly 1 senator each, and 2 parties having 488 senators. In this case, we will have to loop over the 26 indexes 488 times, going from Party A to Party Z, with 24 parties failing the conditional check for (Pi == largest).
This gives us 488 * 26 = 12688 operations.
