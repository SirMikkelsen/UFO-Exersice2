# Code analysis and performance
We have looked at the program provided, that find the number of the same chars used in the text provided.

## Hypothesis
It was clear from the beginning that the tallyChars() took time to run, so we concentrated on that. We tried to run it so it only took the characters in from A-Z and a-z. We also tried to go in and turn it around so it counted all the characters first in a variable, and only then stored it in the hashmap.

## Explanation
We ended up figuring out that the problem was the file reader. Instead we used a buffered reader. This made the script significantly faster to run since the buffered reader obviously is buffered - whereas the filereader is not.
