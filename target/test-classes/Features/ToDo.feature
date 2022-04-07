@AllScenarios
Feature: ToDo List application
  
 Background: 
 Given Launch Chrome driver in path "C:\\Users\\splpt\\eclipse-workspace\\ToDos\\ToDos\\chromedriver.exe" and initialize webdriver with base url "http://todomvc.com/examples/angularjs/#/"

 		@Scenario1
    Scenario: Verify if the item is added to the To do list
  	Given User enters To do List "<ToDoList>"
  	Then verify if the item listed in added "<ToDoList>"
  
  	Examples:
    	|ToDoList|
    	|Drink water every hour,Excersice daily|    
        
    @Scenario2
    Scenario: Verify if the item is crossed out and verify the counter on the bottom-left    
  	Given User enters To do List "<ToDoList>"
  	Then verify if the item listed in added "<ToDoList>"
  	And verify if "<CompleteItem>" item crossed out and counter added "<ToDoList>"
  
  	Examples:
    	|ToDoList||CompleteItem|
    	|Drink water every hour,Excersice daily||Drink water every hour|
    
    @Scenario3
    Scenario: Verify if the item is removed from the list    
    Given User enters To do List "<ToDoList>"
  	Then verify if the item listed in added "<ToDoList>"
  	Then delete the item
  	And verify if item is removed from list "<CompleteItem>"
  
     Examples:
    	|ToDoList||CompleteItem|
    	|Drink water every hour,Excersice daily||Drink water every hour|
    
    @Scenario4
    Scenario: Verify Active and completed Items    
    Given User enters To do List "<ToDoList>"
  	Then verify if the item listed in added "<ToDoList>"
  	And verify if "<CompleteItem>" item crossed out and counter added "<ToDoList>"
  	Then click on active button to check active items "<ActiveItem>"
  	And click on completed button to check completed items "<CompleteItem>"
  
    Examples:
    	|ToDoList||CompleteItem||ActiveItem|
    	|Drink water every hour,Excersice daily||Drink water every hour||Excersice daily|
    
 
    