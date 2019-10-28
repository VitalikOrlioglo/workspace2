# Dictionary App by Thomas W. Smith and Andrew Lamothe

# Mission
To create a self-contained single page application capable of providing definitions for queried words regardless of network connection.

# User experience
Simply type the word you wish to know the definition for, and hit hit the enter button.

# Background
When the submit button is pressed an event called getDefinition() triggers these events in sequential order:
  * Sets textArea to blank.
  * Creates a variable named "wordToDefine".
  * Saves the string content from the textInputBox to the variable named "wordToDefine".
  * Sets definition text area wrap to true. This makes sure the definition stays within the bounds of the app screen.
  * Compares "wordToDefine" with the keys of a hashMap stored in the app to find the definition.
  * Appends definition to textArea.
  
# Looking Back
This project helped refine our skills with hashmaps, pushing to hashmaps, handling user events, writing functions to reduce repetitive typing, finding appropriate variable names, searching though lists, team management and pair programming.  



