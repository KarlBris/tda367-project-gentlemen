-Gentlemen's Dodgeball-

A 2D multiplayer game made in java using LWGJL and JBOX2D


-HOW TO RUN-

If you are running this in eclipse using the latest repository from google code, just run Main in core
to play, but note the platfor specific info at the end of this readme file.

The game uses a library that needs native files depending on the OS, so the 
folder containing the file "Dodgeball.jar" must also contain the LWGJL version 2.7.1 folder,
named "lwjgl-2.7.1", which may be downloaded from the LWJGL website at http://lwjgl.org/


-HOW TO PLAY-

The game is played by picking up and throwing balls at the opponent, as well as picking up and
capturing their flags. To capture an enemy flag, pick it up and take it to your own flag, but beware;
you can only capture an enemy flag when your own flag is at its home position. If a player is hit
with a ball while he carries a flag, the flag will be dropped on the ground. If a player picks up his
own flag while it lies on the ground, it will immediately returned to its home position.

The players are awarded points as following:
* Hitting another player with a ball: 1 point
* Returning the own flag to the base: 4 points
* Capturing the opposing team's flag: 15 points

The first player to reach 100 points is the winner!

Player one uses the WASD-keys for movement and the V key to pick up and throw balls
Player two uses the arrow keys for movement and the . (period) key to pick up and throw balls

To switch level, hold the Ctrl-key at any time and press one of the following keys to switch instantly:
1: Level one, a fortress-like level.
2: Level two, a arena-like level. Also the level that is loaded when the game is started.
3: Level three, a intricate level with a choke in the middle.
R: Randomly generated level, filled with random blocks that may or may not block the path between
bases and/or make all or some of the balls unreachable


-PLATFORM SPECIFIC INFO-

* Windows: None
* OSX: None
* Linux (Ubuntu): LWJGL requires a direct GLX context, (as opposed to an indirect one) and using an 
indirect context will result in an error. Through empirical studies, this problem has shown to
be solved by uninstalling Nvidia graphics drivers and installing the ubuntu provided driver.