# Gui2048

A recreation of the game 2048
![v1](https://github.com/ayoung206/Gui2048/assets/32970772/f14c34be-e450-4ef7-a860-512ca716783a)

Base details: 
2048 is played on a simple grid of size 4x4, with numbered tiles (all powers of 2) that will slide
in one of 4 directions (UP, DOWN, LEFT, RIGHT) based on input from the user. Every turn, a new
tile will randomly appear in an empty spot on the board with a value of 2 (except at the
beginning, when 2 random tiles get values of 2).
Tiles will slide as far as possible in the chosen direction until they are stopped by either another
tile or the edge of the grid. If two tiles of the same number collide while moving, they will
merge into a tile with a total value of the two tiles that collide. For example, if two tiles of value
8 slide into one another the resulting tile will have a value of 16. The resulting tile cannot merge
with another tile again in the same move.

<img width="620" alt="Screenshot 2024-02-27 at 1 58 27 AM" src="https://github.com/ayoung206/Gui2048/assets/32970772/3b046a9e-5090-4b60-80a2-a8d56e444010">
