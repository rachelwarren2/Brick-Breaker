=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: ___17697781____
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. You may copy and paste from your proposal
  document if you did not change the features you are implementing.

  1. Modeling state using collections
  	-I used an ArrayList for designing the bricks in my game. I had a brick class
  	that had all the features of one brick, but since I needed multiple bricks
  	designed in a row, column structure I decided to use an ArrayList to create
  	this design of bricks.

  2. Collision Detection
  	-The collision detection in the bounce function given to us was not
  	sufficient enough for my game so I updated this detection. I needed to make
  	this more sufficient for when the ball bounces off the paddle and also when 
  	it bounces off the different bricks.

  3. Using I/O to parse in a file format
  	-I used this for parsing in high scores. I created a map from the users name
  	to their score and then saved the top 2 scores to the file. The user must
  	input their user name at the beginning of each game. At the games end, the
  	2 highest scores with the players names are shown to the player on a 
  	pop-up message screen.

  4. Object Oriented Design using inheritance and subtyping
   -I used this for having different types of items fall from the bricks when
   they are hit and if they are caught by the paddle then something happens
   with the game state. I needed to create an item interface and item object
   class that contains all of the methods used by all items. Then I had two
   subclasses, one that was for an item that makes the paddle longer, and 
   another that was for an item that causes the player to lose a life. Both of
   these subclasses extend the ItemObj class.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  The Ball class is for the green ball that moves around the screen and 
  hits into walls, bricks, and the paddle. This class has the basic methods
  that ball needs while extending the GameObj class.
  
  The Brick class is for ONE brick. In my GameCourt I create an array of bricks
  in which I draw bricks in rows and columns, but the class contains the methods
  for just one brick. It also has an alive state that changes when the brick
  has disappeared after being hit.
  
  The Direction class is the same class that was given to us.
  
  The ExtendPaddle class is a subclass of my ItemObj type. In this case my
  item type is always equals to 2 and this class has one additional method. In
  this class, when called(the paddle catches this item)
   the paddle increases its size by 10.
  
  The Game class is the same class given to us with the addition of 2 buttons.
  I added a continue button, that allows the player to continue on in the game
  after losing a life. (Different than the start over button because this button 
  refreshes the entire game). I also added an Instructions button that allows
  the player to view the instructions at any time throughout the game.
  
  The GameCourt class is the same class that is given to us. This is where
  all of my logic for the game is. In this class, I added collision detection
  for the ball hitting the bricks and the ball hitting the paddle. This is also
  where I draw all of my objects and items to the board. In GameCourt I also deal
  with parsing in the high scores file and reading it back out on a message screen
  to the player.
  
  The GameObj class is the same class that was given t us.
  
  The Item Interface just has all of the method headers for all of my ItemObj
  types. I implement this interface in my ItemObj class.
  
  The ItemObj class implements the Item interface. This is the class where
  I have all of the methods that everything of an ItemObj type has. Items
  must be allowed to drop, they must be able to get their type and set their 
  type, and they must be able to draw.
  
  The LoseLIfe class is a subclass of my ItemObj type. In this case my
  item type is always equals to 1 and this class has one additional method. In
  this class, when called(if the paddle catches this item) the player loses
  a life.
  
  The Paddle class extends the GameObj class and it basically draws 
  the paddle on the game board. This paddle has a width and height and also
  a changing x position, but a constant y position. I use these positions to
  determine when the ball intersects with the paddle or when the paddle
  does not catch the ball.
  


- Revisit your proposal document. What components of your plan did you end up
  keeping? What did you have to change? Why?
  I originally stated that I would be using a 2-D array to create the board,
  however I realized that this is extremely unnecessary. Instead, I used an
  ArrayList collection to create the bricks on the board. Additionally,
  I stated that I would use subtyping and inheritance for different types of
  bricks. However, instead I created items that fall whenever a brick is hit
  so I used subtyping and inheritance for the types of items, not different
  types f bricks. I kept the fact that I would have to use collision detection
  for determining when the ball hits the bricks and paddle and I also kept the 
  idea to parse in high scores from the I/O file.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  Yes, I was having a difficult time figuring out the collision
  detection for the paddle and the ball. I had it working from the beginning
  for the most part, but some of the edge cases would work sometimes and then
  act weird at other times. I could not get the collision detection to work
  perfectly for the paddle and ball. Also when the paddle hits a red powerUp, it
  removes all the lives instead of just 1 and I did not have time to fix this.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
   My separation of functionality and private state are okay. Had I had more time, I would
   have liked to used more subtyping and inheritance to create more collections of abstract
   classes and then have those classes have seperate types. 


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.


