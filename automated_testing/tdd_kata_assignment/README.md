# Assignment 1: TDD Kata

## Introduction

This is a **graded exercise** for learning TDD by practicing using [code kata](http://vinaikopp.com/2016/02/09/about_code_katas/).  

The idea behind TDD kata is simple: 
> We are going to work on a single functionality, but we will increase the complexity bit by bit while adding new tests in a TDD manner.  

### Administrative info  
- **Deadline:** end of week 16 of semester (sunday 23:59).
- **Mark:** You can get a total of **25 points** for this exercise, 5 per requirement.
- **Weapon of choice:** Choose the programming language or framework that you like.
- The first 3 requirements are not marked... a warm-up, so to speak. 

### Using the starter project

Prerequisites:
- JDK 17
- Git

Go into the ```tdd-kata``` directory and execute gradle build
```bash
./gradlew build
```

Check that tests are working
```bash
./gradlew test
```

Delete dummy classes App.java and AppTest.java.

Write your own classes and implementation

## Code Kata: Greeting

### Requirement 1
Write a method ```greet(name)``` that interpolates ```name``` into a simple greeting. For example, when 
```name``` is ```"Bob"```, the method should return a string ```"Hello, Bob."```.

### Requirement 2
Handle empty or missing input by introducing a stand-in. For example, when ```name``` is null, then the method 
should return the string ```"Hello, my friend."```.  

In case of multiple empty/null/etc inputs it should greet all the friends: ```"Hello, my friends."```.  

**NB!** This requirement should work with the later requirements as well. Example:  
```"Hello, Amy, Charlotte, and my friends. AND HELLO, BRIAN!"```

### Requirement 3
Handle shouting. When ```name``` is all uppercase, then the method should shout back to the user. 
For example, when ```name``` is ```"JERRY"``` then the method should return the string ```"HELLO, JERRY!"```.

### Requirement 4
Handle two names of input. When ```name``` is an array of two names (or, in languages that support 
it, varargs or a splat), then both names should be printed. For example, when ```name``` is 
```["Jill", "Jane"]```, then the method should return the string ```"Hello, Jill and Jane."```.

### Requirement 5
Handle an arbitrary number of names as input. When ```name``` represents more than two names, 
separate them with commas and close with an Oxford comma and "and". For example, when ```name``` 
is ```["Amy", "Brian", "Charlotte"]```, then the method should return the string ```"Hello, Amy, Brian, and Charlotte."```

### Requirement 6
Allow mixing of normal and shouted names by separating the response into two greetings. 
For example, when ```name``` is ```["Amy", "BRIAN", "Charlotte"]```, then the method should return 
the string ```"Hello, Amy and Charlotte. AND HELLO, BRIAN!"```.

### Requirement 7
If any entries in ```name``` are a string containing a comma, split it as its own input. For 
example, when ```name``` is ```["Bob", "Charlie, Dianne"]```, then the method should return the 
string ```"Hello, Bob, Charlie, and Dianne."```.

### Requirement 8
Allow the input to escape intentional commas introduced by Requirement 7. These can 
be escaped in the same manner that CSV is, with double quotes surrounding the entry. 
For example, when ```name``` is ```["Bob", "\"Charlie, Dianne\""]```, then the method should return 
the string ```"Hello, Bob and Charlie, Dianne."```.
