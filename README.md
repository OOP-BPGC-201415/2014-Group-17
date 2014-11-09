Group-17
========
Tests:

Tests contain the test method of each function the code shall implement.
Because these functions are basic and independant of other functions, mock objects haven't been used. The function placebet however depends on checkevent because bets can be placed only on those events which are yet to take place. Conditions like date of the event and presence in the event list have to be checked. A mock object has been used to test this function and is added in the folder named MockObjects.

Some of these functions throw exceptions. User defined exceptions like ScoreNotAvailableException and EventNotOccuredException have been added in a seperate folder.
