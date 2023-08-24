# OOP-Bubble-Trouble-Game

Example gameplay: https://youtu.be/cn9Gq35ZnSw 

The game is played with right and left arrow keys, and the spacebar to shoot the arrow.

The arrow will stay still and get longer until it reaches the top side of the screen. If the arrow were to hit a ball, the ball would divide into two smaller balls if the initial ball is big enough. The game is played until no balls are left or no time is left.

Algorithm explanation:<br />
•	First I created the canvas with desired values.<br />
•	Created Environment Constructor and started with giving specific class variables their initial values.<br />
•	Created Environment class and bar class, then drew the background, bar image, and time bar in order onto StdDraw.<br />
•	Draw each and every ball individually then move them.<br />
•	If there is no ball left to draw, finish the game<br />
•	Check if space is pressed, and then check if there is an arrow on the screen at the moment.<br />
•	Draw the line, while drawing check if any and every ball collides with the arrow. <br />
•	Via using the tip (distance between two points) and the remaining parts of the arrow(checking both axis).<br />
•	If it collides remove the arrow and split the ball if splittable.<br />
•	Move the player if buttons are pressed.<br />
•	Check if the player collides with any of the balls. Turn player hitbox into a bigger rectangle by increasing each side with the radius of the ball. Then check if their centers hit the rectangle.<br />
•	 If it does finish the game and redraw the scene with current variables and no balls.<br />
•	Ask if the user wants to play again or quit the game.<br />
<br />

Starting of the game and throwing an arrow
 
![image](https://github.com/Kerem-Kurt/OOP-Bubble-Trouble-Game/assets/121832450/a8fb7fa9-de71-4641-9f46-6fbf7f3d534e)
![image](https://github.com/Kerem-Kurt/OOP-Bubble-Trouble-Game/assets/121832450/0c1d5d77-2905-447e-8ed4-6a49dae62354)
